package comps368.u8.eureka.web.svc.controllers;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import comps368.u8.eureka.web.svc.models.CustomException;

@ControllerAdvice
public class CustomControllerAdvice {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, String>> handleCustomException(CustomException err){
        return ResponseEntity.badRequest().body(Map.of("errCode", err.getErrCode(), "errText", err.getErrMsg()));
    }
}