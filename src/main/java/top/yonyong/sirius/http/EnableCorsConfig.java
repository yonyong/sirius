package top.yonyong.sirius.http;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author yonyong
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CorsConfig.class)
public @interface EnableCorsConfig {
}
