package br.com.Rest_with._SpringBoot.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.Rest_with._SpringBoot.data.vo.v1.BookVO;
import br.com.Rest_with._SpringBoot.exceptions.RequiredObjectIsNullException;
import br.com.Rest_with._SpringBoot.model.Book;
import br.com.Rest_with._SpringBoot.repositories.BookRepository;
import br.com.Rest_with._SpringBoot.services.BookServices;
import br.com.Rest_with._SpringBoot.unittests.mapper.mocks.MockBook;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BookServicesTest2 {

	MockBook input;

	@InjectMocks
	private BookServices services;

	@Mock
	BookRepository repository;

	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockBook();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		var result = services.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author1", result.getAuthor());
		assertEquals("Some Title1", result.getTitle());
		assertEquals(25, result.getPrice());
		assertNotNull(result.getLaunchDate());
	}

	@Test
	void testCreate() {

		Book entity = input.mockEntity(1);
		entity.setId(1L);

		Book persisted = entity;
		persisted.setId(1L);

		BookVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repository.save(entity)).thenReturn(persisted);

		var result = services.create(vo);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author1", result.getAuthor());
		assertEquals("Some Title1", result.getTitle());
		assertEquals(25, result.getPrice());
		assertNotNull(result.getLaunchDate());

	}

	@Test
	void testCreateWithNullBook() {

		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			services.create(null);
		});

		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testUpdate() {

		Book entity = input.mockEntity(1);

		Book persisted = entity;
		persisted.setId(1L);

		BookVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);

		var result = services.update(vo);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author1", result.getAuthor());
		assertEquals("Some Title1", result.getTitle());
		assertEquals(25, result.getPrice());
		assertNotNull(result.getLaunchDate());

	}

	@Test
	void testUpdateWithNullBook() {

		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			services.update(null);
		});

		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testDelete() {

		Book entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		services.delete(1L);

	}

	@Test
	void testFindAll() {

		List<Book> list = input.mockEntityList();

		when(repository.findAll()).thenReturn(list);

		var book = services.findAll();

		assertNotNull(book);
		assertEquals(14, book.size());

		var bookOne = book.get(1);

		assertNotNull(bookOne);
		assertNotNull(bookOne.getKey());
		assertNotNull(bookOne.getLinks());

		assertTrue(bookOne.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author1", bookOne.getAuthor());
		assertEquals("Some Title1", bookOne.getTitle());
		assertEquals(25, bookOne.getPrice());
		assertNotNull(bookOne.getLaunchDate());

		var bookFour = book.get(4);

		assertNotNull(bookFour);
		assertNotNull(bookFour.getKey());
		assertNotNull(bookFour.getLinks());

		assertTrue(bookFour.toString().contains("links: [</api/book/v1/4>;rel=\"self\"]"));
		assertEquals("Some Author4", bookFour.getAuthor());
		assertEquals("Some Title4", bookFour.getTitle());
		assertEquals(25, bookFour.getPrice());
		assertNotNull(bookFour.getLaunchDate());

		var bookSeven = book.get(7);

		assertNotNull(bookSeven);
		assertNotNull(bookSeven.getKey());
		assertNotNull(bookSeven.getLinks());

		assertTrue(bookSeven.toString().contains("links: [</api/book/v1/7>;rel=\"self\"]"));
		assertEquals("Some Author7", bookSeven.getAuthor());
		assertEquals("Some Title7", bookSeven.getTitle());
		assertEquals(25, bookSeven.getPrice());
		assertNotNull(bookSeven.getLaunchDate());

	}

}
