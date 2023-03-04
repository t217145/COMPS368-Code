package comps368.u8.rest.basic.Controllers;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import comps368.u8.rest.basic.modes.CustomException;

@ControllerAdvice
public class CustomErrorHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, String>> handleCustomException(CustomException err){
        return ResponseEntity.badRequest().body(Map.of("errCode", err.getErrCode(), "errText", err.getErrMsg()));
    }
}
