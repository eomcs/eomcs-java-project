package com.eomcs.pms.dao;

import java.util.List;
import com.eomcs.pms.domain.Task;
import com.eomcs.pms.domain.TaskStatus;

// 역할
// - 작업 데이터를 처리하는 객체 사용법을 정의한다.
public interface TaskDao {
  void insert(Task task) throws Exception;
  List<Task> findAll(int projectNo) throws Exception;
  Task findByNo(int taskNo) throws Exception;
  void delete(int taskNo) throws Exception;
  void update(Task task) throws Exception;
  List<TaskStatus> findAllStatus() throws Exception;
}
