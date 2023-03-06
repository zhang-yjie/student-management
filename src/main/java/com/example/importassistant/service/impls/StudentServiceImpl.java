package com.example.importassistant.service.impls;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.importassistant.dao.StudentDao;
import com.example.importassistant.dao.SubjectDao;
import com.example.importassistant.dto.ListStudConditionsDTO;
import com.example.importassistant.entity.StudentEntity;
import com.example.importassistant.entity.SubjectEntity;
import com.example.importassistant.service.StudentService;
import com.example.importassistant.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  private StudentDao studentDao;

  @Autowired
  private SubjectDao subjectDao;

  @Override
  public StudentEntity getStudentById(String id) {
//    StudentEntity studentEntity = new StudentEntity();
    return null;
  }

  @Override
  public List<StudentEntity> listStudentsByConditions(Map<String, Object> conditions) {
    return null;
  }

  @Override
  public Map<String, Object> getStudentInfo(String studentId, String subjectSno) {
    if(Objects.equals(studentId, "null")) studentId = null;
    if(Objects.equals(subjectSno, "null")) subjectSno = null;
    Map<String, Object> result = new HashMap<>();
    StudentEntity studentEntity = new StudentEntity();
    SubjectEntity subjectEntity = new SubjectEntity();
    result.put("student", studentEntity);
    result.put("subject", subjectEntity);
    if(!StringUtils.hasLength(studentId) && !StringUtils.hasLength(subjectSno)){
      return result;
    }
    if (StringUtils.hasLength(studentId)) {
      studentEntity = studentDao.selectById(studentId);
    }
    if(StringUtils.hasLength(subjectSno)){
      subjectEntity = subjectDao.selectById(subjectSno);
    }
    result.put("student", studentEntity);
    result.put("subject", subjectEntity);
    return result;
  }

  @Override
  public Page<StudentEntity> pageStudentsByConditions(ListStudConditionsDTO conditions) {

//    LambdaQueryWrapper<StudentEntity> wrapper = new QueryWrapper<StudentEntity>().lambda();
    QueryWrapper<StudentEntity> wrapper = new QueryWrapper<StudentEntity>();
    wrapper.like(StringUtils.hasLength(conditions.getStudentName()), "s.STUDENT_NAME", StringUtils.hasLength(conditions.getStudentName()) ? conditions.getStudentName().trim() : null)
            .like(StringUtils.hasLength(conditions.getClassName()), "b.CLASS_NAME", StringUtils.hasLength(conditions.getClassName()) ? conditions.getClassName().trim() : null)
            .like(StringUtils.hasLength(conditions.getSubjectName()), "b.SUBJECT_NAME", StringUtils.hasLength(conditions.getSubjectName()) ? conditions.getSubjectName().trim() : null)
            .eq(conditions.getCourseStatus() != null, "b.COURSE_STATUS", conditions.getCourseStatus());

    Page<StudentEntity> pageParams = new Page<>(conditions.getCurrentPage() == null ? 1 : conditions.getCurrentPage(),
            conditions.getPageSize() == null ? 10 : conditions.getPageSize());
    OrderItem orderItem = new OrderItem();
    orderItem.setColumn("s.MODIFY_TIME");
    orderItem.setAsc(false);
    pageParams.addOrder(orderItem);
    Page<StudentEntity> page = studentDao.listStudentsByConditions(wrapper, pageParams);
    return page;
  }

  @Override
  public StudentEntity save(StudentEntity studentEntity) throws Exception {
    int success = 0;
    if (StringUtils.hasLength(studentEntity.getId())) {
      UpdateWrapper<StudentEntity> wrapper = new UpdateWrapper<>();
      wrapper.eq("id", studentEntity.getId());
      success = studentDao.update(studentEntity, wrapper);
    }else {
      success = studentDao.insert(studentEntity);
    }
    if (success != 1) {
      throw new Exception("保存失败");
    }
    return studentEntity;
  }

}
