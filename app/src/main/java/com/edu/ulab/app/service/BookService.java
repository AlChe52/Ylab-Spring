package com.edu.ulab.app.service;


import com.edu.ulab.app.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto createBook(BookDto userDto);

    void updateBookList (Long userId, List<BookDto> userDto);

    List<BookDto> getBookById(Long id);

    void deleteBookById(Long id);
}
