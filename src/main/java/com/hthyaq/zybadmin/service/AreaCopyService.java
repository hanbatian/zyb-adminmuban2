package com.hthyaq.zybadmin.service;

import com.hthyaq.zybadmin.model.entity.AreaCopy;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 省市县三级的行政区域数据 服务类
 * </p>
 *
 * @author hk
 * @since 2019-08-20
 */
public interface AreaCopyService extends IService<AreaCopy> {
    boolean update1();

    boolean update2();

    boolean update3();

    boolean update4();

    boolean update5();
}
