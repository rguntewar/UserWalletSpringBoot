package com.fab.fabH.converter;

import com.fab.fabH.dto.UserDetailDto;
import com.fab.fabH.models.UserDetail;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class UserDetailConverter {

    public UserDetail dtoToEntity (UserDetailDto dto){

        UserDetail user = new UserDetail();

        user.setUsername(dto.getUsername());
        user.setAddress(dto.getAddress());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(Long.parseLong(dto.getPhoneNumber()));
        return user;
    }
    public List<UserDetail> dtoToEntity (List<UserDetailDto> dto){
        List<UserDetail> userList = new ArrayList<>();

        for(UserDetailDto userDto: dto){
            userList.add(dtoToEntity(userDto));
        }
        return  userList;
    }

    public UserDetailDto entityToDto (UserDetail user){
        UserDetailDto dto = new UserDetailDto();

        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setAddress(user.getAddress());
        dto.setPhoneNumber((user.getPhoneNumber()).toString());

        return dto;
    }

    public Iterable<UserDetailDto> entityToDto(Iterable<UserDetail> obj) {
        List<UserDetailDto> userList = new ArrayList<>();

        for(UserDetail user: obj){
            userList.add(entityToDto(user));
        }
        return  userList;
    }
}
