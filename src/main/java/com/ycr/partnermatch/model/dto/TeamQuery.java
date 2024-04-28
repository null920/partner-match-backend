package com.ycr.partnermatch.model.dto;

import com.ycr.partnermatch.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 队伍查询封装类
 *
 * @author null&&
 * @version 1.0
 * @date 2024/4/27 16:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TeamQuery extends PageRequest {

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
	 * 创建者id
	 */
	private Long userId;

	/**
	 * 队伍状态 0-公开，1-私有，2-加密
	 */
	private Integer status;

	/**
	 * 搜索关键词（同时对队伍名称和描述搜索）
	 */
	private String searchText;

}

