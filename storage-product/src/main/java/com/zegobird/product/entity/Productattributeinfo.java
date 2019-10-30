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
 * 产品属性表
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_productattributeinfo")
public class Productattributeinfo extends Model<Productattributeinfo> {

    private static final long serialVersionUID=1L;

    /**
     * 产品属性Id
     */
    @TableId(type= IdType.AUTO)
    private Integer attrid;

    /**
     * 产品ID
     */
    private Long productid;

    /**
     * 属性ID
     */
    private Integer specificationid;

    /**
     * 属性名
     */
    private String specificationname;

    /**
     * 属性值ID
     */
    private Integer specvalueid;

    /**
     * 属性值
     */
    private String specvalue;

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
        return this.attrid;
    }

}
