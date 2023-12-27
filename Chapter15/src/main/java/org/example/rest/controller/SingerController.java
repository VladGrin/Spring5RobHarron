package org.example.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.rest.entity.Singer;
import org.example.rest.entity.Singers;
import org.example.rest.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping(value = "/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/listdata", method = RequestMethod.GET)
    @ResponseBody
    public Singers listData() {
        return new Singers(singerService.findAll());
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Singer findSingerById(@PathVariable Long id) {
        return singerService.findById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Singer create(@RequestBody Singer singer) {
        log.info("Creating singer: " + singer);
        singerService.save(singer);
        log.info("Singer created successfully with info: " + singer);
        return singer;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void update(@RequestBody Singer singer, @PathVariable Long id) {
        log.info("Updating singer: " + singer);
        singerService.save(singer);
        log.info("Singer updated successfully with info: " + singer);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable Long id) {
        log.info("Deleting singer with id: " + id);
        Singer singer = singerService.findById(id);
        singerService.delete(singer);
        log.info("Singer deleted successfully");
    }
}
// curl -v -H 'Accept: application/json' http://localhost:8080/singer/listdata
// curl -v -H 'Accept: application/xml' http://localhost:8080/singer/listdata