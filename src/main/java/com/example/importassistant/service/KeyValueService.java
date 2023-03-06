package com.example.importassistant.service;

import com.example.importassistant.entity.KeyValueEntity;

import java.util.List;

public interface KeyValueService {

  List<KeyValueEntity> listKeyValueItems(String busName, Integer key);
}
