package com.ycr.partnermatch.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ycr.partnermatch.model.domain.UserTeam;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author null&&
 * @description 针对表【user_team(用户队伍关系)】的数据库操作Mapper
 * @createDate 2024-04-26 22:14:13
 * @Entity generator.domain.UserTeam
 */
public interface UserTeamMapper extends BaseMapper<UserTeam> {

    @Select("select * from user_team where team_id = #{teamId} and user_id = #{userId}")
    UserTeam getUserHasJoinTeam(@Param("teamId") long teamId, @Param("userId") long userId);

    @Update("update user_team set deleted = 0 where team_id = #{teamId} and user_id = #{userId}")
    boolean recoverUserTeam(@Param("teamId") long teamId, @Param("userId") long userId);
}




