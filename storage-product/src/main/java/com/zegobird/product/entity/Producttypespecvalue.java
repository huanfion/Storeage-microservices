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
 * 类目规格属性值
 * </p>
 *
 * @author huanfion
 * @since 2019-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_producttypespecvalue")
public class Producttypespecvalue extends Model<Producttypespecvalue> {

    private static final long serialVersionUID=1L;

    /**
     * 类目规格属性值ID
     */
    @TableId(type = IdType.AUTO)
    private Integer typespecvalueid;

    /**
     * 类目规格属性ID
     */
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
     * 规格属性值ID
     */
    private Integer specvalueid;

    /**
     * 规格属性值
     */
    private String specvalue;

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
        return this.specvalueid;
    }

}
