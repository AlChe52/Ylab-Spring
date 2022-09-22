package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.BookEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BookStorage implements BookRepository{

    private static Map<Long, BookEntity> storageBooks = new ConcurrentHashMap<>();

    @Override
    public void save(BookEntity bookEntity) {
        storageBooks.put(bookEntity.getId(), bookEntity);
      }

    @Override
    public void update(BookEntity bookEntity) {
        Long bookId = bookEntity.getId();
        BookEntity bookUpdate = storageBooks.get(bookId);
        bookUpdate.setAuthor(bookEntity.getAuthor());
        bookUpdate.setUserId(bookEntity.getUserId());
        bookUpdate.setTitle(bookEntity.getTitle());
        bookUpdate.setPageCount(bookEntity.getPageCount());
        storageBooks.put(bookId,bookUpdate);
    }

    @Override
    public BookEntity getById(Long bookId) {
        return storageBooks.get(bookId);
    }

    @Override
    public void deleteById(Long bookId) {
           storageBooks.remove(bookId);
    }

    @Override
    public List<BookEntity> findAll() {
        return storageBooks.values()
                .stream()
                .toList();
    }

    @Override
    public List<BookEntity> findById(Long userId) {

        return storageBooks.values()
                .stream()
                .filter(o-> o.getUserId()==userId)
                .toList();
    }
}

