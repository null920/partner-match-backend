package com.ycr.partnermatch.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ycr.partnermatch.common.ErrorCode;
import com.ycr.partnermatch.exception.BusinessException;
import com.ycr.partnermatch.mapper.TeamMapper;
import com.ycr.partnermatch.model.domain.Team;
import com.ycr.partnermatch.model.domain.User;
import com.ycr.partnermatch.model.domain.UserTeam;
import com.ycr.partnermatch.model.dto.TeamQuery;
import com.ycr.partnermatch.model.enums.TeamStatusEnum;
import com.ycr.partnermatch.model.request.TeamJoinRequest;
import com.ycr.partnermatch.model.request.TeamUpdateRequest;
import com.ycr.partnermatch.model.vo.TeamUserVO;
import com.ycr.partnermatch.model.vo.UserVO;
import com.ycr.partnermatch.service.TeamService;
import com.ycr.partnermatch.service.UserService;
import com.ycr.partnermatch.service.UserTeamService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.ycr.partnermatch.constant.UserConstant.ADMIN_ROLE;
import static com.ycr.partnermatch.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @author null&&
 * @description 针对表【team(队伍)】的数据库操作Service实现
 * @createDate 2024-04-26 22:10:29
 */
@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team>
		implements TeamService {

	@Resource
	private UserTeamService userTeamService;

	@Resource
	private UserService userService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public long addTeam(Team team, HttpServletRequest request) {
		// 为空校验
		if (team == null) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR);
		}
		// 登录校验
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			throw new BusinessException(ErrorCode.NO_AUTH, "未登录");
		}
		final long userId = loginUser.getId();
		// 校验队伍人数 1-20
		int maxNum = Optional.ofNullable(team.getMaxNum()).orElse(0);
		if (maxNum < 1 || maxNum > 20) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍人数不满足要求");
		}
		// 校验队伍名称 1-20
		String teamName = team.getTeamName();
		if (StringUtils.isBlank(teamName) || teamName.length() < 1 || teamName.length() > 20) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍名称不满足要求");
		}
		// 校验描述 0-512
		if (StringUtils.isNotBlank(team.getDescription()) && team.getDescription().length() > 512) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍描述不满足要求");
		}
		// 校验队伍状态 0-公开 1-私有 2-加密
		int status = Optional.ofNullable(team.getStatus()).orElse(0);
		TeamStatusEnum statusEnum = TeamStatusEnum.getEnumByValue(status);
		if (statusEnum == null) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍状态不满足要求");
		}
		// 加密队伍需要校验密码
		String password = team.getPassword();
		if (TeamStatusEnum.ENCRYPT.equals(statusEnum) && (StringUtils.isBlank(password) || password.length() > 32)) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码不能为空或密码过长");
		}
		// 判断过期时间 > 当前时间
		if (new Date().after(team.getExpireTime())) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "过期时间 > 当前时间");
		}
		// 队伍数量校验
		// todo 有可能同时创建100个队伍
		QueryWrapper<Team> wrapper = new QueryWrapper<>();
		wrapper.eq("user_id", userId);
		long hasTeamNum = this.count(wrapper);
		if (hasTeamNum >= 5) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "最多创建5个队伍");
		}
		// 插入队伍
		team.setId(null);
		team.setUserId(userId);
		boolean saveResult = this.save(team);
		Long teamId = team.getId();
		if (!saveResult || teamId == null) {
			throw new BusinessException(ErrorCode.SYSTEM_ERROR, "创建队伍失败");
		}
		// 插入队伍用户关系
		UserTeam userTeam = new UserTeam();
		userTeam.setUserId(userId);
		userTeam.setTeamId(teamId);
		userTeam.setJoinTime(new Date());
		saveResult = userTeamService.save(userTeam);
		if (!saveResult) {
			throw new BusinessException(ErrorCode.SYSTEM_ERROR, "创建队伍失败");
		}
		return teamId;
	}

	@Override
	public List<TeamUserVO> teamList(TeamQuery teamQuery, HttpServletRequest request) {
		QueryWrapper<Team> wrapper = new QueryWrapper<>();
		if (teamQuery != null) {
			Long id = teamQuery.getId();
			if (id != null && id > 0) {
				wrapper.eq("id", id);
			}
			String searchText = teamQuery.getSearchText();
			if (StringUtils.isNotBlank(searchText)) {
				wrapper.and(wr -> wr.like("team_name", searchText).or().like("description", searchText));
			}
			String teamName = teamQuery.getTeamName();
			if (StringUtils.isNotBlank(teamName)) {
				wrapper.like("team_name", teamName);
			}
			String description = teamQuery.getDescription();
			if (StringUtils.isNotBlank(description)) {
				wrapper.like("description", description);
			}
			Integer maxNum = teamQuery.getMaxNum();
			if (maxNum != null && maxNum > 0) {
				wrapper.eq("max_num", maxNum);
			}
			Long userId = teamQuery.getUserId();
			if (userId != null && userId > 0) {
				wrapper.eq("user_id", userId);
			}
			Integer status = teamQuery.getStatus();
			TeamStatusEnum statusEnum = TeamStatusEnum.getEnumByValue(status);
			if (statusEnum == null) {
				statusEnum = TeamStatusEnum.PUBLIC;
			}
			if (!isAdmin(request) && !TeamStatusEnum.PUBLIC.equals(statusEnum)) {
				throw new BusinessException(ErrorCode.NO_AUTH);
			}
			wrapper.eq("status", statusEnum.getValue());
		}
		// 不展示已过期的队伍
		wrapper.and(wr -> wr.ge("expire_time", new Date()).or().isNull("expire_time"));

		List<Team> teamList = this.list(wrapper);
		if (CollectionUtils.isEmpty(teamList)) {
			return new ArrayList<>();
		}
		List<TeamUserVO> teamUserVOList = new ArrayList<>();
		for (Team team : teamList) {
			Long userId = team.getUserId();
			if (userId == null) {
				continue;
			}
			// 关联查询创建人的信息
			User user = userService.getById(userId);
			TeamUserVO teamUserVO = new TeamUserVO();
			BeanUtils.copyProperties(team, teamUserVO);
			if (user != null) {
				UserVO userVO = new UserVO();
				BeanUtils.copyProperties(user, userVO);
				teamUserVO.setCreteUser(userVO);
			}
			teamUserVOList.add(teamUserVO);
		}
		return teamUserVOList;
	}

	@Override
	public boolean updateTeam(TeamUpdateRequest teamUpdateRequest, HttpServletRequest request) {
		if (teamUpdateRequest == null) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR);
		}
		User loginUser = this.getLoginUser(request);
		Long id = teamUpdateRequest.getId();
		if (id == null || id <= 0) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR);
		}
		Team oldTeam = this.getById(id);
		if (oldTeam == null) {
			throw new BusinessException(ErrorCode.NULL_ERROR, "队伍不存在");
		}
		// 校验权限（只有队伍的创建者和管理员可以修改）
		if (!oldTeam.getUserId().equals(loginUser.getId()) && !userService.isAdmin(loginUser)) {
			throw new BusinessException(ErrorCode.NO_AUTH, "没有权限");
		}
		TeamStatusEnum statusEnum = TeamStatusEnum.getEnumByValue(teamUpdateRequest.getStatus());
		// 加密队伍需要校验密码
		if (statusEnum.equals(TeamStatusEnum.ENCRYPT) && (StringUtils.isBlank(teamUpdateRequest.getPassword()) || teamUpdateRequest.getPassword().length() > 32)) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码不能为空或密码过长");
		}
		Team updateTeam = new Team();
		BeanUtils.copyProperties(teamUpdateRequest, updateTeam);
		return this.updateById(updateTeam);
	}

	@Override
	public boolean joinTeam(TeamJoinRequest teamJoinRequest, HttpServletRequest request) {
		if (teamJoinRequest == null) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR);
		}
		long teamId = teamJoinRequest.getTeamId();
		if (teamId <= 0) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR);
		}
		Team team = this.getById(teamId);
		if (team == null) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍不存在");
		}
		Date expireTime = team.getExpireTime();
		if (expireTime != null && expireTime.before(new Date())) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍已过期");
		}
		int teamStatus = team.getStatus();
		if (teamStatus == TeamStatusEnum.PRIVATE.getValue()) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "禁止加入私有队伍");
		}
		if (teamStatus == TeamStatusEnum.ENCRYPT.getValue()) {
			String password = teamJoinRequest.getPassword();
			if (StringUtils.isBlank(password) || !password.equals(team.getPassword())) {
				throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码错误");
			}
		}
		// 判断用户最多加入5个队伍
		long userId = this.getLoginUser(request).getId();
		QueryWrapper<UserTeam> wrapper = new QueryWrapper<>();
		wrapper.eq("user_id", userId);
		long hasJoinNum = userTeamService.count(wrapper);
		if (hasJoinNum > 5) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "最多创建和加入5个队伍");
		}
		// 判断是否已经加入该队伍
		wrapper = new QueryWrapper<>();
		wrapper.eq("team_id", teamId);
		wrapper.eq("user_id", userId);
		if (userTeamService.count(wrapper) > 0) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户已加入该队伍");
		}
		// 已加入队伍的人数
		wrapper = new QueryWrapper<>();
		wrapper.eq("team_id", teamId);
		long teamHasJoinNum = userTeamService.count(wrapper);
		if (teamHasJoinNum >= team.getMaxNum()) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍已满");
		}
		UserTeam userTeam = new UserTeam();
		userTeam.setUserId(userId);
		userTeam.setTeamId(teamId);
		userTeam.setJoinTime(new Date());
		return userTeamService.save(userTeam);
	}


	/**
	 * 获取当前登录用户
	 *
	 * @param request http请求
	 * @return 当前登录用户
	 */
	public User getLoginUser(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		User loginUser = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
		if (loginUser == null) {
			throw new BusinessException(ErrorCode.NO_AUTH, "未登录");
		}
		return loginUser;
	}

	/**
	 * 是否为管理员
	 *
	 * @param request http请求
	 * @return 是管理员返回true，不是返回false
	 */
	private boolean isAdmin(HttpServletRequest request) {
		User userObj = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
		return userObj != null && userObj.getUserRole().equals(ADMIN_ROLE);
	}
}




