package web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import web.exeptions.UserNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ UserNotFoundException.class })
    ModelAndView handleRecordNotFound(UserNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        ModelMap modelMap = modelAndView.getModelMap();
        modelMap.addAttribute("error",ex.getMessage());
        return modelAndView;
    }
}
