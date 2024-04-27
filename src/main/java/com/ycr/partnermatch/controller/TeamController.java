package com.ycr.partnermatch.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ycr.partnermatch.common.BaseResponse;
import com.ycr.partnermatch.common.ErrorCode;
import com.ycr.partnermatch.exception.BusinessException;
import com.ycr.partnermatch.model.domain.Team;
import com.ycr.partnermatch.model.domain.User;
import com.ycr.partnermatch.model.dto.TeamQuery;
import com.ycr.partnermatch.model.request.AddTeamRequest;
import com.ycr.partnermatch.service.TeamService;
import com.ycr.partnermatch.service.UserService;
import com.ycr.partnermatch.utils.ReturnResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 队伍接口
 *
 * @author null&&
 * @version 1.0
 * @date 2024/4/11 11:27
 */
@RestController
@RequestMapping("/team")
@Slf4j
@CrossOrigin(origins = {"https://user.null920.top", "http://user.null920.top", "http://localhost:3000", "http://127.0.0.1:3000",
		"http://localhost:8000", "http://127.0.0.1:8000"},
		allowCredentials = "true")
public class TeamController {
	@Resource
	private UserService userService;
	@Resource
	private TeamService teamService;

	@PostMapping("/add")
	public BaseResponse<Long> addTeam(@RequestBody AddTeamRequest addTeamRequest, HttpServletRequest request) {
		// 1. 校验
		if (addTeamRequest == null) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR);
		}
		Team team = new Team();
		BeanUtils.copyProperties(addTeamRequest, team);
		// 2. 添加
		long teamId = teamService.addTeam(team, request);
		// 3. 返回结果
		return ReturnResultUtils.success(teamId);
	}

	@PostMapping("/delete")
	public BaseResponse<Boolean> deleteTeam(@RequestBody long id) {
		// 1. 校验
		if (id <= 0) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR);
		}
		// 2. 删除
		boolean result = teamService.removeById(id);
		if (!result) {
			return ReturnResultUtils.error(ErrorCode.SYSTEM_ERROR, "删除失败");
		}
		return ReturnResultUtils.success(true);
	}

	@PostMapping("/update")
	public BaseResponse<Boolean> updateTeam(@RequestBody Team team, HttpServletRequest request) {
		if (team == null) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR);
		}
		boolean result = teamService.updateById(team);
		if (!result) {
			return ReturnResultUtils.error(ErrorCode.SYSTEM_ERROR, "更新失败");
		}
		return ReturnResultUtils.success(true);
	}

	@GetMapping("/get")
	public BaseResponse<Team> getTeamById(long id) {
		// 1. 校验
		if (id <= 0) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR);
		}
		// 2. 查询
		Team team = teamService.getById(id);
		if (team == null) {
			return ReturnResultUtils.error(ErrorCode.SYSTEM_ERROR, "队伍不存在");
		}
		return ReturnResultUtils.success(team);
	}

	@GetMapping("/list")
	public BaseResponse<List<Team>> listTeam(TeamQuery teamQuery) {
		// 1. 校验
		if (teamQuery == null) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR);
		}
		// 2. 查询
		Team team = new Team();
		BeanUtils.copyProperties(teamQuery, team);
		List<Team> teamList = teamService.list(new QueryWrapper<>(team));
		return ReturnResultUtils.success(teamList);
	}

	@GetMapping("/list/page")
	public BaseResponse<Page<Team>> listTeamByPage(TeamQuery teamQuery) {
		// 1. 校验
		if (teamQuery == null) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR);
		}
		// 2. 查询
		Team team = new Team();
		BeanUtils.copyProperties(teamQuery, team);
		Page<Team> page = new Page<>(teamQuery.getPageNum(), teamQuery.getPageSize());
		Page<Team> resultPage = teamService.page(page, new QueryWrapper<>(team));
		return ReturnResultUtils.success(resultPage);
	}
}
