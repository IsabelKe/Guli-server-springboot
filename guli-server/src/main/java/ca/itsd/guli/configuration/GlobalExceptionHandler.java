package ca.itsd.guli.configuration;


import ca.itsd.guli.util.ResultEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.NoSuchElementException;

/*
this class handles exceptions
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    //catch all exceptions
    @ExceptionHandler(Exception.class)
    //return data
    @ResponseBody
    public ResultEntity error(Exception e)
    {
        e.printStackTrace();
        return ResultEntity.failed(e.getMessage());
    }

    //catch NoSuchElementException
    @ExceptionHandler(NoSuchElementException.class)
    //return data
    @ResponseBody
    public ResultEntity error(NoSuchElementException e)
    {
        e.printStackTrace();
        return ResultEntity.failed("No data matches");
    }
}
