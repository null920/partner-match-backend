package com.ycr.partnermatch.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 队伍和用户信息视图对象（脱敏）
 *
 * @author null&&
 * @version 1.0
 * @date 2024/4/28 17:42
 */
@Data
public class TeamUserVO implements Serializable {

	private static final long serialVersionUID = 5785513693778549137L;

	/**
	 * id
	 */
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
	 * 创建时间（数据插入时间）
	 */
	private Date createTime;

	/**
	 * 更新时间（数据更新时间）
	 */
	private Date updateTime;

	/**
	 * 创建人
	 */
	UserVO creteUser;
}
