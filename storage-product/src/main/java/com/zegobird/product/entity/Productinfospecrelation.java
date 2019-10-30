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
 * 产品规格项
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_productinfospecrelation")
public class Productinfospecrelation extends Model<Productinfospecrelation> {

    private static final long serialVersionUID=1L;

    /**
     * 产品规格项Id
     */
    @TableId(type=IdType.AUTO)
    private Integer productspecid;

    /**
     * 产品Id
     */
    private Long productid;

    /**
     * 规格ID
     */
    private Integer specificationid;

    /**
     * 规格名称
     */
    private String specificationname;

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
        return this.productspecid;
    }

}
