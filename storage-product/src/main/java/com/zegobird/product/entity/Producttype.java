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
 * 产品类目
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_producttype")
public class Producttype extends Model<Producttype> {

    private static final long serialVersionUID=1L;

    /**
     * 分类ID
     */
    @TableId(type = IdType.AUTO,value = "typeid")
    private Integer id;

    /**
     * 父ID
     */
    @TableField("parentid")
    private Integer parentId;

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
     * 分类层次
     */
    private Integer level;

    /**
     * 状态 (1 启用  0 停用)
     */
    private Integer state;
    /**
     *  排序号
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

    /***
     *  分级排序号
     */
    private Integer levelorderno;

    /**
     * 电商产品类型id
     */
    private Integer categoryid;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
