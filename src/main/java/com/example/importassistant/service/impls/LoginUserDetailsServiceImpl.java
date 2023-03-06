package com.example.importassistant.service.impls;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.importassistant.dao.EmployeeDao;
import com.example.importassistant.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginUserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private EmployeeDao employeeDao;

  /**
   * password 中的密码是从数据库中取出的加密后的密码, 会自动与前端传过来的密码进行比较
   *
   * @param username 登录时输入的用户名
   * @return 是否验证通过
   * @throws UsernameNotFoundException
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    EmployeeEntity emp = employeeDao.selectOne(new LambdaQueryWrapper<EmployeeEntity>().eq(EmployeeEntity::getLogin, username));
    return User.withUsername(username).password(emp.getLoginPwd()).roles("admin").build();
  }
}
