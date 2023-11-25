package comps368.u5.advance.utils;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import comps368.u5.advance.models.CustomException;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(CustomException.class)
    public String handleException(CustomException e, ModelMap m) {
        m.addAttribute("ErrObject", e);
        return "error";
    }
}
