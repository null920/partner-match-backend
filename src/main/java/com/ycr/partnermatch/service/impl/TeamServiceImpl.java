package com.ycr.partnermatch.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ycr.partnermatch.common.ErrorCode;
import com.ycr.partnermatch.exception.BusinessException;
import com.ycr.partnermatch.mapper.TeamMapper;
import com.ycr.partnermatch.model.domain.Team;
import com.ycr.partnermatch.model.domain.User;
import com.ycr.partnermatch.model.domain.UserTeam;
import com.ycr.partnermatch.model.enums.TeamStatusEnum;
import com.ycr.partnermatch.service.TeamService;
import com.ycr.partnermatch.service.UserTeamService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

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
}




