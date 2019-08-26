package com.hthyaq.zybadmin.controller;


import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hthyaq.zybadmin.model.entity.Work;
import com.hthyaq.zybadmin.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 岗位及工种 前端控制器
 * </p>
 *
 * @author hk
 * @since 2019-08-26
 */
@RestController
@RequestMapping("/work")
public class WorkController {
    @Autowired
    public WorkService workService;

    @GetMapping("/add")
    public void add() {
        ExcelReader reader = ExcelUtil.getReader("D:/岗位（工种).xls");
        List<Work> all = reader.readAll(Work.class);
        for (Work work : all) {
            System.out.println(work);
            workService.save(work);
        }
    }

    @GetMapping("/select")
    public boolean select() {
        List listid1=new ArrayList();
        List listid2=new ArrayList();
        QueryWrapper<Work> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("topId","-1");
        List<Work> list = workService.list(queryWrapper);
        for(Work work :list ){
            int lever= work.getLevel();
            if(lever==1){
                listid1.clear();
                Integer id = work.getId();
                listid1.add(id);
            }else if(lever==2){
                work.setPid((Integer) listid1.get(0));
                work.setTopId((Integer) listid1.get(0));
                listid2.clear();
                Integer id = work.getId();
                listid2.add(id);
                workService.updateById(work);
            }else if(lever==3){
                work.setPid((Integer) listid2.get(0));
                work.setTopId((Integer) listid1.get(0));
                System.out.println(work);
                workService.updateById(work);
            }
        }
        return true;
    }
}
