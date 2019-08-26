package com.hthyaq.zybadmin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 职业病危害因素分类目录
 * </p>
 *
 * @author hk
 * @since 2019-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("dangerOfDic")
public class DangerOfDic implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 职业病危害因素
     */
    private String name;

    /**
     * CAS 号
     */
    @TableField("casNum")
    private String casNum;

    /**
     * 上级节点的id
     */
    private Integer pid;

    /**
     * 类别
     */
    private Integer leibie;


}
