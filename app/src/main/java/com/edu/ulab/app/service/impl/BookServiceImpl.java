package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.BookEntity;
import com.edu.ulab.app.mapper.BookMapper;
import com.edu.ulab.app.mapper.UserMapper;
import com.edu.ulab.app.service.BookService;
import com.edu.ulab.app.storage.Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookServiceImpl implements BookService {
    private static AtomicLong bookId=new AtomicLong(0);

    private final Repository repository;
    private final BookMapper bookMapper;

    public BookServiceImpl(Repository repository, BookMapper bookMapper) {
        this.repository = repository;
        this.bookMapper = bookMapper;
       }


    @Override
    public BookDto createBook(BookDto bookDto) {
        bookDto.setId(getBookId());

       BookEntity bookEntity = bookMapper.bookDtoToBookEntity(bookDto);
        log.info("Mapped user Dto: {}", bookDto);
        repository.save(bookEntity);
        log.info("Save user to storage: {}", bookEntity);
        return bookDto;
    }

    @Override
    public void  updateBookList(Long userId, List <BookDto> bookDto) {
       repository.deleteBooksByUserId(userId);
        System.out.println("Kbcn "+bookDto);
       repository.updateUserBooks(bookDto.
               stream().map(bookMapper ::bookDtoToBookEntity).collect(Collectors.toList()));
    }

    @Override
    public List <BookDto> getBookById(Long id) {
        return repository.getBooksByIdUser(id).
                stream().map(bookMapper ::bookEntityToBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBookById(Long id) {

    }
    private long getBookId () {
        return bookId.incrementAndGet();
    }
}
