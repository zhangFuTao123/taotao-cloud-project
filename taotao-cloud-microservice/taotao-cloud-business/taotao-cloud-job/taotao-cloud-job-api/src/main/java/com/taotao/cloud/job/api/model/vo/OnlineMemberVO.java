package com.taotao.cloud.job.api.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 在线会员
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OnlineMemberVO {

    /**
     * 在线时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH")
    private Date date;

    /**
     * 在线会员人数
     */
    private Integer num;

}