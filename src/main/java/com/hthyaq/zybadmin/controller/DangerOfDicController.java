package com.hthyaq.zybadmin.controller;


import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hthyaq.zybadmin.model.entity.DangerOfDic;
import com.hthyaq.zybadmin.service.DangerOfDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 职业病危害因素分类目录 前端控制器
 * </p>
 *
 * @author hk
 * @since 2019-08-25
 */
@RestController
@RequestMapping("/dangerOfDic")
public class DangerOfDicController {
    @Autowired
    public DangerOfDicService dangerOfDicService;
    @GetMapping("/add")
    public void add() {
        ExcelReader reader = ExcelUtil.getReader("D:/因素.xlsx");
        List<DangerOfDic> all = reader.readAll(DangerOfDic.class);
        for (DangerOfDic dangerOfDic : all) {
            dangerOfDicService.save(dangerOfDic);
        }
    }
    @GetMapping("/list1")
    public boolean list1(){
        QueryWrapper<DangerOfDic> queryWrapper = new QueryWrapper<>();
//        SELECT * FROM dangerOfDic WHERE leibie LIKE '%000'
        queryWrapper.likeLeft("leibie","000");
        List<DangerOfDic> list2 = dangerOfDicService.list(queryWrapper);
        for (DangerOfDic dangerOfDic : list2) {
            Integer id = dangerOfDic.getId();
            Integer leibie = dangerOfDic.getLeibie();
            String s = leibie.toString();
            String substring = s.substring(0, 1);
            QueryWrapper<DangerOfDic> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.likeRight("leibie",substring).ne("leibie",leibie);
            List<DangerOfDic> list3= dangerOfDicService.list(queryWrapper1);
            for (DangerOfDic ofDic : list3) {
                DangerOfDic dangerOfDic1 = ofDic.setPid(id);
                System.out.println(dangerOfDic1);
            }
            dangerOfDicService.updateBatchById(list3);
        }
        return dangerOfDicService.updateBatchById(list2);
    }
}
