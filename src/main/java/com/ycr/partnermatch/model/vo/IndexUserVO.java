package com.ycr.partnermatch.model.vo;

import com.ycr.partnermatch.model.domain.User;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 首页用户视图对象（脱敏）
 *
 * @author null&&
 * @version 1.0
 * @date 2024/4/28 17:45
 */
@Data
public class IndexUserVO implements Serializable {
    private static final long serialVersionUID = -1719635375757223207L;

    /**
     * 用户VO列表
     */
    private List<User> userVOList;

    /**
     * 用户总人数
     */
    private Long pageCount;
}
