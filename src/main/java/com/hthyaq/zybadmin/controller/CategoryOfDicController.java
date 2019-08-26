package com.hthyaq.zybadmin.controller;


import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hthyaq.zybadmin.model.entity.CategoryOfDic;
import com.hthyaq.zybadmin.service.CategoryOfDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 国民经济行业分类及职业病 前端控制器
 * </p>
 *
 * @author hk
 * @since 2019-08-25
 */
@RestController
@RequestMapping("/categoryOfDic")
public class CategoryOfDicController {
    @Autowired
    public CategoryOfDicService categoryOfDicService;

    @GetMapping("/add")
    public void add() {
        ExcelReader reader = ExcelUtil.getReader("D:/zyb.xlsx");
        List<CategoryOfDic> all = reader.readAll(CategoryOfDic.class);
        for (CategoryOfDic categoryOfDic : all) {
            categoryOfDicService.save(categoryOfDic);
        }
    }

    @GetMapping("/list1")
    public void list1() {
        QueryWrapper<CategoryOfDic> queryWrapper = new QueryWrapper<>();
        ArrayList<Integer> li = new ArrayList<>();
        queryWrapper.likeLeft("men", "00");
        List<CategoryOfDic> list2 = categoryOfDicService.list(queryWrapper);
        for (CategoryOfDic categoryOfDic : list2) {
            Integer id = categoryOfDic.getId();
            li.add(id);
        }
        for(int i=1;i<=3;i++) {
            QueryWrapper<CategoryOfDic> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.likeRight("max", i);
            List<CategoryOfDic> list1 = categoryOfDicService.list(queryWrapper1);
            for (CategoryOfDic categoryOfDic : list1) {
                categoryOfDic.setPid( li.get(i-1));
                categoryOfDic.setTopId(li.get(i-1));
            }
            categoryOfDicService.updateBatchById(list1);
        }

        }
//        categoryOfDicService.updateBatchById(list3);
//        return categoryOfDicService.updateBatchById(list2);
//        QueryWrapper<CategoryOfDic> queryWrapper2 = new QueryWrapper<>();
//        queryWrapper2.eq("name","职业病分类");
//        List<CategoryOfDic> list4 = categoryOfDicService.list(queryWrapper2);
//        for (CategoryOfDic categoryOfDic : list4) {
//        }

}
