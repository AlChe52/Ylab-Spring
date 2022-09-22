package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.UserEntity;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.mapper.UserMapper;
import com.edu.ulab.app.service.UserService;
import com.edu.ulab.app.storage.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private static AtomicLong userId=new AtomicLong(0);

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setId(getUserId());
        UserEntity userEntity = userMapper.userDtoToUserEntity(userDto);
        log.info("Mapped user Dto: {}", userDto);
        userRepository.save(userEntity);
        log.info("Save user to storage: {}", userEntity);

       return userDto;
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
     checkUser(userId);
      userDto.setId(userId);

        userDto.setId(userId);
        UserEntity userEntity = userMapper.userDtoToUserEntity(userDto);
        userRepository.save(userEntity);
        log.info("Update user success: {}", userEntity);
        return userDto;
    }

    @Override
    public UserDto getUserById(Long id) {
        checkUser(id);
        UserDto userDto = userMapper.userEntityTiUserDto(userRepository.getById(id));
        log.info("Get user by id: {}", id, userDto);
        return userDto;
    }

    @Override
    public void deleteUserById(Long id) {
        checkUser(id);
        userRepository.deleteById(id);
        log.info("Delete user by id: {}", id);
        }


    private long getUserId () {
       return userId.incrementAndGet();
    }

    private void checkUser (Long userId) {
        if (userRepository.getById(userId)==null) {
            throw new NotFoundException("User with id "+ userId +" not found");
        }
    }
}
