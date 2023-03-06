package com.example.importassistant;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.importassistant.dao.StudentDao;
import com.example.importassistant.entity.StudentEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ImportAssistantApplicationTests {

  @Autowired
  private StudentDao studentDao;

  @Test
  void contextLoads() {
  }

  @Test
  public void testSelect(){
    System.out.println(("----- selectAll method test ------"));
    List<StudentEntity> studentList = studentDao.selectList(null);
    studentList.forEach(System.out::println);
  }

  @Test
  public void testJoinSelect(){
//    LambdaQueryWrapper<StudentEntity> wrapper = new QueryWrapper<StudentEntity>().lambda();
//    wrapper.eq(StudentEntity::getStudentName, "张小凡");
    QueryWrapper<StudentEntity> wrapper = new QueryWrapper<>();
    wrapper.eq("s.STUDENT_NAME", "张小凡");
    Page page = new Page(1, 10);
    OrderItem orderItem = new OrderItem();
    orderItem.setColumn("s.MODIFY_TIME");
    orderItem.setAsc(false);
    page.addOrder(orderItem);

//    Page<StudentEntity> student = studentDao.listStudentsByConditions(Wrappers.<StudentEntity>lambdaQuery().eq(StudentEntity::getStudentName, "张小凡"), page);
    Page<StudentEntity> student = studentDao.listStudentsByConditions(wrapper, page);
    List<StudentEntity> records = student.getRecords();
    long total = student.getTotal();
    long size = student.getSize();
    System.out.println("================================");
    System.out.println("总条数： " + total);
    System.out.println("当前条数： " + size);
    System.out.println("============记录如下=============");
    for (StudentEntity record : records) {
      System.out.println(record);
    }
    System.out.println("================================");


    System.out.println(student);
  }

  @Test
  public void testJoinPage(){

  }

  @Test
  public void testInsert(){
    StudentEntity student = new StudentEntity();
    student.setStudentName("李四");
    student.setSex(1);
    student.setAge(5);
    student.setAddress("马里亚纳海沟");
    student.setCellphone("8888888");
    int insert = studentDao.insert(student);
    System.out.println(insert);
  }

  @Test
  public void testUpdate(){
//    UpdateWrapper wrapper = (UpdateWrapper) new UpdateWrapper().eq("name", "王五");
//    wrapper.eq("name", "李四");
    UpdateWrapper<StudentEntity> wrapper = new UpdateWrapper<>();
    wrapper.eq("name", "李四").ne("sex", 0);
    StudentEntity student = new StudentEntity();
    student.setParent("赵虎");
    int update = studentDao.update(student, wrapper);
    System.out.println(update);
  }

  @Test
  public void testDelete(){
    UpdateWrapper<StudentEntity> wrapper = new UpdateWrapper<StudentEntity>().eq("name", "李四");
    int delete = studentDao.delete(wrapper);
    System.out.println(delete);
  }
}
