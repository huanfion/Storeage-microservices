package com.zegobird.warehouse.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 批次属性
 * </p>
 *
 * @author huanfion
 * @since 2019-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_batchattribute")
public class Batchattribute extends Model<Batchattribute> {

    private static final long serialVersionUID=1L;

    /**
     * 批次属性ID
     */
    @TableId(type = IdType.AUTO)
    private Integer batchattributeid;

    /**
     * 批次属性名
     */
    private String batchattribute;

    /**
     * 描述
     */
    private String describe;

    /**
     * 状态(1 正常 0 停用)
     */
    private Integer state;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer createid;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String creator;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdate;

    /**
     * 修改人ID
     */
    @TableField(fill = FieldFill.UPDATE)
    private Integer modifyid;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    private String modifier;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime modifydate;


    @Override
    protected Serializable pkVal() {
        return this.batchattributeid;
    }

}
