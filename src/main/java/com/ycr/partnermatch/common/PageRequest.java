package com.ycr.partnermatch.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用分页请求参数
 *
 * @author null&&
 * @version 1.0
 * @date 2024/4/27 17:45
 */
@Data
public class PageRequest implements Serializable {
	private static final long serialVersionUID = 6334097048610211944L;
	/**
	 * 当前页码
	 */
	protected int pageNum = 1;

	/**
	 * 每页显示条数
	 */
	protected int pageSize = 10;


}
