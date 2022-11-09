package com.demo.hateos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cars")
@Data
@AllArgsConstructor
public class CarDTO extends RepresentationModel<CarDTO> {


    private String plate;

    private String name;

}
