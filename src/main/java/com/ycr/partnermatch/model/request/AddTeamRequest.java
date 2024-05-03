package com.ycr.partnermatch.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 添加队伍请求体
 *
 * @author null&&
 * @version 1.0
 * @date 2024/4/27 21:43
 */
@Data
public class AddTeamRequest implements Serializable {
	private static final long serialVersionUID = -2921799087108718880L;

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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
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

}
