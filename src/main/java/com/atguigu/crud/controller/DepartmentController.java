package com.atguigu.crud.controller;

import com.atguigu.crud.bean.Department;
import com.atguigu.crud.bean.Msg;
import com.atguigu.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 处理和部门有关的请求
 */
@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    /**
     * 查询部门（分页查询），使用AJAX和Json技术
     * @return
     */
    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts(){
        List<Department> depts = departmentService.getDepts();

        return Msg.success().add("depts", depts);
    }
}
