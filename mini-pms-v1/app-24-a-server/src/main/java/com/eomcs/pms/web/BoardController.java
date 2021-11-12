package com.eomcs.pms.web;

import java.util.Collection;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Member;

@Controller
public class BoardController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired BoardDao boardDao;

  @GetMapping("/board/form")
  public ModelAndView form() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "새 글");
    mv.addObject("contentUrl", "board/BoardForm.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @PostMapping("/board/add")
  public ModelAndView add(Board board, HttpSession session) throws Exception {

    board.setWriter((Member) session.getAttribute("loginUser"));

    boardDao.insert(board);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }

  @GetMapping("/board/list")
  public ModelAndView list() throws Exception {
    Collection<Board> boardList = boardDao.findAll();

    ModelAndView mv = new ModelAndView();
    mv.addObject("boardList", boardList);
    mv.addObject("pageTitle", "게시글목록");
    mv.addObject("contentUrl", "board/BoardList.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @GetMapping("/board/detail")
  public ModelAndView detail(int no) throws Exception {
    Board board = boardDao.findByNo(no);

    if (board == null) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }

    boardDao.updateCount(no);

    ModelAndView mv = new ModelAndView();
    mv.addObject("board", board);
    mv.addObject("pageTitle", "게시글");
    mv.addObject("contentUrl", "board/BoardDetail.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @PostMapping("/board/update")
  public ModelAndView update(Board board) throws Exception {

    Board oldBoard = boardDao.findByNo(board.getNo());
    if (oldBoard == null) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    } 

    boardDao.update(board);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }

  @GetMapping("/board/delete")
  public ModelAndView delete(int no) throws Exception {

    Board board = boardDao.findByNo(no);
    if (board == null) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }

    boardDao.delete(no);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }
}







