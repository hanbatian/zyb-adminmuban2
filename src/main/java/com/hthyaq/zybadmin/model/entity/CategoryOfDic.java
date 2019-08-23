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
 * 国民经济行业分类及职业病
 * </p>
 *
 * @author hk
 * @since 2019-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("categoryOfDic")
public class CategoryOfDic implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 行业分类
     */
    private String name;

    /**
     * 上级节点的id
     */
    private Integer pid;

    /**
     * 下级节点的id
     */
    @TableField("topId")
    private Integer topId;

    /**
     * 职业病
     */
    private String flag;

    /**
     * 门类
     */
    private String men;

    /**
     * 大类
     */
    private Integer max;


}
