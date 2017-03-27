package com.pinebud.examples.springboot.war;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2016/11/21 0021.
 *
 */
@RestController
public class JxHelloController {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String getHello(){
        return "HelloWorld";
    }
}
