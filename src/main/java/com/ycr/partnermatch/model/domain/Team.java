package com.ycr.partnermatch.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 队伍
 *
 * @TableName team
 */
@TableName(value = "team")
@Data
public class Team implements Serializable {
	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 队伍名称
	 */
	private String teamName;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 最大人数
	 */
	private Integer maxNum;

	/**
	 * 过期时间
	 */
	private Date expireTime;

	/**
	 * 创建者id
	 */
	private Long userId;

	/**
	 * 队伍状态 0-公开，1-私有，2-加密
	 */
	private Integer status;

	/**
	 * 密码
	 */
	private String password;

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