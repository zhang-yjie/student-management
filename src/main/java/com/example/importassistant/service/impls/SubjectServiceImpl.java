package com.example.importassistant.service.impls;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.importassistant.dao.SubjectDao;
import com.example.importassistant.entity.SubjectEntity;
import com.example.importassistant.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class SubjectServiceImpl implements SubjectService {

  @Autowired
  private SubjectDao subjectDao;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public SubjectEntity save(SubjectEntity subjectEntity) throws Exception {
    int success = 0;
    if(!StringUtils.hasLength(subjectEntity.getSubjectName()) || !StringUtils.hasLength(subjectEntity.getStudentId())){
      return subjectEntity;
    }
    if (StringUtils.hasLength(subjectEntity.getSno()) && !Objects.equals(subjectEntity.getSno().trim(), "null")) {
      UpdateWrapper<SubjectEntity> updateWrapper = new UpdateWrapper<>();
      updateWrapper.eq("sno", subjectEntity.getSno());
      success = subjectDao.update(subjectEntity, updateWrapper);
    }else {
      success = subjectDao.insert(subjectEntity);
    }
    if(success != 1){
      throw new Exception("保存失败");
    }
    return subjectEntity;
  }
}
