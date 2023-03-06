package com.example.importassistant.service.impls;

import com.example.importassistant.dao.EmployeeDao;
import com.example.importassistant.entity.EmployeeEntity;
import com.example.importassistant.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  private EmployeeDao employeeDao;

  @Override
  public boolean validate(String account, String password) {

    return false;
  }

}
