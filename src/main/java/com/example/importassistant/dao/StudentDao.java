package com.example.importassistant.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.importassistant.entity.StudentEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends BaseMapper<StudentEntity> {

  @ResultMap("mybatis-plus_StudentEntity")
  @Select("SELECT s.ID, s.STUDENT_NAME, s.AGE, s.CELLPHONE, s.MODIFY_TIME, b.SNO, b.CLASS_NAME, b.SUBJECT_NAME, b.BEGIN_DATE, b.END_DATE, b.COURSE_STATUS " +
          "FROM S_STUDENT s LEFT JOIN S_SUBJECT b ON s.ID = b.STUDENT_ID ${ew.customSqlSegment} ")
  Page<StudentEntity> listStudentsByConditions(@Param(Constants.WRAPPER) Wrapper<StudentEntity> wrapper, @Param("page") Page<StudentEntity> page);

}
