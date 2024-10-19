package org.formataxproject.mapper;

import org.formataxproject.dto.UserDTO;
import org.formataxproject.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "email", source = "email")
    })
    UserDTO userToUserDTO(User user);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "email", source = "email")
    })
    User userDTOToUser(UserDTO userDTO);
}