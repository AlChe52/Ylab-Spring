package com.edu.ulab.app.storage;



import com.edu.ulab.app.entity.BookEntity;
import com.edu.ulab.app.entity.UserEntity;
import com.edu.ulab.app.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Storage implements Repository {

    private static List <UserEntity> userEntityList = new ArrayList<>();
    private static List <BookEntity> bookEntityList = new ArrayList<>();

    @Override
    public void save(Object o) {
      if (o instanceof UserEntity) {
        userEntityList.add((UserEntity) o);
          System.out.println(userEntityList);
      }
      if (o instanceof BookEntity)  {
        bookEntityList.add((BookEntity) o);
          System.out.println(bookEntityList);}
            }

    @Override
    public UserEntity getUserById(Long id) {
        UserEntity userEntity =userEntityList.
                stream().filter(x -> x.getId() ==id).findFirst()
                .orElseThrow(() ->new UserNotFoundException("User not found "+id));
        return userEntity;
    }
    @Override
    public List<BookEntity> getBooksByIdUser(Long Id) {
        List <BookEntity> books = bookEntityList.stream()
                .filter(x -> x.getUserId()==Id).
                toList();
        System.out.println(books);
        return books;
    }

    @Override
    public void deleteUserWithBooks(Long userId) {

        List<UserEntity > deleteUser = userEntityList.stream()
                .filter(x-> x.getId()==userId)
                .collect(Collectors.toList());
        userEntityList.removeAll(deleteUser);
        System.out.println(userEntityList);
        deleteBooksByUserId(userId);
    }

    @Override
    public void deleteBooksByUserId (Long userId) {
        List <BookEntity> deleteListBook =bookEntityList.stream()
                .filter(y -> y.getUserId()==userId)
                .collect(Collectors.toList());
        bookEntityList.removeAll(deleteListBook);
        System.out.println(bookEntityList);
    }

    @Override
    public void updateUserBooks(List<BookEntity> updatebookEntityList) {
      bookEntityList.addAll(updatebookEntityList);


    }

    @Override
    public UserEntity update(Long userId, UserEntity userEntity) {
        UserEntity user = getUserById(userId);
        user.setFullName(userEntity.getFullName());
        user.setTitle(userEntity.getTitle());
        user.setAge(user.getAge());
        System.out.println(userEntityList);
         return user;
    }

     private void updateBook (Long userId) {

     }
}
    //todo создать хранилище в котором будут содержаться данные
    // сделать абстракции через которые можно будет производить операции с хранилищем
    // продумать логику поиска и сохранения
    // продумать возможные ошибки
    // учесть, что при сохранеии юзера или книги, должен генерироваться идентификатор
    // продумать что у узера может быть много книг и нужно создать эту связь
    // так же учесть, что методы хранилища принимают друго тип данных - учесть это в абстракции

