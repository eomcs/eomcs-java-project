package com.eomcs.pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.dao.ProjectDao;
import com.eomcs.pms.domain.Project;
import com.eomcs.pms.domain.Task;
import com.eomcs.request.RequestAgent;

// 역할
// - 게시글을 데이터를 서버를 통해 관리한다.
//
public class NetProjectDao implements ProjectDao {

  RequestAgent requestAgent;

  public NetProjectDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void insert(Project project) throws Exception {
    requestAgent.request("project.insert", project);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
  }

  @Override
  public List<Project> findAll() throws Exception {
    requestAgent.request("project.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }

    return new ArrayList<>(requestAgent.getObjects(Project.class));
  }

  @Override
  public Project findByNo(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("project.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(Project.class);
  }

  @Override
  public void update(Project project) throws Exception {
    requestAgent.request("project.update", project);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
  }

  @Override
  public void delete(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("project.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
  }

  @Override
  public void insertTask(Task task) throws Exception {
    requestAgent.request("project.task.insert", task);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
  }

  @Override
  public void deleteTask(int projectNo, int taskNo) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("taskNo", String.valueOf(taskNo));
    params.put("projectNo", String.valueOf(projectNo));

    requestAgent.request("project.task.delete", params);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
  }

  @Override
  public void updateTask(Task task) throws Exception {
    requestAgent.request("project.task.update", task);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
  }

}



