package org.zuel.mould.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zuel.mould.util.RespMsg;
import org.zuel.mould.util.RespUtil;

@ControllerAdvice
public class ExceptionHunter {

    private Logger logger = LoggerFactory.getLogger(ExceptionHunter.class);

    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    public RespMsg handle(Exception e){
        if(e instanceof DefinedException){
            DefinedException definitionException = (DefinedException)e;
            return RespUtil.error(definitionException.getCode(), definitionException.getMessage());
        } else{
            e.printStackTrace();
            return RespUtil.error(0, e.getMessage());
        }
    }
}
