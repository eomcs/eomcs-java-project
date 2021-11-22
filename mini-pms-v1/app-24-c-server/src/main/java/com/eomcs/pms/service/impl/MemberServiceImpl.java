package com.eomcs.pms.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

  @Autowired MemberDao memberDao;

  @Transactional // 메서드 실행할 때 오류가 발생하지 않으면 자동으로 commit 한다.
  @Override
  public void add(Member member) throws Exception {
    memberDao.insert(member);
  }

  @Override
  public List<Member> list() throws Exception {
    return memberDao.findAll();
  }

  @Override
  public Member get(int no) throws Exception {
    return memberDao.findByNo(no);
  }

  @Override
  public Member get(String name) throws Exception {
    return memberDao.findByName(name);
  }

  @Override
  public boolean isDuplicated(String email) throws Exception {
    return memberDao.findByEmail(email) != null;
  }

  @Override
  public int exist(String email, String password) throws Exception {
    Member member = memberDao.findByEmailAndPassword(email, password);
    if (member == null) {
      return -1;
    }
    return member.getNo();
  }

  @Transactional
  @Override
  public void update(Member member) throws Exception {
    memberDao.update(member);
  }

  @Transactional
  @Override
  public void remove(int no) throws Exception {
    memberDao.delete(no);
  }
}






