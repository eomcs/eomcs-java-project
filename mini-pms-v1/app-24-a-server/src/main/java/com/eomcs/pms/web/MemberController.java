package com.eomcs.pms.web;

import java.util.Collection;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Member;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
public class MemberController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired MemberDao memberDao;
  @Autowired ServletContext sc;

  @GetMapping("/member/form")
  public ModelAndView form() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "새회원");
    mv.addObject("contentUrl", "member/MemberForm.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @PostMapping("/member/add")
  public ModelAndView add(Member member, Part photoFile) throws Exception {
    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      photoFile.write(sc.getRealPath("/upload/member") + "/" + filename);
      member.setPhoto(filename);

      Thumbnails.of(sc.getRealPath("/upload/member") + "/" + filename)
      .size(20, 20)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_20x20";
        }
      });

      Thumbnails.of(sc.getRealPath("/upload/member") + "/" + filename)
      .size(100, 100)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_100x100";
        }
      });
    }

    memberDao.insert(member);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.addObject("refresh", "2;url=list");
    mv.addObject("pageTitle", "회원등록");
    mv.addObject("contentUrl", "member/MemberAdd.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @GetMapping("/member/list")
  public ModelAndView list() throws Exception {

    Collection<Member> memberList = memberDao.findAll();

    ModelAndView mv = new ModelAndView();
    mv.addObject("memberList", memberList);
    mv.addObject("pageTitle", "회원목록");
    mv.addObject("contentUrl", "member/MemberList.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @GetMapping("/member/detail")
  public ModelAndView detail(int no) throws Exception {
    Member member = memberDao.findByNo(no);
    if (member == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("member", member);
    mv.addObject("pageTitle", "회원정보");
    mv.addObject("contentUrl", "member/MemberDetail.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @PostMapping("/member/update")
  public ModelAndView update(Member member, Part photoFile) throws Exception {

    Member oldMember = memberDao.findByNo(member.getNo());
    if (oldMember == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    } 

    member.setPhoto(oldMember.getPhoto());
    member.setRegisteredDate(oldMember.getRegisteredDate());

    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      photoFile.write(sc.getRealPath("/upload/member") + "/" + filename);
      member.setPhoto(filename);

      Thumbnails.of(sc.getRealPath("/upload/member") + "/" + filename)
      .size(20, 20)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_20x20";
        }
      });

      Thumbnails.of(sc.getRealPath("/upload/member") + "/" + filename)
      .size(100, 100)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_100x100";
        }
      });

      member.setPhoto(filename);
    }

    memberDao.update(member);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }

  @GetMapping("/member/delete")
  public ModelAndView delete(int no) throws Exception {
    Member member = memberDao.findByNo(no);
    if (member == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }

    memberDao.delete(no);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }
}







