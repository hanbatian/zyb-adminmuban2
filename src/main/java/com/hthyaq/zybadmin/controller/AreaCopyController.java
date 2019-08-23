package com.hthyaq.zybadmin.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hthyaq.zybadmin.model.entity.AreaCopy;
import com.hthyaq.zybadmin.service.AreaCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 省市县三级的行政区域数据 前端控制器
 * </p>
 *
 * @author hk
 * @since 2019-08-20
 */
@RestController
@RequestMapping("/areaCopy")
public class AreaCopyController {
    @Autowired
    public AreaCopyService areaCopyService;

    @GetMapping("/update")
    public boolean update() {
        QueryWrapper<AreaCopy> qw = new QueryWrapper<>();
        qw.likeLeft("adcode", "0000");
        List<AreaCopy> list1 = areaCopyService.list(qw);
        if (ObjectUtil.length(list1) > 0) {
            for (AreaCopy copy1 : list1) {
                copy1.setPid(-1);
            }
        }
        return areaCopyService.updateBatchById(list1);
    }

    //    获取省，level等级为1
    @GetMapping("/update1")
    public boolean update1() {
        return areaCopyService.update1();
    }

    //    获取市，level等级为2
    @GetMapping("/update2")
    public boolean update2() {
        return areaCopyService.update2();
    }

    //    获取县区，level等级为3
    @GetMapping("/update3")
    public boolean update3() {
        return areaCopyService.update3();
    }

    @GetMapping("/update4")
    public boolean update4() {
        return areaCopyService.update4();
    }

    @GetMapping("/update5")
    public boolean update5() {
        return areaCopyService.update5();
    }
}