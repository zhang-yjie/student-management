package com.example.importassistant.controller;

import com.example.importassistant.annotations.ApiController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@ApiController("/common/")
public class HomeController {

  @GetMapping("/home")
  public String home(Model model){
    Map<String, String> params = new HashMap<>();
    params.put("title", "课时管理-主页");
    params.put("html", null);
    params.put("content", null);
    params.put("type", "home");
    params.put("script", null);
    model.addAttribute("params", params);
    return "index";
  }

}
