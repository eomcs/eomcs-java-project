package com.eomcs.pms.handler;

import java.util.HashMap;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.util.Prompt;

public class MemberUpdateHandler implements Command {

  MemberDao memberDao;

  public MemberUpdateHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[회원 변경]");
    int no = (int) request.getAttribute("no");

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    Member member = memberDao.findByNo(no);

    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    String name = Prompt.inputString("이름(" + member.getName()  + ")? ");
    String email = Prompt.inputString("이메일(" + member.getEmail() + ")? ");
    String password = Prompt.inputString("암호? ");
    String photo = Prompt.inputString("사진(" + member.getPhoto() + ")? ");
    String tel = Prompt.inputString("전화(" + member.getTel() + ")? ");

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 변경을 취소하였습니다.");
      return;
    }

    member.setName(name);
    member.setEmail(email);
    member.setPassword(password);
    member.setPhoto(photo);
    member.setTel(tel);
    memberDao.update(member);

    System.out.println("회원을 변경하였습니다.");
  }
}







