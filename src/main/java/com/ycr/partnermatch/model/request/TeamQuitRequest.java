package com.ycr.partnermatch.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 退出队伍请求体
 *
 * @author null&&
 * @version 1.0
 * @date 2024/4/29 18:05
 */
@Data
public class TeamQuitRequest implements Serializable {
	private static final long serialVersionUID = -8501467175774404395L;

	/**
	 * 队伍id
	 */
	private Long teamId;

}
