package com.ycr.partnermatch.model.request;

import lombok.Data;

/**
 * @author null&&
 * @Date 2024/5/3 14:57
 */
@Data
public class TeamDeleteRequest {
    private static final long serialVersionUID = 8816004398730093429L;

    /**
     * 队伍id
     */
    private Long teamId;
}
