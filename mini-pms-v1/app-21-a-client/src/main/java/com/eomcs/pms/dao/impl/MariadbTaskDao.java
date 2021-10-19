package com.eomcs.pms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.pms.dao.TaskDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Task;
import com.eomcs.pms.domain.TaskStatus;

// 역할
// - 작업 데이터를 DBMS 서버를 통해 관리한다.
//
public class MariadbTaskDao implements TaskDao {

  Connection con;

  public MariadbTaskDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Task task) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into pms_task(project_no,member_no,content,deadline,task_status_no) values(?,?,?,?,?)")) {

      stmt.setInt(1, task.getProjectNo());
      stmt.setInt(2, task.getOwner().getNo());
      stmt.setString(3, task.getContent());
      stmt.setDate(4, task.getDeadline());
      stmt.setInt(5, task.getStatus().getNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("작업 데이터 입력 실패!");
      }
    }
  }

  @Override
  public List<Task> findAll(int projectNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " t.task_no,"
            + " t.project_no,"
            + " t.content,"
            + " t.deadline,"
            + " m.member_no owner_no,"
            + " m.name owner_name,"
            + " ts.task_status_no status_no,"
            + " ts.title status_title"
            + " from"
            + " pms_task t"
            + " inner join pms_member m on t.member_no=m.member_no"
            + " inner join pms_task_status ts on t.task_status_no=ts.task_status_no"
            + " where"
            + " t.project_no=" + projectNo);
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<Task> list = new ArrayList<>();

      while (rs.next()) {
        Task task = new Task();
        task.setNo(rs.getInt("task_no"));
        task.setProjectNo(rs.getInt("project_no"));
        task.setContent(rs.getString("content"));
        task.setDeadline(rs.getDate("deadline"));

        TaskStatus taskStatus = new TaskStatus();
        taskStatus.setNo(rs.getInt("status_no"));
        taskStatus.setTitle(rs.getString("status_title"));

        task.setStatus(taskStatus);

        Member member = new Member();
        member.setNo(rs.getInt("owner_no"));
        member.setName(rs.getString("owner_name"));
        task.setOwner(member);

        list.add(task);
      }

      return list;
    }
  }

  @Override
  public Task findByNo(int taskNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " t.task_no,"
            + " t.project_no,"
            + " t.content,"
            + " t.deadline,"
            + " m.member_no owner_no,"
            + " m.name owner_name,"
            + " ts.task_status_no status_no,"
            + " ts.title status_title"
            + " from"
            + " pms_task t"
            + " inner join pms_member m on t.member_no=m.member_no"
            + " inner join pms_task_status ts on t.task_status_no=ts.task_status_no"
            + " where"
            + " t.task_no=" + taskNo);
        ResultSet rs = stmt.executeQuery()) {

      if (rs.next()) {
        Task task = new Task();
        task.setNo(rs.getInt("task_no"));
        task.setProjectNo(rs.getInt("project_no"));
        task.setContent(rs.getString("content"));
        task.setDeadline(rs.getDate("deadline"));

        TaskStatus taskStatus = new TaskStatus();
        taskStatus.setNo(rs.getInt("status_no"));
        taskStatus.setTitle(rs.getString("status_title"));

        task.setStatus(taskStatus);

        Member member = new Member();
        member.setNo(rs.getInt("owner_no"));
        member.setName(rs.getString("owner_name"));
        task.setOwner(member);

        return task;
      }

      return null;
    }
  }

  @Override
  public void update(Task task) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update pms_task set"
            + " content=?,"
            + " deadline=?,"
            + " task_status_no=?,"
            + " member_no=?"
            + " where task_no=?")) {

      stmt.setString(1, task.getContent());
      stmt.setDate(2, task.getDeadline());
      stmt.setInt(3, task.getStatus().getNo());
      stmt.setInt(4, task.getOwner().getNo());
      stmt.setInt(5, task.getNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("작업 데이터 변경 실패!");
      }
    }
  }


  @Override
  public void delete(int taskNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from pms_task where task_no=?")) {

      stmt.setInt(1, taskNo);

      if (stmt.executeUpdate() == 0) {
        throw new Exception("프로젝트 데이터 삭제 실패!");
      }
    }
  }

  @Override
  public List<TaskStatus> findAllStatus() throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select task_status_no, title from pms_task_status order by task_status_no asc");
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<TaskStatus> list = new ArrayList<>();

      while (rs.next()) {
        TaskStatus taskStatus = new TaskStatus();
        taskStatus.setNo(rs.getInt("task_status_no"));
        taskStatus.setTitle(rs.getString("title"));

        list.add(taskStatus);
      }
      return list;
    }
  }
}



