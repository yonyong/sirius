package top.yonyong.sirius.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yonyong
 */
@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public Result<?> handle(Exception e, HttpServletRequest request) {
        if (e instanceof MyException) {
            MyException myException = (MyException) e;
            return Result.error(Code.ACE_EXCEPTION,myException.getResult().toString());
        } else if (e instanceof ClassCastException) {
            return Result.error(Code.ACE_EXCEPTION,"convert exception");
        } else {
            // 直接将异常对象传入日志接口，保存异常信息到日志文件中
            log.error("error: {}\n{}", e.getMessage(), ExcetionInfoUtil.getExceptionInformation(e));
            return Result.error(e.toString());
        }
    }

    /**
     * 对方法参数校验异常处理方法(仅对于表单提交有效，对于以json格式提交将会失效)
     * 如果是表单类型的提交，则spring会采用表单数据的处理类进行处理（进行参数校验错误时会抛出BindException异常）
     */
    @ExceptionHandler(BindException.class)
    public Result<?> handlerBindException(BindException exception) {
        return handlerNotValidException(exception);
    }

    /**
     * 对方法参数校验异常处理方法(前端提交的方式为json格式出现异常时会被该异常类处理)
     * json格式提交时，spring会采用json数据的数据转换器进行处理（进行参数校验时错误是抛出MethodArgumentNotValidException异常）
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handlerArgumentNotValidException(MethodArgumentNotValidException exception) {
        return handlerNotValidException(exception);
    }

    public Result<?> handlerNotValidException(Exception e) {
        BindingResult result;
        if (e instanceof BindException) {
            BindException exception = (BindException) e;
            result = exception.getBindingResult();
        } else {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            result = exception.getBindingResult();
        }
        Map<String, Object> maps;
        String message = null;
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            maps = new HashMap<>(fieldErrors.size());
            fieldErrors.forEach(error -> {
                maps.put(error.getField(), error.getDefaultMessage());
            });
            message = fieldErrors.get(0).getDefaultMessage();
        } else {
            maps = Collections.EMPTY_MAP;
        }
//        return Result.error(maps.toString());
        return Result.error(message);
    }

    /**
     * controller层方法层参数校验异常捕捉
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<?> handlerConstraintViolationException(ConstraintViolationException exception) {
        String message = exception.getConstraintViolations().iterator().next().getMessage();
        return Result.error(Code.ACE_COMMON_FAIL,message);
//        return Result.error(Code.ACE_COMMON_FAIL,exception.getMessage());
    }

}