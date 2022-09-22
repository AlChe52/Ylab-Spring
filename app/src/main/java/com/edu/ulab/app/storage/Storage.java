package com.edu.ulab.app.storage;



import com.edu.ulab.app.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class Storage implements UserRepository {

   private static Map<Long,UserEntity> storageUsers = new ConcurrentHashMap<>();

    @Override
    public void save(UserEntity userEntity) {
        storageUsers.put(userEntity.getId(), userEntity);

    }


    @Override
    public UserEntity getById(Long id) {
        return storageUsers.get(id);
    }

    @Override
    public void deleteById(Long userId) {
        storageUsers.remove(userId);

    }
}
    //todo создать хранилище в котором будут содержаться данные
    // сделать абстракции через которые можно будет производить операции с хранилищем
    // продумать логику поиска и сохранения
    // продумать возможные ошибки
    // учесть, что при сохранеии юзера или книги, должен генерироваться идентификатор
    // продумать что у узера может быть много книг и нужно создать эту связь
    // так же учесть, что методы хранилища принимают друго тип данных - учесть это в абстракции

