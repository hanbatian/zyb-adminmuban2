package com.hthyaq.zybadmin.service.impl;

import com.hthyaq.zybadmin.model.entity.Test;
import com.hthyaq.zybadmin.mapper.TestMapper;
import com.hthyaq.zybadmin.service.TestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hk
 * @since 2019-08-12
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

}
