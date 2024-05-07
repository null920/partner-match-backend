package com.ycr.partnermatch.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 *
 * @author null&&
 * @TableName user
 */
@TableName(value = "user")
@Data
public class User implements Serializable {
	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 用户昵称
	 */
	private String username;

	/**
	 * 账号
	 */
	private String userAccount;

	/**
	 * 头像
	 */
	private String avatarUrl;

	/**
	 * 性别
	 */
	private Integer gender;

	/**
	 * 密码
	 */
	private String userPassword;

	/**
	 * 电话
	 */
	private String phone;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 用户状态 0-正常
	 */
	private Integer userStatus;

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

	/**
	 * 用户角色 0-普通用户 1-管理员
	 */
	private Integer userRole;

	/**
	 * 标签列表JSON
	 */
	private String tags;

	@TableField(exist = false)
	private static final long serialVersionUID = 2728434441333864902L;
}