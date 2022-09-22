package com.edu.ulab.app.mapper;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.UserEntity;
import com.edu.ulab.app.web.request.UserRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-22T15:36:53+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.4 (Private Build)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto userRequestToUserDto(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setFullName( userRequest.getFullName() );
        userDto.setTitle( userRequest.getTitle() );
        userDto.setAge( userRequest.getAge() );

        return userDto;
    }

    @Override
    public UserRequest userDtoToUserRequest(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserRequest userRequest = new UserRequest();

        userRequest.setFullName( userDto.getFullName() );
        userRequest.setTitle( userDto.getTitle() );
        userRequest.setAge( userDto.getAge() );

        return userRequest;
    }

    @Override
    public UserEntity userDtoToUserEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        if ( userDto.getId() != null ) {
            userEntity.setId( userDto.getId() );
        }
        userEntity.setFullName( userDto.getFullName() );
        userEntity.setTitle( userDto.getTitle() );
        userEntity.setAge( userDto.getAge() );

        return userEntity;
    }

    @Override
    public UserDto userEntityTiUserDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( userEntity.getId() );
        userDto.setFullName( userEntity.getFullName() );
        userDto.setTitle( userEntity.getTitle() );
        userDto.setAge( userEntity.getAge() );

        return userDto;
    }
}
