package com.pinebud.examples.springbootshiro.io;

import com.pinebud.examples.springbootshiro.service.JxAppService;
import com.pinebud.examples.springbootshiro.service.security.common.JxUser;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by jiangyuesong on 2016/12/26 0026.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class JxUserController {

    @Resource(name = "svc")
    private JxAppService svc;

    @RequestMapping(method = RequestMethod.GET)
    public JxUser getuser(@RequestParam(value = "name")String name){
        return svc.getDataService().getUserByName(name);
    }
}
