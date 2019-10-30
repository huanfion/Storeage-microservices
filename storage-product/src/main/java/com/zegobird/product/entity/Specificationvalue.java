package com.zegobird.product.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 规格属性值表
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_specificationvalue")
public class Specificationvalue extends Model<Specificationvalue> {

    private static final long serialVersionUID=1L;

    /**
     * 规格属性值ID
     */
    @TableId(type = IdType.AUTO)
    private Integer specvalueId;

    /**
     * 规格ID
     */
    private Integer specificationid;

    /**
     * 规格属性值
     */
    private String specvalue;

    /**
     * 图片路径
     */
    private String imagepath;

    /*
     * 对照规格值
     */
    private String contrastvalue;

    /**
     * 状态 (1 启用  0 停用)
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
        return this.specvalueId;
    }

}
