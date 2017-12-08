package org.jys.server.site.controller;

import io.swagger.annotations.Api;
import org.jys.server.site.service.article.tag.Tag;
import org.jys.server.site.service.article.tag.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ysjiang on 2017/12/8.
 *
 * @author Jiang YueSong
 * @date 2017/12/8
 */
@Api(tags = "Tag Service")
@RestController
@RequestMapping(value = "/rest/tag")
public class TagController {

    private final TagRepository service;

    @Autowired
    public TagController(TagRepository service) {
        this.service = service;
    }

    @GetMapping
    public Page<Tag> find(@PageableDefault(value = 15, sort = { "crtdate" }, direction = Sort.Direction.DESC) Pageable pageable){
        return service.findAll(pageable);
    }

    @PostMapping
    public Tag add(@RequestBody Tag tag){
        return service.save(tag);
    }

    @GetMapping(value = "/{id}")
    public Tag get(@PathVariable(value = "id")Integer id){
        return service.findOne(id);
    }

    @PutMapping(value = "/{id}")
    public Tag set(@RequestBody Tag tag){
        return service.save(tag);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") Integer id){
        service.delete(id);
    }

}
