package com.hthyaq.zybadmin.service.impl;

import com.hthyaq.zybadmin.model.entity.Msg;
import com.hthyaq.zybadmin.mapper.MsgMapper;
import com.hthyaq.zybadmin.service.MsgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hk
 * @since 2019-08-13
 */
@Service
public class MsgServiceImpl extends ServiceImpl<MsgMapper, Msg> implements MsgService {

}
