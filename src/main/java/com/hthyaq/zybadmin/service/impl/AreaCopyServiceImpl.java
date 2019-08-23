package com.hthyaq.zybadmin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hthyaq.zybadmin.model.entity.AreaCopy;
import com.hthyaq.zybadmin.mapper.AreaCopyMapper;
import com.hthyaq.zybadmin.service.AreaCopyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 省市县三级的行政区域数据 服务实现类
 * </p>
 *
 * @author hk
 * @since 2019-08-20
 */
@Service
public class AreaCopyServiceImpl extends ServiceImpl<AreaCopyMapper, AreaCopy> implements AreaCopyService {
    @Override
    public boolean update1() {
        QueryWrapper<AreaCopy> qw = new QueryWrapper<>();
        qw.likeLeft("adcode", "0000");
        List<AreaCopy> list1 = this.list(qw);
        if (ObjectUtil.length(list1) > 0) {
            for (AreaCopy copy1 : list1) {
                copy1.setLevel("1");
            }
        }
        return this.updateBatchById(list1);
    }

    @Override
    public boolean update2() {
        QueryWrapper<AreaCopy> qw = new QueryWrapper<>();
        qw.likeLeft("adcode", "00").notLike("adcode", "0000");
        List<AreaCopy> list1 = this.list(qw);
        if (ObjectUtil.length(list1) > 0) {
            for (AreaCopy copy1 : list1) {
                copy1.setLevel("2");
            }
        }
        return this.updateBatchById(list1);
    }

    @Override
    public boolean update3() {
        QueryWrapper<AreaCopy> qw = new QueryWrapper<>();
        qw.notLike("adcode", "00").notLike("adcode", "0000");
        List<AreaCopy> list1 = this.list(qw);
        if (ObjectUtil.length(list1) > 0) {
            for (AreaCopy copy1 : list1) {
                copy1.setLevel("3");
            }
        }
        return this.updateBatchById(list1);
    }

    @Override
    public boolean update4() {
        QueryWrapper<AreaCopy> qw = new QueryWrapper<>();
        qw.likeLeft("adcode", "0000")
                .likeLeft("name", "市");
        List<AreaCopy> list1 = this.list(qw);
        for (AreaCopy copy1 : list1) {
            copy1.setLevel("1");
            Integer id = copy1.getId();
            Integer adcode = copy1.getAdcode();
            String adcode1 = adcode.toString();
            String adcode2 = adcode1.substring(0, 3);
            QueryWrapper<AreaCopy> qw1 = new QueryWrapper<>();
            qw1.likeRight("adcode", adcode2).ne("adcode", adcode);
            List<AreaCopy> list2 = this.list(qw1);
            for (AreaCopy copy2 : list2) {
                copy2.setLevel("2");
                copy2.setPid(id);
            }
            this.updateBatchById(list2);
            copy1.setChildNum(list2.size());
        }
        return this.updateBatchById(list1);
    }

    @Override
    public boolean update5() {
        QueryWrapper<AreaCopy> qw = new QueryWrapper<>();
//        select * from area where adcode like '%0000' and name not like '%市'
        qw.likeLeft("adcode", "0000").notLike("name", "市");
        List<AreaCopy> list = this.list(qw);
        for (AreaCopy areaCopy : list) {
            areaCopy.setLevel("1");
            Integer adcode = areaCopy.getAdcode();
            Integer id = areaCopy.getId();
            String adcode1 = adcode.toString();
            String adcodte2 = adcode1.substring(0, 3) + "_00";
            QueryWrapper<AreaCopy> qw1 = new QueryWrapper<>();
            qw1.like("adcode", adcodte2).ne("adcode", adcode);
            List<AreaCopy> list1 = this.list(qw1);
            for (AreaCopy areaCopy1 : list1) {
                areaCopy1.setPid(id);
                areaCopy1.setLevel("2");
                Integer id1 = areaCopy1.getId();
                Integer adcode3 = areaCopy1.getAdcode();
                String adcode4 = adcode3.toString();
                String adcode5 = adcode4.substring(0, 4);
                QueryWrapper<AreaCopy> qw2 = new QueryWrapper<>();
                qw2.likeRight("adcode", adcode5).ne("adcode", adcode3);
                List<AreaCopy> list2 = this.list(qw2);
                for (AreaCopy areaCopy2 : list2) {
                    areaCopy2.setPid(id1);
                    areaCopy2.setLevel("3");
                }
                if (CollectionUtil.isNotEmpty(list2)) {
                    this.updateBatchById(list2);
                }
                areaCopy1.setChildNum(list2.size());
            }
            if (CollectionUtil.isNotEmpty(list1)) {
                this.updateBatchById(list1);
            }
            areaCopy.setChildNum(list1.size());
        }
        return this.updateBatchById(list);
    }
}