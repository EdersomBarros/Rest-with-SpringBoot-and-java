package br.com.Rest_with._SpringBoot.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Rest_with._SpringBoot.controller.BookController;
import br.com.Rest_with._SpringBoot.data.vo.v1.BookVO;
import br.com.Rest_with._SpringBoot.exceptions.RequiredObjectIsNullException;
import br.com.Rest_with._SpringBoot.mapper.DozerMapper;
import br.com.Rest_with._SpringBoot.model.Book;
import br.com.Rest_with._SpringBoot.repositories.BookRepository;

@Service
public class BookServices {

	private Logger logger = Logger.getLogger(BookServices.class.getName());

	@Autowired
	BookRepository repository;

	public BookVO findById(Long id) {

		logger.info("Finding one book!");
		var entity = repository.findById(id)
				.orElseThrow(() -> new RequiredObjectIsNullException("No records found for this ID!"));
		BookVO vo = DozerMapper.parseObject(entity, BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return vo;

	}

	public List<BookVO> findAll() {
		logger.info("Finding All book!");
		var books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
		books.stream().forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		return books;
	}

	public BookVO create(BookVO book) {

		if (book == null) throw new RequiredObjectIsNullException();
		logger.info("Creating one book!");
		var entity = DozerMapper.parseObject(book, Book.class);
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	/*
	 * public BookVO2 createV2(BookVO2 book) {
	 * logger.info("Creating one book with V2!"); var entity =
	 * mapper.convertVoTOEntity(book); var vo =
	 * mapper.convertEntityToVo(repository.save(entity)); return vo; }
	 */

	public BookVO update(BookVO book) {
		if (book == null)
			throw new RequiredObjectIsNullException();
		logger.info("Update one book!");
		var entity = repository.findById(book.getKey())
				.orElseThrow(() -> new RequiredObjectIsNullException("No records found for this ID!"));

		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());

		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	public void delete(Long id) {
		logger.info("Delete on book!");
		var entity = repository.findById(id)
				.orElseThrow(() -> new RequiredObjectIsNullException("No records found for this ID!"));
		repository.delete(entity);

	}

}
