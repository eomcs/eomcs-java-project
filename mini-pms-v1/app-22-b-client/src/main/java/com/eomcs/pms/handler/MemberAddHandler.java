package com.eomcs.pms.handler;

import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.util.Prompt;

public class MemberAddHandler implements Command {

  MemberDao memberDao;
  SqlSession sqlSession;

  public MemberAddHandler(MemberDao memberDao, SqlSession sqlSession) {
    this.memberDao = memberDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[회원 등록]");

    Member member = new Member();

    member.setName(Prompt.inputString("이름? "));
    member.setEmail(Prompt.inputString("이메일? "));
    member.setPassword(Prompt.inputString("암호? "));
    member.setPhoto(Prompt.inputString("사진? "));
    member.setTel(Prompt.inputString("전화? "));

    memberDao.insert(member);
    sqlSession.commit();

    System.out.println("회원을 등록했습니다.");
  }
}







