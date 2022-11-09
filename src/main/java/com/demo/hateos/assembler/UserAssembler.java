package com.demo.hateos.assembler;

import com.demo.hateos.dto.UserDTO;
import com.demo.hateos.entity.User;
import com.demo.hateos.resource.UserResource;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@Component
public class UserAssembler implements RepresentationModelAssembler<User, UserDTO> {

    @Override
    public UserDTO toModel(User entity) {
        UserDTO userDTO = new UserDTO(entity.getName(), entity.getSurname(), entity.getCode(), entity.getAddress());
        userDTO.add(linkTo(methodOn(UserResource.class).findUserCars(entity.getCode())).withRel("cars"));
        userDTO.add(linkTo(methodOn(UserResource.class).findByCode(entity.getCode())).withSelfRel());
        return userDTO;
    }
}
