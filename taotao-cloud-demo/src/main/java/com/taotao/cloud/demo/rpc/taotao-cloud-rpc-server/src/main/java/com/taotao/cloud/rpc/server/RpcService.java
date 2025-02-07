/**
 * Project Name: my-projects
 * Package Name: com.taotao.rpc.server
 * Descroption: 标注rpc服务
 * Date: 2020/2/27 14:03
 * Author: shuigedeng
 */
package com.taotao.cloud.demo.rpc.taotao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.stereotype.Component;

/**
 * 标注是rpc服务 <br>
 *
 * @author shuigedeng
 * @version v1.0.0
 * @create 2020/2/27 14:03
 * @version 1.0.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RpcService {
	Class<?> value();
}
