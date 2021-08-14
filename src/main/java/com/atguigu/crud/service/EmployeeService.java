package com.atguigu.crud.service;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.EmployeeExample;
import com.atguigu.crud.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 查询所有员工
     * @return
     */
    public List<Employee> getAll(){
        return employeeMapper.selectByExampleWithDept(null);
    }

    /**
     * 保存员工
     * @param employee
     * @return
     */
    public int saveEmp(Employee employee){
        return employeeMapper.insertSelective(employee);
    }

    /**
     * 校验用户名是否可用
     * @param empName
     * @return
     */
    public boolean checkUserName(String empName) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        Long count= employeeMapper.countByExample(example);
        return count==0;
    }

    /**
     * 查询员工信息
     * @return
     */
    public Employee getEmp(Integer id) {
        return employeeMapper.selectByPrimaryKeyWithDept(id);
    }

    /**
     * 修改员工信息
     * @param employee
     * @return
     */
    public int updateEmp(Employee employee) {
        return employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /**
     * 删除员工信息
     * @param id
     * @return
     */
    public int deleteEmp(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 删除多位员工信息
     * @param idList
     */
    public int deleteBatch(List<Integer> idList) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andEmpIdIn(idList);
        return employeeMapper.deleteByExample(employeeExample);
    }
}
