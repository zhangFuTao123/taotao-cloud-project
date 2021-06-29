package com.taotao.cloud.security.security;

import com.taotao.cloud.common.enums.ResultEnum;
import com.taotao.cloud.common.utils.LogUtil;
import com.taotao.cloud.common.utils.ResponseUtil;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * 用户权限不足处理器
 *
 * @author zyc
 */
public class CustomizedAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
		AccessDeniedException accessDeniedException) throws IOException {

		LogUtil.error("用户权限不足", accessDeniedException);
		ResponseUtil.fail(response, ResultEnum.FORBIDDEN);
	}
}