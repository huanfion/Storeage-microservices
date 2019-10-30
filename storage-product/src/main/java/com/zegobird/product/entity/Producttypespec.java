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
 * 类目规格属性
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_producttypespec")
public class Producttypespec extends Model<Producttypespec> {

    private static final long serialVersionUID=1L;

    /**
     * 类目规格属性ID
     */
    @TableId(type = IdType.AUTO)
    private Integer typespecid;

    /**
     * 规格ID
     */
    private Integer specificationid;

    /**
     * 规格属性名称
     */
    private String specname;

    /**
     * 分类ID
     */
    private Integer typeid;

    /**
     * 分类名称
     */
    private String typename;

    /**
     * 完全路径Id
     */
    private String fulltypeid;

    private String fulltypename;

    /**
     * 输入方式（1 复选框 2 单选框 3 文本框 4 下拉框）
     */
    private Integer displaymode;

    /**
     * 是否必填 （0 否 1 是）
     */
    private Integer ismust;

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
        return this.typespecid;
    }

}
