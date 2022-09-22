package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.BookEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository {
    void save (BookEntity bookEntity);

    void update (BookEntity bookEntity);

    BookEntity getById(Long bookId);

    void deleteById (Long bookId);

    List <BookEntity> findAll ();

    List <BookEntity> findById (Long userId);

}
