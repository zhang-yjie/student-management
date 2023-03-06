package com.example.importassistant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@TableName("KEY_VALUE_ITEMS")
@EqualsAndHashCode
@ToString
public class KeyValueEntity {

  @TableId(type = IdType.ASSIGN_UUID)
  private String id;
  private Integer itemKey;
  private String itemValue;
  private String busName;
  private String creater;
  private String createrId;
  private String createTime;
  private String modifier;
  private String modifierId;
  private String modifyTime;

}
