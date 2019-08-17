package com.hthyaq.zybadmin.service;

import com.hthyaq.zybadmin.model.entity.Demo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hthyaq.zybadmin.model.vo.DemoData;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangqiang
 * @since 2019-08-10
 */
public interface DemoService extends IService<Demo> {
    boolean savaDemo(DemoData demoData);

    boolean deleteDemo(Integer id);

    boolean editDemo(DemoData demoData);
}
