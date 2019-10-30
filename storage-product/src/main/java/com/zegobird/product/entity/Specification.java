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
 * 规格属性表
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_specification")
public class Specification extends Model<Specification> {

    private static final long serialVersionUID=1L;

    /**
     * 规格ID
     */
    @TableId(type = IdType.AUTO)
    private Integer specificationid;

    /**
     * 规格名称
     */
    private String specificationname;

    /**
     * 输入方式（1 复选框 2 单选框 3 文本框 4 下拉框）
     */
    private Integer displaymode;

    /**
     * 是否必填 （0 否 1 是）
     */
    private Integer ismust;

    /**
     * 状态 (1 启用  0 停用)
     */
    private Integer state;

    /**
     * 是否销售属性 （true(是),false(否)）
     */
    private Boolean issaleprop;

    /**
     * 是否颜色属性 :  true(是),false(否)
     */
    private Boolean iscolorprop;

    /**
     * 排序号
     */
    private Integer orderno;

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
        return this.specificationid;
    }

}
