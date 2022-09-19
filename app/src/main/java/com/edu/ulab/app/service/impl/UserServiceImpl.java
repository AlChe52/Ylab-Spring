package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.UserEntity;
import com.edu.ulab.app.mapper.UserMapper;
import com.edu.ulab.app.service.UserService;
import com.edu.ulab.app.storage.Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final Repository repository;
    private final UserMapper userMapper;

    private static AtomicLong userId=new AtomicLong(0);

    public UserServiceImpl(Repository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }


    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setId(getUserId());

        UserEntity userEntity = userMapper.userDtoToUserEntity(userDto);
        log.info("Mapped user Dto: {}", userDto);
        repository.save(userEntity);
        log.info("Save user to storage: {}", userEntity);

          // сгенерировать идентификатор
        // создать пользователя
        // вернуть сохраненного пользователя со всеми необходимыми полями id
       return userDto;
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        UserEntity userEntity = userMapper.userDtoToUserEntity(userDto);
        UserDto updateUser = userMapper.userEntityTiUserDto(repository.update(userId,userEntity));
        return updateUser;
    }

    @Override
    public UserDto getUserById(Long id) {

        UserDto userDto = userMapper.userEntityTiUserDto(repository.getUserById(id));
        System.out.println(userDto);

        return userDto;
    }

    @Override
    public void deleteUserById(Long id) {
        repository.deleteUserWithBooks(id);

        }


    private long getUserId () {

        return userId.incrementAndGet();
    }
}
