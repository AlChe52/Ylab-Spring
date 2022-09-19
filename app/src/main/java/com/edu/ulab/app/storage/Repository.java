package com.edu.ulab.app.storage;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.BookEntity;
import com.edu.ulab.app.entity.UserEntity;
import org.apache.catalina.User;

import java.util.List;

public interface Repository {

     void save(Object entity);

    UserEntity getUserById(Long id);

    List <BookEntity> getBooksByIdUser (Long userId);

    void deleteUserWithBooks (Long userId);

    UserEntity update (Long userId, UserEntity userEntity);

    void deleteBooksByUserId (Long userId);

    void updateUserBooks (List<BookEntity> bookEntityList);




}
