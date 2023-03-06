package com.example.importassistant.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.importassistant.dto.ListStudConditionsDTO;
import com.example.importassistant.entity.StudentEntity;

import java.sql.Wrapper;
import java.util.List;
import java.util.Map;

public interface StudentService {

  StudentEntity getStudentById(String id);
  List<StudentEntity> listStudentsByConditions(Map<String, Object> conditions);
  Map<String, Object> getStudentInfo(String studentId, String subjectSno);
  Page<StudentEntity> pageStudentsByConditions(ListStudConditionsDTO conditions);
  StudentEntity save(StudentEntity studentEntity) throws Exception;

}
