package com.example.importassistant.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.importassistant.annotations.ApiController;
import com.example.importassistant.annotations.StudentInfoModel;
import com.example.importassistant.dao.KeyValueDao;
import com.example.importassistant.dto.ListStudConditionsDTO;
import com.example.importassistant.entity.KeyValueEntity;
import com.example.importassistant.entity.StudentEntity;
import com.example.importassistant.entity.SubjectEntity;
import com.example.importassistant.service.KeyValueService;
import com.example.importassistant.service.StudentService;
import com.example.importassistant.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.importassistant.constant.KeyValueConstant.COURSE_STATUS_NAME;


@ApiController("/student")
public class StudentController {

  @Autowired
  private StudentService studentService;

  @Autowired
  private SubjectService subjectService;

  @Autowired
  private KeyValueService keyValueService;

  @GetMapping("/info")
  public String getStudentInfo(@RequestParam(name = "subjectSno") String subjectSno,
                               @RequestParam(name = "studentId") String studentId,
                               @RequestParam(name = "action") String action,
                               Model model) {
    Map<String, String> params = new HashMap<>();
    params.put("title", "课时管理-学员详情");
    params.put("html", "/student/student_info");
    params.put("content", "student_info");
    params.put("type", "student");
    params.put("script", "stud_info_script");
    params.put("action", action);
    model.addAttribute("params", params);

    Map<String, Object> studentInfo = studentService.getStudentInfo(studentId, subjectSno);
    model.addAllAttributes(studentInfo);

    return "index";
  }

  @GetMapping("/list")
  public String listStudents(ListStudConditionsDTO conditions, Model model){
    Map<String, String> params = new HashMap<>();
    params.put("title", "课时管理-学员列表");
    params.put("html", "/student/student_list");
    params.put("content", "student_list");
    params.put("type", "student");
    params.put("script", "stud_list_script");
    model.addAttribute("params", params);
    Page<StudentEntity> page = studentService.pageStudentsByConditions(conditions);
    model.addAttribute("page", page);

    List<KeyValueEntity> courseStatus = keyValueService.listKeyValueItems(COURSE_STATUS_NAME, null);
    Map<Integer, String> courseStatusMap = courseStatus.stream().collect(Collectors.toMap(item -> item.getItemKey(), item -> item.getItemValue()));
    model.addAttribute("courseStatus", courseStatusMap);
    return "index";
  }

  @GetMapping("/info/add")
  public String addStudentInfo(HttpServletRequest request) {
    Map<String, String> params = new HashMap<>();
    params.put("title", "课时管理-学员详情");
    params.put("html", "/student/student_info");
    params.put("content", "student_info");
    params.put("type", "student");
    params.put("action", "edit");
    params.put("script", "stud_info_script");
    request.setAttribute("params", params);
    if(request.getAttribute("student") == null || request.getAttribute("subject") == null){
      Map<String, Object> studentInfo = studentService.getStudentInfo(null, null);
      request.setAttribute("student", studentInfo.get("student"));
      request.setAttribute("subject", studentInfo.get("subject"));
    }
    return "index";
  }

  @ResponseBody
  @GetMapping("/query")
  public Object getStudentList(ListStudConditionsDTO conditions){
    Map<String, Object> result = new HashMap<>();
    Page<StudentEntity> page = studentService.pageStudentsByConditions(conditions);
    Map<Integer, String> courseStatusMap = keyValueService.listKeyValueItems(COURSE_STATUS_NAME, null).stream().collect(Collectors.toMap(item -> item.getItemKey(), item -> item.getItemValue()));
    result.put("page", page);
    result.put("courseStatus", courseStatusMap);
    return result;
  }

  @PostMapping("/save")
  public Object saveStudentInfo(@ModelAttribute StudentEntity studentEntity,
                                @ModelAttribute SubjectEntity subjectEntity, RedirectAttributes redirectAttributes) throws Exception {
    if(!StringUtils.hasLength(studentEntity.getStudentName())){
      redirectAttributes.addFlashAttribute("student", studentEntity);
      redirectAttributes.addFlashAttribute("subject", subjectEntity);
      redirectAttributes.addFlashAttribute("warning", "请填写姓名");
      return "redirect:/student/info/add";
    }
    StudentEntity student = studentService.save(studentEntity);
    SubjectEntity subject = subjectService.save(subjectEntity);
    redirectAttributes.addFlashAttribute("saveResult", true);
    return "redirect:/student/info?subjectSno=" + subject.getSno() + "&studentId=" + student.getId() + "&action=edit";
  }

}
