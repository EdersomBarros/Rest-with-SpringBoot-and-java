package br.com.Rest_with._SpringBoot.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Rest_with._SpringBoot.controller.PersonController;
import br.com.Rest_with._SpringBoot.data.vo.v1.PersonVO;
import br.com.Rest_with._SpringBoot.data.vo.v2.PersonVO2;
import br.com.Rest_with._SpringBoot.exceptions.RequiredObjectIsNullException;
import br.com.Rest_with._SpringBoot.mapper.DozerMapper;
import br.com.Rest_with._SpringBoot.mapper.custom.PersonMapper;
import br.com.Rest_with._SpringBoot.model.Person;
import br.com.Rest_with._SpringBoot.repositories.PersonRepository;

@Service
public class PersonServices {


    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;
    @Autowired
    PersonMapper mapper;

    public PersonVO findById(Long id){

        logger.info("Finding one person!");
        var entity = repository.findById(id)
                         .orElseThrow(() ->
                         new RequiredObjectIsNullException("No records found for this ID!"));
        PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;

    }

    public List<PersonVO> findAll(){
        logger.info("Finding All people!");
        var persons =  DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
        persons
                .stream()
                .forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return persons;
    }

    public PersonVO create(PersonVO  person) {
    	
    	if (person == null) throw new RequiredObjectIsNullException();
        logger.info("Creating one Person!");
        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVO2 createV2(PersonVO2 person) {
        logger.info("Creating one Person with V2!");
        var entity = mapper.convertVoTOEntity(person);
        var vo = mapper.convertEntityToVo(repository.save(entity));
        return vo;
    }

    public PersonVO update(PersonVO  person) {
    	if (person == null) throw new RequiredObjectIsNullException();
        logger.info("Update one Person!");
        var entity = repository.findById(person.getKey())
                        .orElseThrow(() ->
                        new RequiredObjectIsNullException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {
        logger.info("Delete on Person!");
        var entity = repository.findById(id)
                .orElseThrow(() ->
                        new RequiredObjectIsNullException("No records found for this ID!"));
        repository.delete(entity);

    }



}
