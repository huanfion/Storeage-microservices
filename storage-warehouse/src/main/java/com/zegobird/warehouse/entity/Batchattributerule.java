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
 * 批次属性规则
 * </p>
 *
 * @author huanfion
 * @since 2019-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_batchattributerule")
public class Batchattributerule extends Model<Batchattributerule> {

    private static final long serialVersionUID=1L;

    /**
     * 规则ID
     */
    @TableId(type=IdType.AUTO)
    private Integer ruleid;

    /**
     * 批次属性ID
     */
    private Integer batchattributeid;

    /**
     * 启用 （1 是 0 否 ）
     */
    private Integer enable;

    /**
     * 标签（生产日期、失效日期、入库日期、有效日期是系统预留的，其他属性用户可自定义）
     */
    private String label;

    /**
     * 简称（显示名）
     */
    private String shortname;

    /**
     * 显示（1 是 0 否 ）
     */
    private Integer display;

    /**
     * 输入类型（0 禁用、1 必填、2 可选、3 只读）
     */
    private Integer inputtype;

    /**
     * 格式 （1字符、2数字、3日期．4日期时间）
     */
    private Integer format;

    /**
     * 属性选项
     */
    private String attributeoptions;

    /**
     * 关键属性（0 否 1 是）
     */
    private Integer keyattributes;

    /**
     * 验证（0 否 1 是）
     */
    private Integer validation;

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
        return this.ruleid;
    }

}
