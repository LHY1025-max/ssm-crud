package com.atguigu.crud.controller;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.Msg;
import com.atguigu.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 处理员工CRUD请求
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 查询员工数据（分页查询）
     * @return
     */
//    @RequestMapping("/emps")
    public String getEmps(@RequestParam(value="pn", required=false, defaultValue="1")Integer pn, Map<String, Object> map){
        //引入PageHelper分页插件
        //传入页码，以及每页的数量
        PageHelper.startPage(pn,5);
        //startPage后面紧跟的这个查询就是一个分页查询
        List<Employee> emps = employeeService.getAll();
        //用PageInfo包装查询后的结果，只需要将pageInfo交给页面就行
        //封装了详细的分页信息，包括有查询出来的信息，传入连续显示的页数
        PageInfo page = new PageInfo(emps,5);
        map.put("pageInfo", page);
        return "list";
    }

    /**
     * 查询员工数据（分页查询），使用AJAX和Json技术
     * @return
     */
    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpsWithJson(@RequestParam(value="pn", required=false, defaultValue="1") Integer pn){
        //引入PageHelper分页插件
        //传入页码，以及每页的数量
        PageHelper.startPage(pn,5);
        //startPage后面紧跟的这个查询就是一个分页查询
        List<Employee> emps = employeeService.getAll();
        //用PageInfo包装查询后的结果，只需要将pageInfo交给页面就行
        //封装了详细的分页信息，包括有查询出来的信息，传入连续显示的页数
        PageInfo page = new PageInfo(emps,5);
        return Msg.success().add("pageInfo", page);
    }
}
