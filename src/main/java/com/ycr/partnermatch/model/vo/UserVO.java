package com.ycr.partnermatch.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户视图对象（脱敏）
 *
 * @author null&&
 * @version 1.0
 * @date 2024/4/28 17:45
 */
@Data
public class UserVO implements Serializable {
	private static final long serialVersionUID = -5916124655317919353L;

	/**
	 * id
	 */
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
	 * 用户角色 0-普通用户 1-管理员
	 */
	private Integer userRole;

	/**
	 * 标签列表JSON
	 */
	private String tags;

}
