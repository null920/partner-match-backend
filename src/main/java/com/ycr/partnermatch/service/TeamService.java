package com.ycr.partnermatch.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ycr.partnermatch.model.domain.Team;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author null&&
 * @description 针对表【team(队伍)】的数据库操作Service
 * @createDate 2024-04-26 22:10:29
 */
public interface TeamService extends IService<Team> {
	/**
	 * 添加队伍
	 *
	 * @param team
	 * @param request
	 * @return
	 */
	long addTeam(Team team, HttpServletRequest request);

}
