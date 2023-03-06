package com.example.importassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

  @GetMapping(value = {"/", "index"})
  public String index(Model model){
    Map<String, String> params = new HashMap<>();
    params.put("title", "课时管理系统-欢迎登录");
    model.addAttribute("params", params);
    return "/home/login";
  }

  @PostMapping("index")
  public String login(@RequestParam("account") String account, @RequestParam("password") String password){
    System.out.println(account);
    System.out.println(password);
    return "redirect:/common/home";
  }

  @GetMapping("/register")
  public String register(Model model){
    Map<String, String> params = new HashMap<>();
    params.put("title", "课时管理系统-欢迎登录");
    model.addAttribute("params", params);
    return "/home/register";
  }
}
