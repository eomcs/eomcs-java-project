package com.eomcs.pms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.pms.dao.ProjectDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Project;

// 역할
// - 프로젝트 데이터를 DBMS 서버를 통해 관리한다.
//
public class MariadbProjectDao implements ProjectDao {

  Connection con;

  public MariadbProjectDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Project project) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into pms_project(title,content,start_dt,end_dt,member_no) values(?,?,?,?,?)",
        Statement.RETURN_GENERATED_KEYS)) {

      stmt.setString(1, project.getTitle());
      stmt.setString(2, project.getContent());
      stmt.setDate(3, project.getStartDate());
      stmt.setDate(4, project.getEndDate());
      stmt.setInt(5, project.getOwner().getNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("프로젝트 데이터 입력 실패!");
      }

      // 입력된 프로젝트의 PK 값 꺼내기
      int projectNo = 0;
      try (ResultSet pkRS = stmt.getGeneratedKeys()) {
        if (pkRS.next()) {
          projectNo = pkRS.getInt("project_no");
        }
      }

      // 프로젝트의 멤버를 입력하기
      try (PreparedStatement stmt2 = con.prepareStatement(
          "insert into pms_project_member(project_no,member_no) values(?,?)")) {
        for (Member member : project.getMembers()) {
          stmt2.setInt(1, projectNo);
          stmt2.setInt(2, member.getNo());
          stmt2.executeUpdate();
        }
      }
    }
  }

  // 방법1: 프로젝트 목록을 가져올 때 멤버 목록도 함께 가져오기
  @Override
  public List<Project> findAll() throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " p.project_no,"
            + " p.title,"
            + " p.start_dt,"
            + " p.end_dt,"
            + " m.member_no owner_no,"
            + " m.name owner_name,"
            + " m.email owner_email,"
            + " m2.member_no member_no,"
            + " m2.name member_name,"
            + " m2.email member_email"
            + " from" 
            + " pms_project p"
            + " inner join pms_member m on p.member_no=m.member_no"
            + " left outer join pms_project_member pm on p.project_no=pm.project_no"
            + " inner join pms_member m2 on pm.member_no=m2.member_no"
            + " order by" 
            + " project_no desc, m2.name asc");
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<Project> list = new ArrayList<>();

      int projectNo = 0;
      Project project = null;

      while (rs.next()) {
        if (projectNo != rs.getInt("project_no")) {
          project = new Project();
          project.setNo(rs.getInt("project_no"));
          project.setTitle(rs.getString("title"));
          project.setStartDate(rs.getDate("start_dt"));
          project.setEndDate(rs.getDate("end_dt"));

          Member owner = new Member();
          owner.setNo(rs.getInt("owner_no"));
          owner.setName(rs.getString("owner_name"));
          owner.setEmail(rs.getString("owner_email"));

          project.setOwner(owner);

          list.add(project);
          projectNo = project.getNo();
        }

        // 프로젝트의 멤버가 있다면 기존 멤버 목록에 추가한다.
        if (rs.getString("member_name") != null) {
          Member member = new Member();
          member.setNo(rs.getInt("member_no"));
          member.setName(rs.getString("member_name"));
          member.setEmail(rs.getString("member_email"));
          project.getMembers().add(member);
        }
      }

      return list;
    }
  }

  // 방법2: 프로젝트 목록을 가져온 후 각 프로젝트에 대해 멤버 목록을 가져오기
  //  @Override
  //  public List<Project> findAll() throws Exception {
  //    try (PreparedStatement stmt = con.prepareStatement(
  //        "select" 
  //            + " p.project_no,"
  //            + " p.title,"
  //            + " p.start_dt,"
  //            + " p.end_dt,"
  //            + " m.member_no,"
  //            + " m.name,"
  //            + " m.email"
  //            + " from pms_project p"
  //            + " inner join pms_member m on p.member_no=m.member_no"
  //            + " order by project_no desc");
  //        PreparedStatement stmt2 = con.prepareStatement(
  //            "select" 
  //                + " pm.project_no, "
  //                + " m.member_no,"
  //                + " m.name,"
  //                + " m.email"
  //                + " from "
  //                + " pms_project_member pm"
  //                + " inner join pms_member m on pm.member_no=m.member_no"
  //                + " where"
  //                + " pm.project_no=?"
  //                + " order by "
  //                + " m.name asc");
  //        ResultSet rs = stmt.executeQuery()) {
  //
  //      ArrayList<Project> list = new ArrayList<>();
  //
  //      while (rs.next()) {
  //        Project project = new Project();
  //        project.setNo(rs.getInt("project_no"));
  //        project.setTitle(rs.getString("title"));
  //        project.setStartDate(rs.getDate("start_dt"));
  //        project.setEndDate(rs.getDate("end_dt"));
  //
  //        Member owner = new Member();
  //        owner.setNo(rs.getInt("member_no"));
  //        owner.setName(rs.getString("name"));
  //        owner.setEmail(rs.getString("email"));
  //
  //        project.setOwner(owner);
  //
  //        // 프로젝트의 멤버 목록 가져오기
  //        stmt2.setInt(1, project.getNo());
  //        try (ResultSet memberRs = stmt2.executeQuery()) {
  //          while (memberRs.next()) {
  //            Member member = new Member();
  //            member.setNo(memberRs.getInt("member_no"));
  //            member.setName(memberRs.getString("name"));
  //            member.setEmail(memberRs.getString("email"));
  //
  //            project.getMembers().add(member);
  //          }
  //        }
  //
  //        list.add(project);
  //      }
  //
  //      return list;
  //    }
  //  }

  @Override
  public Project findByNo(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " p.project_no,"
            + " p.title,"
            + " p.content,"
            + " p.start_dt,"
            + " p.end_dt,"
            + " m.member_no owner_no,"
            + " m.name owner_name,"
            + " m.email owner_email,"
            + " m2.member_no member_no,"
            + " m2.name member_name,"
            + " m2.email member_email"
            + " from" 
            + " pms_project p"
            + " inner join pms_member m on p.member_no=m.member_no"
            + " left outer join pms_project_member pm on p.project_no=pm.project_no"
            + " inner join pms_member m2 on pm.member_no=m2.member_no"
            + " where p.project_no=" + no
            + " order by m2.name asc");
        ResultSet rs = stmt.executeQuery()) {

      Project project = null;

      while (rs.next()) {
        if (project == null) {
          project = new Project();
          project.setNo(rs.getInt("project_no"));
          project.setTitle(rs.getString("title"));
          project.setContent(rs.getString("content"));
          project.setStartDate(rs.getDate("start_dt"));
          project.setEndDate(rs.getDate("end_dt"));

          Member owner = new Member();
          owner.setNo(rs.getInt("owner_no"));
          owner.setName(rs.getString("owner_name"));
          owner.setEmail(rs.getString("owner_email"));

          project.setOwner(owner);
        }

        // 프로젝트의 멤버가 있다면 기존 멤버 목록에 추가한다.
        if (rs.getString("member_name") != null) {
          Member member = new Member();
          member.setNo(rs.getInt("member_no"));
          member.setName(rs.getString("member_name"));
          member.setEmail(rs.getString("member_email"));
          project.getMembers().add(member);
        }
      }

      return project;
    }
  }

  @Override
  public void update(Project project) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update pms_project set"
            + " title=?,"
            + " content=?,"
            + " start_dt=?,"
            + " end_dt=?"
            + " where project_no=?")) {

      stmt.setString(1, project.getTitle());
      stmt.setString(2, project.getContent());
      stmt.setDate(3, project.getStartDate());
      stmt.setDate(4, project.getEndDate());
      stmt.setInt(5, project.getNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("프로젝트 데이터 변경 실패!");
      }

      // 프로젝트 멤버 변경
      // => 기존 멤버 모두 제거
      try (PreparedStatement stmt2 = con.prepareStatement(
          "delete from pms_project_member where project_no=?")) {
        stmt2.setInt(1, project.getNo());
        stmt2.executeUpdate();
      }

      // => 프로젝트 새 멤버 입력
      try (PreparedStatement stmt2 = con.prepareStatement(
          "insert into pms_project_member(project_no,member_no) values(?,?)")) {
        for (Member member : project.getMembers()) {
          stmt2.setInt(1, project.getNo());
          stmt2.setInt(2, member.getNo());
          stmt2.executeUpdate();
        }
      }
    }
  }

  @Override
  public void delete(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from pms_project where project_no=?");
        PreparedStatement stmt2 = con.prepareStatement(
            "delete from pms_project_member where project_no=?")) {

      // 프로젝트 멤버를 먼제 삭제한다.
      stmt2.setInt(1, no);
      stmt2.executeUpdate();

      // 프로젝트를 삭제한다.
      stmt.setInt(1, no);
      if (stmt.executeUpdate() == 0) {
        throw new Exception("프로젝트 데이터 삭제 실패!");
      }
    }
  }
}



