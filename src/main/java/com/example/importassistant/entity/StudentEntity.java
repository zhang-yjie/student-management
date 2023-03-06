package com.example.importassistant.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ToString
@TableName(value = "S_STUDENT", autoResultMap = true)
public class StudentEntity {

  @TableId(type = IdType.ASSIGN_UUID)
  private String id;
  private String studentName;
  private Integer sex;
  private Integer age;
  private String hobby;
  private String parent;
  private String cellphone;
  private String kindergarten;
  private String address;
  private String studentRemark;
  private String creator;
  private Date createTime;
  private String modifier;
  @OrderBy
  private Date modifyTime;
  @Version
  private Integer version;

  @TableField(exist = false)
  private String sno;

  @TableField(exist = false)
  private String className;

  @TableField(exist = false)
  private String subjectName;

  @TableField(exist = false)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date beginDate;

  @TableField(exist = false)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date endDate;

  @TableField(exist = false)
  private Integer courseStatus;

}
