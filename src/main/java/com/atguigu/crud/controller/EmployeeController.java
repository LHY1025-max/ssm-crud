package com.atguigu.crud.controller;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.Msg;
import com.atguigu.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
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

    /**
     * 保存员工数据
     * 1、JSR303校验
     * 2、导入Hibernate-Validator
     * @param employee
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/emp", method= RequestMethod.POST)
    public Msg saveEmp(@Valid Employee employee, BindingResult result){
        if (result.hasErrors()){
            //校验失败，返回失败，在模态框中显示校验失败的错误信息
            Map<String,Object> map = new HashMap<>();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        }else {
            employeeService.saveEmp(employee);
            return Msg.success();
        }
    }

    /**
     * 校验员工姓名数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkUser")
    public Msg checkUserName(@RequestParam("empName")String empName) {
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
        if (!empName.matches(regx)){
            return Msg.fail();
        }
        boolean b = employeeService.checkUserName(empName);
        if (b) {
            return Msg.success();
        } else {
            return Msg.fail();
        }
    }

    /**
     * 根据id查询员工信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public Msg getEmp(@PathVariable("id")Integer id){
        Employee emp = employeeService.getEmp(id);
        return Msg.success().add("emp", emp);
    }

    /**
     * 根据id修改员工信息
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/emp/{empId}", method = RequestMethod.PUT)
    public Msg updateEmp(Employee employee){
        employeeService.updateEmp(employee);
        return Msg.success();
    }

    /**
     * 根据id删除员工
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    public Msg deleteEmp(@PathVariable("id")Integer id){
        employeeService.deleteEmp(id);
        return Msg.success();
    }

    /**
     * 根据id删除多个员工
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteEmps/{ids}", method = RequestMethod.DELETE)
    public Msg deleteEmps(@PathVariable("ids")String ids){
        String[] splitId = ids.split(",");
        List<Integer> idList = new ArrayList<>();
        for (String s : splitId) {
            int empId = Integer.parseInt(s);
            idList.add(empId);
        }
        employeeService.deleteBatch(idList);
        return Msg.success();
    }

}
