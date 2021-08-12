package com.atguigu.crud.test;

import com.atguigu.crud.bean.Department;
import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.dao.DepartmentMapper;
import com.atguigu.crud.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.swing.*;
import java.util.UUID;

/**
 * 测试dao层的工作
 * 推荐Spring的项目使用Spring的单元测试，可以自动注入我们需要的组件
 * 1.导入SpringTest模块
 * 2.@ContextConfiguration指定Spring配置文件的位置
 * 3.直接 autowired 要使用的组件
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    SqlSession sqlSession;
    @Test
    public void testCRUD(){
        System.out.println(departmentMapper);
        //1.插入部门
//        departmentMapper.insertSelective(new Department(null, "开发部"));
//        departmentMapper.insertSelective(new Department(null, "测试部"));
        //2.插入员工
//        employeeMapper.insert(new Employee(null, "张三", "男", "123@qq.com", 1));
//        employeeMapper.insert(new Employee(null, "李四", "男", null, 1));
//        employeeMapper.insertSelective(new Employee(null, "兰小龙", "男", "12414@qq.com", 1));
        //3.批量插入多个员工
//        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//        for(int i=0; i<1000; i++){
//            String uid = UUID.randomUUID().toString().substring(0, 5)+i;
//            mapper.insertSelective(new Employee(null, uid, "男", uid+"@atguigu.com",1));
//        }
//        System.out.println("完成");
        //4.删除员工
//        employeeMapper.deleteByPrimaryKey(10);
        //5.修改员工
//        employeeMapper.updateByPrimaryKeySelective(new Employee(4, "罗昊轶", "男", "534131274@qq.com",1));
        //6.查询员工
//        Employee employee = employeeMapper.selectByPrimaryKeyWithDept(1);
//        System.out.println(employee);
//        System.out.println(employee.getDepartment());
    }
}
