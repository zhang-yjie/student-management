package com.example.importassistant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@TableName("EMPLOYEE")
@EqualsAndHashCode
@ToString
public class EmployeeEntity {

  @TableId(type = IdType.ASSIGN_UUID)
  private String id;
  private String empName;
  private Integer sex;
  private String login;
  private String loginPwd;
  private String department;
  private String remark;
  private String creater;
  private String createrId;
  private String createTime;
  private String modifier;
  private String modifierId;
  private String modifyTime;

}
