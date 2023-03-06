package com.example.importassistant.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class ListStudConditionsDTO implements Serializable {

  private static final long serialVersionUID = 1L;
  private String className;
  private String subjectName;
  private String studentName;
  private Integer courseStatus;
  private Integer currentPage = 1;
  private Integer pageSize = 10;
}
