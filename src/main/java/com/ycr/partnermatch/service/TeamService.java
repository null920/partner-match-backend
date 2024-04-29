package com.ycr.partnermatch.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ycr.partnermatch.model.domain.Team;
import com.ycr.partnermatch.model.dto.TeamQuery;
import com.ycr.partnermatch.model.request.TeamJoinRequest;
import com.ycr.partnermatch.model.request.TeamQuitRequest;
import com.ycr.partnermatch.model.request.TeamUpdateRequest;
import com.ycr.partnermatch.model.vo.TeamUserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author null&&
 * @description 针对表【team(队伍)】的数据库操作Service
 * @createDate 2024-04-26 22:10:29
 */
public interface TeamService extends IService<Team> {
	/**
	 * 添加队伍
	 *
	 * @param team    队伍
	 * @param request http请求
	 * @return 队伍id
	 */
	long addTeam(Team team, HttpServletRequest request);

	/**
	 * 搜索队伍
	 *
	 * @param teamQuery 搜索队伍参数
	 * @param request   http请求
	 * @return 队伍列表
	 */
	List<TeamUserVO> teamList(TeamQuery teamQuery, HttpServletRequest request);

	/**
	 * 更新队伍
	 *
	 * @param teamUpdateRequest 更新队伍参数
	 * @param request           http请求
	 * @return 是否更新成功
	 */
	boolean updateTeam(TeamUpdateRequest teamUpdateRequest, HttpServletRequest request);

	/**
	 * 加入队伍
	 *
	 * @param teamJoinRequest 加入队伍参数
	 * @param request         http请求
	 * @return 是否加入成功
	 */
	boolean joinTeam(TeamJoinRequest teamJoinRequest, HttpServletRequest request);

	/**
	 * 退出队伍
	 *
	 * @param teamQuitRequest 退出队伍参数
	 * @param request         http请求
	 * @return 是否退出成功
	 */
	boolean quitTeam(TeamQuitRequest teamQuitRequest, HttpServletRequest request);

	/**
	 * 解散队伍
	 *
	 * @param teamId  队伍id
	 * @param request http请求
	 * @return 是否解散成功
	 */
	boolean deleteTeam(long teamId, HttpServletRequest request);
}
