package com.hthyaq.zybadmin.controller;


import com.hthyaq.zybadmin.model.entity.Emp;
import com.hthyaq.zybadmin.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hk
 * @since 2019-08-13
 */
@RestController
@RequestMapping("/emp")
public class EmpController {
    @Autowired
    EmpService empService;
    @GetMapping("/add")
    public boolean add(Emp emp){
   return empService.save(emp);
    }

//    @GetMapping("/adds")

}
