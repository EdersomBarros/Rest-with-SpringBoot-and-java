package br.com.Rest_with._SpringBoot.controller;


import br.com.Rest_with._SpringBoot.model.Person;
import br.com.Rest_with._SpringBoot.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;



@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById(
            @PathVariable(value = "id")String id)   throws Exception{
        return service.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public Person crate(@RequestBody Person person)   throws Exception{
        return service.create(person);
    }

    @RequestMapping(method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person update(@RequestBody Person person)   throws Exception{
        return service.update(person);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(
            @PathVariable(value = "id")String id)   throws Exception{
        service.delete(id);
    }


}
