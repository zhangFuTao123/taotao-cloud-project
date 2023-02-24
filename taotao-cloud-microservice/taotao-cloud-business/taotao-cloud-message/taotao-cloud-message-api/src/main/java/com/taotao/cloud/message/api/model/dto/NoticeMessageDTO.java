package com.taotao.cloud.message.api.model.dto;

import com.taotao.cloud.message.api.enums.NoticeMessageNodeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 站内信消息
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-05-19 14:59:16
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeMessageDTO {

	@Schema(description = "会员ID")
	private Long memberId;

	@Schema(description = "消息节点")
	private NoticeMessageNodeEnum noticeMessageNodeEnum;

	@Schema(description = "消息参数")
	private Map<String, String> parameter;
}