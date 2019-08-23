package com.hthyaq.zybadmin.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Strings;
import com.hthyaq.zybadmin.model.bean.Child;
import com.hthyaq.zybadmin.model.entity.Demo;
import com.hthyaq.zybadmin.model.entity.DemoCourse;
import com.hthyaq.zybadmin.model.vo.DemoData;
import com.hthyaq.zybadmin.service.DemoCourseService;
import com.hthyaq.zybadmin.service.DemoService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhangqiang
 * @since 2019-08-10
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    DemoService demoService;
    @Autowired
    DemoCourseService demoCourseService;

    @PostMapping("/add")
//    public boolean add(@RequestParam DemoData demoData){
//       boolean flag1=true;
//       boolean flag2=true;
//        Demo demo = new Demo();
//        BeanUtils.copyProperties(demoData,demo);
//        flag1 = demoService.save(demo);
//        List<DemoCourse> dataSource = demoData.getCourse().getDataSource();
//        Integer id = demo.getId();
//        if (ObjectUtil.length(dataSource)>0){
//            dataSource.forEach(demoCourse -> demoCourse.setId(id));
//            flag2 = demoCourseService.saveBatch(dataSource);
//        }
//        return flag1&&flag2;
//    }
//    事务写到service层
    public boolean add(@RequestBody DemoData demoData) {
        return demoService.savaDemo(demoData);
    }

    @GetMapping("/delete")
//    public boolean delete(Integer id) {
//        boolean flag1 = true;
//        boolean flag2 = true;
//        flag1 = demoService.removeById(id);
//        QueryWrapper<DemoCourse> queryWrapper = new QueryWrapper<>();
//        QueryWrapper<DemoCourse> demo_id = queryWrapper.eq("demo_id", id);
//        demoCourseService.remove(demo_id);
//        return flag1 && flag2;
//    }
    public boolean delete(Integer id) {
            return demoService.deleteDemo(id);
        }

    @GetMapping("/getById")
    public DemoData getById(Integer id) {
//        通过id获取主表中的数据
        Demo demo = demoService.getById(id);
//        创建demodate空对象
        DemoData demoData = new DemoData();
//        将主表数据复制给demodate，demodae就有了id
        BeanUtils.copyProperties(demo, demoData);
//        创建条件查询构造器
        QueryWrapper<DemoCourse> queryWrapper = new QueryWrapper<>();
//        主键id等于外键demo_id
        queryWrapper.eq("demo_id", id);
//        根据处理后的条件，查询出外键数据
        List<DemoCourse> list1 = demoCourseService.list(queryWrapper);
//        将查询后的向上返回
        Child<DemoCourse> child = new Child<>();
        child.setDataSource(list1);
        demoData.setCourse(child);
        return demoData;
    }

    @PostMapping("/edit")
    public boolean edit(@RequestBody DemoData demoData) {
        return demoService.editDemo(demoData);
    }


    @GetMapping("/list")
    public IPage<Demo> list(String json) {
        //字符串解析成java对象
        JSONObject jsonObject = JSON.parseObject(json);
        //从对象中获取值
        Integer currentPage = jsonObject.getInteger("currentPage");
        Integer pageSize = jsonObject.getInteger("pageSize");
        String username = jsonObject.getString("username");
        QueryWrapper<Demo> queryWrapper = new QueryWrapper<>();
        if (!Strings.isNullOrEmpty(username)) {
            queryWrapper.eq("username", username);
        }

        IPage<Demo> page = demoService.page(new Page<>(currentPage, pageSize), queryWrapper);

        return page;
    }

}