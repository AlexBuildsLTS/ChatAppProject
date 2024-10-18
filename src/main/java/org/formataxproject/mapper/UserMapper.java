package org.formataxproject.mapper;

import org.formataxproject.dto.UserDTO;
import org.formataxproject.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);
}
