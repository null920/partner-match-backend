package com.ycr.partnermatch.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 标签
 *
 * @TableName tag
 */
@TableName(value = "tag")
@Data
public class Tag implements Serializable {
	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 标签名称
	 */
	private String tagName;

	/**
	 * 用户 id
	 */
	private Long userId;

	/**
	 * 父标签 id
	 */
	private Long parentId;

	/**
	 * 是否是父标签 0-不是 1-是
	 */
	private Integer isParent;

	/**
	 * 创建时间（数据插入时间）
	 */
	private Date createTime;

	/**
	 * 更新时间（数据更新时间）
	 */
	private Date updateTime;

	/**
	 * 是否删除 0 1（逻辑删除）
	 */
	@TableLogic
	private Integer deleted;

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
}