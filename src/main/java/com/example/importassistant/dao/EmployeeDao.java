package com.example.importassistant.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.importassistant.entity.EmployeeEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends BaseMapper<EmployeeEntity> {
}
