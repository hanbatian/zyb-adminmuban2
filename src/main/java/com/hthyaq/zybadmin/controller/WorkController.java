package com.hthyaq.zybadmin.controller;


import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hthyaq.zybadmin.model.entity.TreeSelectData;
import com.hthyaq.zybadmin.model.entity.Work;
import com.hthyaq.zybadmin.service.WorkService;
import org.apache.commons.compress.utils.Lists;
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
        ExcelReader reader = ExcelUtil.getReader("D:/岗位（工种)1.xls");
        List<Work> all = reader.readAll(Work.class);
        for (Work work : all) {
            System.out.println(work);
            workService.save(work);
        }
    }

    @GetMapping("/select")
    public boolean select() {
        List listid1 = new ArrayList();
        List listid2 = new ArrayList();
        QueryWrapper<Work> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("topId", "-1");
        List<Work> list = workService.list(queryWrapper);
        for (Work work : list) {
            int lever = work.getLevel();
            if (lever == 1) {
                listid1.clear();
                Integer id = work.getId();
                listid1.add(id);
            } else if (lever == 2) {
                work.setPid((Integer) listid1.get(0));
                work.setTopId((Integer) listid1.get(0));
                listid2.clear();
                Integer id = work.getId();
                listid2.add(id);
                workService.updateById(work);
            } else if (lever == 3) {
                work.setPid((Integer) listid2.get(0));
                work.setTopId((Integer) listid1.get(0));
                System.out.println(work);
                workService.updateById(work);
            }
        }
        return true;
    }

    @GetMapping("/tree")
    public List<TreeSelectData> tree() {
        ArrayList<TreeSelectData> list5 = new ArrayList<>();
        ArrayList<TreeSelectData> list = new ArrayList<>();
        QueryWrapper<Work> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", "-1");
        List<Work> list1 = workService.list(queryWrapper);
        for (Work work1 : list1) {
            TreeSelectData treeSelectData = new TreeSelectData();
            treeSelectData.setTitle(work1.getIndustyName());
            treeSelectData.setValue(work1.getLevel());
            treeSelectData.setKey(work1.getTopId());
//            将第一层遍历后设置后的结果存入大集合中
            list.add(treeSelectData);
//            将第二层获取的遍历结果存入5中，设置为第一层的子类
            treeSelectData.setChildren(list5);


            QueryWrapper<Work> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("pid", work1.getId());
            List<Work> list2 = workService.list(queryWrapper1);
            for (Work work2 : list2) {
                Integer id1 = work2.getId();
                TreeSelectData treeSelectData1 = new TreeSelectData();
                treeSelectData1.setTitle(work2.getIndustyName());
                treeSelectData1.setValue(work2.getLevel());
                treeSelectData1.setKey(work2.getTopId());
//                将遍历后设置后的结果放入5中，当做条件，放入上一层
                list5.add(treeSelectData1);

                List<TreeSelectData> children = Lists.newArrayList();
                QueryWrapper<Work> queryWrapper2 = new QueryWrapper<>();
                queryWrapper2.eq("pid", id1);
                List<Work> list4 = workService.list(queryWrapper2);
                for (Work work3 : list4) {
                    TreeSelectData treeSelectData2 = new TreeSelectData();
                    treeSelectData2.setTitle(work3.getIndustyName());
                    treeSelectData2.setValue(work3.getLevel());
                    treeSelectData2.setKey(work3.getTopId());
                    children.add(treeSelectData2);
                }
                treeSelectData1.setChildren(children);
            }
        }
        return list;
    }
}
