package com.eomcs.pms.config;

import javax.sql.DataSource;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

// 프론트 컨트롤러는 객체를 보관할 수 있는 bean container를 갖고 있다.  
// 빈컨테이너에는 페이지 컨트롤러 객체를 보관한다.
// 또한 페이지 컨트롤러가 의존하는 객체도 보관한다.
// 
// 다음은 이런 빈컨테이너의 행동을 설정하는 클래스이다.
//

//1) 빈 컨테이너가 자동으로 객체를 생성해야 하는 패키지를 등록한다.
@ComponentScan("com.eomcs.pms")

//2) Spring WebMVC 관련 객체를 찾아서 등록하는 기능을 활성화시킨다.
@EnableWebMvc

//3) JDBC 정보를 담고 있는 프로퍼티 파일을 로딩한다.
@PropertySource("classpath:com/eomcs/pms/config/jdbc.properties")

//4) 애노테이션을 이용하여 트랜잭션을 다루는 기능을 활성화시킨다.
@EnableTransactionManagement

//8) 지정된 패키지의 DAO 인터페이스에 대해 구현체를 자동으로 생성한다.
@MapperScan("com.eomcs.pms.dao")

public class AppConfig {

  // 수동으로 생성할 객체가 있다면, 다음과 같이 그 객체를 만들어 리턴하는 메서드를 정의하라!
  // 단 메서드 선언에 @Bean을 붙여서 빈 컨테이너에게 이 메서드를 호출하라고 요구해야 한다.
  // 그리고 이 메서드의 리턴 값을 컨테이너에 보관해 두라고 요구해야 한다.

  // 5) DB 커넥션풀 객체 생성
  // => DB 커넥션을 생성한 후 내부 버퍼에 보관해 둔다.
  // => 요청할 때 빌려주고, 사용 후 반납 받는다.
  // => 그래서 DB 커넥션을 매번 생성하지 않게 한다.
  @Bean
  public DataSource dataSource(
      @Value("${jdbc.driver}") String jdbcDriver,
      @Value("${jdbc.url}") String jdbcUrl,
      @Value("${jdbc.username}") String jdbcUsername,
      @Value("${jdbc.password}") String jdbcPassword) {


    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName(jdbcDriver);
    ds.setUrl(jdbcUrl);
    ds.setUsername(jdbcUsername);
    ds.setPassword(jdbcPassword);
    return ds;
  }

  // 6) 트랜잭션 관리자 생성
  // => commit/rollback 을 다룬다.
  @Bean 
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  // 7) SqlSessionFactory 객체 생성
  @Bean
  public SqlSessionFactory sqlSessionFactory(
      DataSource dataSource, // DB 커넥션풀
      ApplicationContext appCtx // Spring IoC 컨테이너
      ) throws Exception {

    // Log4J2 기능 활성화시키기
    // => 로그 출력 형식은 .properties 파일이나 .xml 파일로 설정한다.
    LogFactory.useLog4J2Logging();

    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);

    // mybatis 설정 파일을 XML 파일로 따로 두지 말고,
    // 다음과 같이 자바 코드로 설정하면 편하다.
    // 
    sqlSessionFactoryBean.setTypeAliasesPackage("com.eomcs.pms.domain");
    sqlSessionFactoryBean.setMapperLocations(
        appCtx.getResources("classpath:com/eomcs/pms/dao/*Dao.xml"));
    return sqlSessionFactoryBean.getObject();
  }

  @Bean
  public MultipartResolver multipartResolver() {
    // 스프링 WebMVC에서 multipart/form-data 콘텐트를 요청 핸들러의 파라미터로 받고 싶다면,
    // MultipartResolver 구현체를 스프링 빈 컨테이너에 등록해야 한다.
    // 그래야 request handler는 프론트 컨트롤러로부터 MultipartFile/Part 객체를 받을 수 있다.
    // MultipartResolver 객체를 등록하지 않는다면,
    // HttpServletRequest를 이용하여 직접 업로드 파일의 콘텐트를 꺼내야 한다.
    // 즉 편리하게 파라미터로 받을 수 없다.
    //
    return new StandardServletMultipartResolver(); // Servlet 3.0 API의 멀티파트 처리 기능을 사용할 경우
  }

  @Bean
  public ViewResolver viewResolver() {
    // 기존의 기본 ViewResolver를 이 메서드가 리턴하는 객체로 대체한다.
    // - 요청핸들러가 리턴한 jsp 이름을 가지고 앞뒤로 경로를 붙여서 찾는다.
    InternalResourceViewResolver vr = new InternalResourceViewResolver();
    vr.setViewClass(JstlView.class); // JSTL을 처리해줄 클래스 지정
    vr.setPrefix("/WEB-INF/jsp/");
    vr.setSuffix(".jsp");
    return vr;
  }

}
