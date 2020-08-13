package top.yonyong.sirius.exception;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author yonyong
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyExceptionHandler.class)
public @interface EnableYHandleException {
}
