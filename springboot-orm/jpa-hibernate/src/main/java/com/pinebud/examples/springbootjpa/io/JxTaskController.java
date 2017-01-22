package com.pinebud.examples.springbootjpa.io;

import com.pinebud.examples.springbootjpa.service.JxAppService;
import com.pinebud.examples.springbootjpa.service.task.JxTask;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2016/6/16 0016.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/task")
@Transactional
public class JxTaskController {
    
    @Resource(name = "svc")
    private JxAppService svc;
    @RequestMapping(method = RequestMethod.GET)
    public Map<String,JxTask> getTasks(@RequestParam(value = "principal" ,required = false) String principal,
                                       @RequestParam(value = "state" ,required = false) String state,
                                       @RequestParam(value = "crtbeg" ,required = false) String crtbeg,
                                       @RequestParam(value = "crtend" ,required = false) String crtend,
                                       @RequestParam(value = "ddlbeg" ,required = false) String ddlbeg,
                                       @RequestParam(value = "ddlend" ,required = false) String ddlend,
                                       @RequestParam(value = "description",required = false)String description,
                                       @RequestParam(value = "offset",required = false,defaultValue = "0")String offset,
                                       @RequestParam(value = "limit",required = false,defaultValue = "100")String limit,
                                       @RequestParam(value = "order",required = false,defaultValue = "creattime")String order,
                                       @RequestParam(value = "desc",required = false,defaultValue = "true")String desc){
        return  svc.getTaskService().find();
    }


    @RequestMapping(method = RequestMethod.POST)
    public  JxTask addTasks(){
        JxTask task=new JxTask();
        task.setUuid(UUID.randomUUID());
        task.setDeadline(new Date());
        task.setCreattime(new Date());
        task.setState(1);
        task.setDescription("spring-orm test");
        task.setPrincipal("JYS");
        return svc.getTaskService().add(task);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JxTask getTask(@PathVariable(value = "id")String id){
        return svc.getTaskService().get(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void deleteTask(@PathVariable(value = "id")String id){
         svc.getTaskService().remove(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public void updateTask(@PathVariable(value = "id")String id,@RequestBody JxTask task){
        svc.getTaskService().set(id,task);
    }
}
