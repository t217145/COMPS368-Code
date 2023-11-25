package comps368.u5.crud.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import comps368.u5.crud.models.CustomException;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(CustomException.class)
    public ModelAndView handleException(CustomException e) {
        return new ModelAndView("error", "ErrObject", e);
    }
}
