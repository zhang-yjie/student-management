package com.example.importassistant.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode
@ToString
@TableName("S_SUBJECT")
public class SubjectEntity {

  @TableId(type = IdType.ASSIGN_UUID)
  private String sno;
  private String studentId;
  private String subjectName;
  private String className;
  private Float remainHours;
  private Float totalHours;
  private Integer courseStatus;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date beginDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date endDate;
  private Integer points;
  private String level;
  private String subjectRemark;
  private String creator;
  private Date createTime;
  private String modifier;
  private Date modifyTime;
  @Version
  private Integer version;

}
