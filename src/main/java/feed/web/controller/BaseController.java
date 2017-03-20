package feed.web.controller;

import static feed.web.common.ResponseEnum.DBFAILED;
import static feed.web.common.ResponseEnum.LOGICFAILED;
import static feed.web.common.ResponseEnum.UNCATCHED;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import feed.web.common.ResponseEntity;
import feed.web.common.exception.FeedDaoException;
import feed.web.common.exception.FeedServiceException;

@Controller
public class BaseController {

	@ExceptionHandler(value = { Exception.class })
	public @ResponseBody ResponseEntity<Void> exceptionHandler(Exception e,
			HttpServletRequest reqeust, HttpServletResponse response) {
		ResponseEntity<Void> responseEntity = new ResponseEntity<Void>();
		if(e instanceof DataAccessException || e instanceof FeedDaoException){
			responseEntity.setCode(DBFAILED);
		}else if(e instanceof FeedServiceException){
			responseEntity.setCode(LOGICFAILED);
		}else{
			responseEntity.setCode(UNCATCHED);
		}
		return responseEntity;
	}
}
