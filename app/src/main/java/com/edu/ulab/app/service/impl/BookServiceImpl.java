package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.BookEntity;
import com.edu.ulab.app.mapper.BookMapper;
import com.edu.ulab.app.service.BookService;
import com.edu.ulab.app.storage.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
public class BookServiceImpl implements BookService {
    private static AtomicLong bookId=new AtomicLong(0);

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {

        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }
    @Override
    public BookDto createBook(BookDto bookDto) {
        bookDto.setId(getBookId());
        BookEntity bookEntity = bookMapper.bookDtoToBookEntity(bookDto);
        log.info("Mapped book Dto: {}", bookDto);
        bookRepository.save(bookEntity);
        log.info("Save book to storage: {}", bookEntity);
        return bookDto;
    }

    @Override
    public void  updateBookList(Long userId, List <BookDto> bookDto) {
               bookDto.stream()
                .peek(bookDto1 -> bookDto1.setUserId(userId))
                .map(bookMapper::bookDtoToBookEntity)
                .forEach(bookRepository::update);
         log.info("Update books: {}", bookDto);
     }

    @Override
    public BookDto getBookById(Long id) {
        return bookMapper
                .bookEntityToBookDto(bookRepository.getById(id));

    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);

    }

    @Override
    public List<BookDto> getAllBooks() {
        List <BookEntity> listBooks = bookRepository.findAll();
        return listBooks.stream()
                .map(bookMapper::bookEntityToBookDto)
                .toList();
    }

    @Override
    public List<BookDto> getBooksByUserId(Long userId) {
        List <BookEntity> listBooks = bookRepository.findById(userId);
        return listBooks.stream()
                .map(bookMapper::bookEntityToBookDto)
                .toList();
    }

    @Override
    public void deleteBooksByUserId(Long userId) {
         getBooksByUserId(userId)
        .stream()
                 .map(x-> x.getId())
                 .forEach(bookRepository::deleteById);

    }

    private long getBookId () {
        return bookId.incrementAndGet();
    }
}
