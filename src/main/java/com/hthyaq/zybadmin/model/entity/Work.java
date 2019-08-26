package com.hthyaq.zybadmin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 岗位及工种
 * </p>
 *
 * @author hk
 * @since 2019-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Work implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 门类
     */

    @TableField("industyName")
    private String industyName;

    /**
     * 大类
     */

    @TableField("postName")
    private String postName;

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
     * 等级
     */
    private Integer level;


}
