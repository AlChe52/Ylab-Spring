package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    void save (UserEntity userEntity);



    UserEntity getById(Long userId);

    void deleteById (Long userId);

}
