package com.example.jpademo.exception;


import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常处理器
 * 
 * @author yhq
 * @email
 * @date 20180726
 */
@RestControllerAdvice
@Log4j2
public class MobaoExceptionHandler {

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(MobaoException.class)
	public AjaxResult<Void> handleRRException(MobaoException e){
		return new AjaxResult(e.getCode(),e.getMsg());
	}


	@ExceptionHandler(NoHandlerFoundException.class)
	public AjaxResult<Void> handlerNoFoundException(Exception e) {
		log.error(e.getMessage(), e);
		return new AjaxResult(404, "路径不存在，请检查路径是否正确");
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public AjaxResult<Void> handleDuplicateKeyException(DuplicateKeyException e){
		log.error(e.getMessage(), e);
		return new AjaxResult(2, "数据库中已存在该记录");
	}

	@ExceptionHandler(Exception.class)
	public AjaxResult<Void> handleException(Exception e){
		log.error(e.getMessage(), e);
		return new AjaxResult<>(2,"未知错误！");
	}
}
