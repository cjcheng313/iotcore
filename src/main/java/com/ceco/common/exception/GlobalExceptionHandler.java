package com.ceco.common.exception;

import com.ceco.common.Enum.EnumCode;
import com.ceco.common.utils.response.ResultResponse;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 方法校验异常
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public Object MethodArgumentNotValidHandler(HttpServletRequest request,
                                                MethodArgumentNotValidException exception) {
        List<ObjectError> errors =exception.getBindingResult().getAllErrors();
        StringBuffer errorMsg=new StringBuffer();
        errors.stream().forEach(x -> errorMsg.append(x.getDefaultMessage()).append(";"));
        return ResultResponse.response(EnumCode.INTERNAL_SERVER_ERROR.getValue(),errorMsg.toString(),false);
    }
    /**
     *参数校验异常
     */
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handle(ValidationException exception) {
        StringBuffer errorMsg=new StringBuffer();
        if(exception instanceof ConstraintViolationException){
            ConstraintViolationException exs = (ConstraintViolationException) exception;

            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                //打印验证不通过的信息
               errorMsg.append(item.getMessage()).append(";");

            }
        }
          return ResultResponse.response(EnumCode.INTERNAL_SERVER_ERROR.getValue(),errorMsg.toString(),false);
    }


    /**
     * 业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public Object exceptionHandler(BusinessException e) {
          return ResultResponse.response(EnumCode.INTERNAL_SERVER_ERROR.getValue(),e.getMessage(),false);
    }


    /**
     * MySQLSyntaxErrorException
     * mysql语法的异常处理
     */



    // TooManyResultsException 结果集映射异常
    @ExceptionHandler(value = TooManyResultsException.class)
    public Object tooManyResultsExceptionHandel(TooManyResultsException e) {
          return ResultResponse.response(EnumCode.INTERNAL_SERVER_ERROR.getValue(),e.getMessage(),false);
    }
    //错误的sql语句异常
    // BadSqlGrammarException
    @ExceptionHandler(value = BadSqlGrammarException.class)
    public Object exceptionHandler(BadSqlGrammarException e) {
        return ResultResponse.response(EnumCode.INTERNAL_SERVER_ERROR.getValue(),e.getMessage(),false);
    }

    //sql异常
    // SQLException
    @ExceptionHandler(value = SQLException.class)
    public Object exceptionHandler(SQLException e) {
        return ResultResponse.response(EnumCode.INTERNAL_SERVER_ERROR.getValue(),e.getMessage(),false);
    }


    /**
     * 系统异常处理
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler({Exception.class})
    public Object globalException(Exception e, WebRequest request) {
        e.printStackTrace();
        return ResultResponse.response(EnumCode.INTERNAL_SERVER_ERROR.getValue(),EnumCode.INTERNAL_SERVER_ERROR.getText(),false);
    }





}
