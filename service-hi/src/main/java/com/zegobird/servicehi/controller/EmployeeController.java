package com.zegobird.servicehi.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zegobird.servicehi.entity.Employee;
import com.zegobird.servicehi.mapper.EmployeeMapper;
import com.zegobird.servicehi.service.IEmployeeService;
import com.zegobird.servicehi.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huanfion
 * @since 2019-07-29
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

//    @Autowired
//    EmployeeMapper employeeMapper;

    @Autowired
    private IEmployeeService employeeService;

    @PostMapping("add")
    public Map<String,String> addEmpployee(@Valid @RequestBody Employee entity){
        Employee employee=new Employee();
        employee.setAge(entity.getAge());
        employee.setEmail(entity.getEmail());
        employee.setGender(entity.getGender());
        employee.setLastName(entity.getLastName());
        //由于设置了主键策略 id可不用赋值 会自动生成
        employeeService.save(employee);
        Map<String,String> result = new HashMap<String,String>();
        result.put("respCode", "01");
        result.put("respMsg", "新增成功");
        //事务测试
        //System.out.println(1/0);
        return result;
    }
    /**
     * 分页 PAGE
     */
    @GetMapping("/list")
    public Map<String,Object> list() {
        //分页
        Page<Employee> page = new Page<>(1, 2);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("respCode", "01");
        result.put("respMsg", "成功");
        result.put("data", employeeService.page(page).getRecords());
        return result;
        //return employeeMapper.selectPage(new Page<Employee>(1,2),null).getRecords();
    }


}
