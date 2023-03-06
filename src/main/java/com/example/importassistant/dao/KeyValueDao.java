package com.example.importassistant.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.importassistant.entity.KeyValueEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeyValueDao extends BaseMapper<KeyValueEntity> {

  @Select(value = "SELECT ID, ITEM_KEY, ITEM_VALUE, BUS_NAME FROM KEY_VALUE_ITEMS ${ew.customSqlSegment}")
  List<KeyValueEntity> listItems(@Param(Constants.WRAPPER) Wrapper<KeyValueEntity> wrapper);
}
