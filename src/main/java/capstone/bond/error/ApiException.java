package capstone.bond.error;

import capstone.bond.dto.ApiResult;
import capstone.bond.utils.ApiUtils;
import ch.qos.logback.core.net.SocketConnector;
import org.jsoup.HttpStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiException{

    @ExceptionHandler(value={MissingServletRequestParameterException.class})
    public ApiResult<Object> handleMissingServletRequestParam(MissingServletRequestParameterException e){
        return (ApiResult<Object>) ApiUtils.error("모든 인자를 입력해주세요",HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(value={IllegalArgumentException.class})
    public ApiResult<Object> handleSymbolError(IllegalArgumentException e){
        return (ApiResult<Object>) ApiUtils.error("심볼을 정확히 입력해주세요",HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(value={HttpStatusException.class})
    public ApiResult<Object> handleHttpError(HttpStatusException e){
        return (ApiResult<Object>) ApiUtils.error("데이터 포멧을 정확히 입력해주세요",HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(value={NoSuchElementException.class})
    public ApiResult<Object> handleNoElementError(NoSuchElementException e){
        return (ApiResult<Object>)ApiUtils.error("입력날짜의 포멧을 정확히 입력해주세요.(yyyy-MM-dd)",HttpStatus.BAD_REQUEST.value());
    }
}
