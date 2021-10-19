package com.eomcs.pms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Member;

// 역할
// - 게시글을 DBMS 서버를 통해 관리한다.
//
public class MariadbBoardDao implements BoardDao {

  Connection con;

  public MariadbBoardDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Board board) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into pms_board(title,content,member_no) values(?,?,?)")) {

      stmt.setString(1, board.getTitle());
      stmt.setString(2, board.getContent());
      stmt.setInt(3, board.getWriter().getNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("게시글 데이터 입력 실패!");
      }
    }
  }

  @Override
  public List<Board> findAll() throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select "
            + " b.board_no,"
            + " b.title,"
            + " b.created_dt,"
            + " b.view_cnt,"
            + " m.member_no,"
            + " m.name,"
            + " m.email"
            + " from "
            + " pms_board b"
            + " inner join pms_member m on b.member_no=m.member_no"
            + " order by b.board_no desc");
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<Board> list = new ArrayList<>();

      while (rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("board_no"));
        board.setTitle(rs.getString("title"));
        board.setRegisteredDate(rs.getDate("created_dt"));
        board.setViewCount(rs.getInt("view_cnt"));

        Member member = new Member();
        member.setNo(rs.getInt("member_no"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));

        board.setWriter(member);

        list.add(board);
      }

      return list;
    }
  }

  @Override
  public List<Board> findByKeyword(String keyword) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select "
            + " b.board_no,"
            + " b.title,"
            + " b.created_dt,"
            + " b.view_cnt,"
            + " m.member_no,"
            + " m.name,"
            + " m.email"
            + " from "
            + " pms_board b"
            + " inner join pms_member m on b.member_no=m.member_no"
            + " where b.title like(concat('%',?,'%'))"
            + " or b.content like (concat('%',?,'%'))"
            + " or m.name like (concat('%',?,'%'))"
            + " order by b.board_no desc")) {

      stmt.setString(1, keyword);
      stmt.setString(2, keyword);
      stmt.setString(3, keyword);

      try (ResultSet rs = stmt.executeQuery()) {

        ArrayList<Board> list = new ArrayList<>();

        while (rs.next()) {
          Board board = new Board();
          board.setNo(rs.getInt("board_no"));
          board.setTitle(rs.getString("title"));
          board.setRegisteredDate(rs.getDate("created_dt"));
          board.setViewCount(rs.getInt("view_cnt"));

          Member member = new Member();
          member.setNo(rs.getInt("member_no"));
          member.setName(rs.getString("name"));
          member.setEmail(rs.getString("email"));

          board.setWriter(member);

          list.add(board);
        }

        return list;
      }
    }
  }

  @Override
  public Board findByNo(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select "
            + " b.board_no,"
            + " b.title,"
            + " b.content,"
            + " b.created_dt,"
            + " b.view_cnt,"
            + " m.member_no,"
            + " m.name,"
            + " m.email"
            + " from "
            + " pms_board b"
            + " inner join pms_member m on b.member_no=m.member_no"
            + " where board_no=" + no);
        ResultSet rs = stmt.executeQuery()) {

      if (rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("board_no"));
        board.setTitle(rs.getString("title"));
        board.setContent(rs.getString("content"));
        board.setRegisteredDate(rs.getDate("created_dt"));
        board.setViewCount(rs.getInt("view_cnt"));

        Member member = new Member();
        member.setNo(rs.getInt("member_no"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));

        board.setWriter(member);

        // 조회수 증가하기
        try (PreparedStatement stmt2 = con.prepareStatement(
            "update pms_board set view_cnt=view_cnt + 1 where board_no=" + no)) {
          stmt2.executeUpdate();
        }

        return board;
      }

      return null;
    }
  }

  @Override
  public void update(Board board) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update pms_board set"
            + " title=?,content=?"
            + " where board_no=?")) {

      stmt.setString(1, board.getTitle());
      stmt.setString(2, board.getContent());
      stmt.setInt(3, board.getNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("게시글 데이터 변경 실패!");
      }
    }
  }

  @Override
  public void delete(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from pms_board where board_no=?")) {

      stmt.setInt(1, no);

      if (stmt.executeUpdate() == 0) {
        throw new Exception("게시글 데이터 삭제 실패!");
      }
    }
  }
}



