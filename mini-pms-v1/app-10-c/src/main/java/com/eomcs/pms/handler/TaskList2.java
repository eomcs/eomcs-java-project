package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Task;

public class TaskList2 extends LinkedList {

  public Task findByNo(int no) {
    Object[] list = toArray();
    for (Object obj : list) {
      Task task = (Task) obj;
      if (task.no == no) {
        return task;
      }
    }
    return null;
  }

}








