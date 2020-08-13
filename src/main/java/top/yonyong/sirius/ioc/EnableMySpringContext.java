package top.yonyong.sirius.ioc;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author yonyong
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MySpringContext.class)
public @interface EnableMySpringContext {
}
