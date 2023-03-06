package com.example.importassistant.service.impls;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.importassistant.dao.KeyValueDao;
import com.example.importassistant.entity.KeyValueEntity;
import com.example.importassistant.service.KeyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class KeyValueServiceImpl implements KeyValueService {

  @Autowired
  private KeyValueDao keyValueDao;

  @Override
  public List<KeyValueEntity> listKeyValueItems(String busName, Integer key) {
    LambdaQueryWrapper<KeyValueEntity> wrapper = new QueryWrapper<KeyValueEntity>().lambda();
    wrapper.eq(StringUtils.hasLength(busName), KeyValueEntity::getBusName, busName)
    .eq(key != null, KeyValueEntity::getItemKey, key);
    return keyValueDao.listItems(wrapper);
  }
}
