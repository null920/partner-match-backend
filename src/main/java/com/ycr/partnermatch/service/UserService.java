package com.ycr.partnermatch.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ycr.partnermatch.model.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户服务
 *
 * @author null&&
 * @description 针对表【user(用户)】的数据库操作Service
 * @createDate 2024-04-10 17:49:34
 */
public interface UserService extends IService<User> {
	/**
	 * 用户注册
	 *
	 * @param userAccount   用户账户
	 * @param userPassword  密码
	 * @param checkPassword 校验密码
	 * @return 新用户 id
	 */
	long userRegister(String userAccount, String userPassword, String checkPassword);

	/**
	 * 用户登录
	 *
	 * @param userAccount  用户账户
	 * @param userPassword 密码
	 * @param request      http请求
	 * @return 用户信息（脱敏后）
	 */
	User userLogin(String userAccount, String userPassword, HttpServletRequest request);

	/**
	 * 用户信息脱敏
	 *
	 * @param originUser 原始用户数据
	 * @return 返回脱敏后的用户数据
	 */
	User getSafetyUser(User originUser);

	/**
	 * 退出登录
	 *
	 * @param request http请求
	 */
	int userLogout(HttpServletRequest request);

	/**
	 * 根据标签搜索用户(内存过滤)
	 *
	 * @param tagNameList 用户需要拥有的标签list
	 * @return 用户list
	 */
	List<User> searchUsersByTags(List<String> tagNameList);

	/**
	 * 根据标签搜索用户(SQL查询版)
	 *
	 * @param tagNameList 用户需要拥有的标签list
	 * @return 用户list
	 */
	List<User> searchUsersByTagsBySQL(List<String> tagNameList);

	/**
	 * 推荐用户
	 *
	 * @param pageSize 页面大小
	 * @param pageNum  页码
	 * @param request  http请求
	 * @return 用户list
	 */
	List<User> recommendUsers(long pageSize, long pageNum, HttpServletRequest request);

	/**
	 * 更新用户信息
	 *
	 * @param user      传入的用户信息
	 * @param loginUser 当前登录的用户
	 * @return 受影响的行数
	 */
	Integer updateUser(User user, User loginUser);

	/**
	 * 获取当前登录用户
	 *
	 * @param request http请求
	 * @return 当前登录用户
	 */
	User getLoginUser(HttpServletRequest request);

	/**
	 * 是否为管理员
	 *
	 * @param loginUser 当前登录用户
	 * @return 是否为管理员
	 */
	boolean isAdmin(User loginUser);

	/**
	 * 根据id查询用户
	 *
	 * @param id 用户id
	 * @return 用户
	 */
	User selectUserById(Long id);
}
