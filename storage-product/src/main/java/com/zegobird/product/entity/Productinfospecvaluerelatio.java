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
 * 产品规格值
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_productinfospecvaluerelatio")
public class Productinfospecvaluerelatio extends Model<Productinfospecvaluerelatio> {

    private static final long serialVersionUID=1L;

    /**
     * 产品规格值ID
     */
    @TableId(type= IdType.AUTO)
    private Integer specvaluerelationid;

    /**
     * 产品规格项Id
     */
    private Integer productspecid;

    /**
     * 规格属性值ID
     */
    private Integer specvalueId;

    /**
     * 规格属性值
     */
    private String specvalue;

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
    /**
     * 图片URL
     */
    private String imageurl;
    /**
     * 质检图片
     */
    private String qualitypictures;
    @Override
    protected Serializable pkVal() {
        return this.specvaluerelationid;
    }

}
