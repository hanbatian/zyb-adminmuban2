package com.hthyaq.zybadmin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hthyaq.zybadmin.mapper.DemoMapper;
import com.hthyaq.zybadmin.model.bean.Child;
import com.hthyaq.zybadmin.model.entity.Demo;
import com.hthyaq.zybadmin.model.entity.DemoCourse;
import com.hthyaq.zybadmin.model.vo.DemoData;
import com.hthyaq.zybadmin.service.DemoCourseService;
import com.hthyaq.zybadmin.service.DemoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhangqiang
 * @since 2019-08-10
 */
@Service
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements DemoService {
@Autowired
public DemoCourseService demoCourseService;
    @Override
    public boolean savaDemo(DemoData demoData) {
        //        传入demodate对象
        boolean flag1 = true, flag2 = true;
//        创建一个空demo对象
        Demo demo = new Demo();
//        将传入的demodate对象里面的数据复制进demo对象中，demo中将会存在demodate里所有数据
        BeanUtils.copyProperties(demoData, demo);
//        将demo数据存储到数据库中，demo数据库中存在id自增，从而可获取ID值
        flag1 = this.save(demo);
//      通过demedate获取本类中的course对象
        Child<DemoCourse> course = demoData.getCourse();
//      通过上一层获取的sourse对象获取其子对象集合
        List<DemoCourse> dataSource = course.getDataSource();
//        判断该子集合是否有数据
        if (ObjectUtil.length(dataSource) > 0) {
//            有数据则可以从上面的demo中获取的自增的id做为参数
            Integer id = demo.getId();
//            遍历有数据的list集合
            for (DemoCourse demoCourse1 : dataSource) {
//                将获取的id设置进遍历后的每一条数据中，可以查到每一个id所对应的datesource
                DemoCourse demoCourse = demoCourse1.setDemoId(id);
//                将查询后的结果集存入数据库
                flag2 = demoCourseService.saveBatch(dataSource);
            }
        }
        return flag1 && flag2;
    }

    @Override
    public boolean deleteDemo(Integer id) {
        boolean flag1 = true, flag2 = true;
//        根据id删除数据库主表
        flag1 = this.removeById(id);
//        创建一个条件查询构造器
        QueryWrapper<DemoCourse> queryWrapper = new QueryWrapper<>();
//        主键id等于外键demo_id
        queryWrapper.eq("demo_id", id);
//        将查询处理后的id作为条件，删除副标中的数据
        flag2 = demoCourseService.removeById(queryWrapper);
        return flag1 && flag2;
    }

    @Override
    public boolean editDemo(DemoData demoData) {
        boolean flag1 = true, flag2 = true;
//        创建空demo对象，更新就是先删除，再添加
        Demo demo = new Demo();
//        将传入的demodat对象复制给demo，从而获取自增的id
        BeanUtils.copyProperties(demoData, demo);
//        将demo更新并保存到数据库
        flag1 = this.updateById(demo);
//        获取demo自增的id
        Integer id = demo.getId();
//        创建条件查询器
        QueryWrapper<DemoData> queryWrapper = new QueryWrapper<>();
//        主键id等于外键id
        queryWrapper.eq("demo_id", id);
//        通过处理后的条件删除数据
        flag2 = demoCourseService.removeById(queryWrapper);
//        删除后，插入数据，通过demodate获取course
        Child<DemoCourse> course = demoData.getCourse();
//        通过course获取datesource集合
        List<DemoCourse> dataSource = course.getDataSource();
//        判断该集合是否有数据
//        dataSource.forEach(demoCourse -> demoCourse.setDemoId(demo.getId()));
        if (ObjectUtil.length(dataSource) > 0) {
//            获取主键自增的id
            Integer id1 = demo.getId();
//            遍历集合
            for (DemoCourse demoCourse1 : dataSource) {
//                将获取的id设置进遍历后的每一条数据中，可以查到每一个id所对应的datesource
                DemoCourse demoCourse = demoCourse1.setDemoId(id1);
//                将遍历后的结果集存入数据库中吗
                flag2 = demoCourseService.saveBatch(dataSource);
            }
        }
        return flag1 && flag2;
    }
}
