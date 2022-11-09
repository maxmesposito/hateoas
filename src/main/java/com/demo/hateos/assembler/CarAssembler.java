package com.demo.hateos.assembler;

import com.demo.hateos.dto.CarDTO;
import com.demo.hateos.entity.Car;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


@Component
public class CarAssembler implements RepresentationModelAssembler<Car, CarDTO> {

    @Override
    public CarDTO toModel(Car entity) {
        CarDTO carDTO = new CarDTO(entity.getPlate(), entity.getName());
        return carDTO;
    }
}
