package comps368.u5.crud.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import comps368.u5.crud.models.CustomException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/customError")
    @PostMapping("/customError")
    public ModelAndView handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        CustomException err = new CustomException();

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                err = new CustomException("404", "Page not found!", "/");
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                err = new CustomException("500", "Server error!", "/");
            }
        }

        if (err.getErrMsg() == null || err.getErrMsg().isEmpty()) {
            err = new CustomException("-1", "Unknown Error Occur!", "/");
        }
        return new ModelAndView("error", "ErrObject", err);
    }
}