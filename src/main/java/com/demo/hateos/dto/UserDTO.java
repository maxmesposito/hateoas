package com.demo.hateos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "users")
@Data
@AllArgsConstructor
public class UserDTO extends RepresentationModel<UserDTO> {

    private String name;

    private String surname;

    private String code;

    private String address;
}
