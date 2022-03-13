package com.taotao.cloud.dubbo.filter;

import static org.apache.dubbo.common.constants.CommonConstants.CONSUMER;
import static org.apache.dubbo.common.constants.CommonConstants.PROVIDER;
import static org.apache.dubbo.common.constants.FilterConstants.VALIDATION_KEY;

import com.taotao.cloud.common.enums.ResultEnum;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.utils.CollectionUtils;
import org.apache.dubbo.common.utils.ConfigUtils;
import org.apache.dubbo.rpc.AsyncRpcResult;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.validation.Validation;
import org.apache.dubbo.validation.Validator;

/**
 * CustomValidationFilter
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2022-03-10 13:36:55
 */
@Activate(group = {CONSUMER, PROVIDER}, value = "dubboValidationFilter", order = 10000)
public class DubboValidationFilter implements Filter {

	private Validation validation;

	public void setValidation(Validation validation) {
		this.validation = validation;
	}

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		if (validation != null && !invocation.getMethodName().startsWith("$")
			&& ConfigUtils.isNotEmpty(
			invoker.getUrl().getMethodParameter(invocation.getMethodName(), VALIDATION_KEY))) {
			try {
				Validator validator = validation.getValidator(invoker.getUrl());
				if (validator != null) {
					validator.validate(invocation.getMethodName(), invocation.getParameterTypes(),
						invocation.getArguments());
				}
			} catch (RpcException e) {
				throw e;
			} catch (ConstraintViolationException e) {
				Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
				if (CollectionUtils.isNotEmpty(violations)) {
					ConstraintViolation<?> violation = violations.iterator().next();
					com.taotao.cloud.common.model.Result<String> result = com.taotao.cloud.common.model.Result.fail(
						violation.getMessage(), ResultEnum.ERROR.getCode());
					return AsyncRpcResult.newDefaultAsyncResult(result, invocation);
				}
				return AsyncRpcResult.newDefaultAsyncResult(new ValidationException(e.getMessage()),
					invocation);
			} catch (Throwable t) {
				return AsyncRpcResult.newDefaultAsyncResult(t, invocation);
			}
		}
		return invoker.invoke(invocation);
	}
}