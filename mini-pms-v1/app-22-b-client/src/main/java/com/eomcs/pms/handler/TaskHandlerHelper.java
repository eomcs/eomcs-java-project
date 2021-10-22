package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.dao.TaskDao;
import com.eomcs.pms.domain.TaskStatus;
import com.eomcs.util.Prompt;

public class TaskHandlerHelper {

  TaskDao taskDao;

  public TaskHandlerHelper(TaskDao taskDao) {
    this.taskDao = taskDao;
  }

  public TaskStatus promptStatus() throws Exception {
    return promptStatus(-1);
  }

  public TaskStatus promptStatus(int statusNo) throws Exception {

    List<TaskStatus> statusList = taskDao.findAllStatus();

    System.out.println("상태?");

    while (true) {
      for (int i = 0; i < statusList.size(); i++) {
        TaskStatus taskStatus = statusList.get(i);
        System.out.printf("%d: %s %s\n", 
            i, 
            taskStatus.getTitle(),
            taskStatus.getNo() == statusNo ? "<--- 현재 값" : "");
      }
      int no = Prompt.inputInt("> ");
      if (no >= 0 && no < statusList.size()) {
        return statusList.get(no);
      }
      System.out.println("상태 번호가 유효하지 않습니다.");
    }
  }
}





