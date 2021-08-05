package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Task;

public class TaskList extends ArrayList {

  public Task findByNo(int no) {
    Object[] arr = toArray();
    for (Object obj : arr) {
      Task task = (Task) obj;
      if (task.no == no) {
        return task;
      }
    }
    return null;
  }

}








