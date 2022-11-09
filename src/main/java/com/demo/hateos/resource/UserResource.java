package com.demo.hateos.resource;

import com.demo.hateos.dto.CarDTO;
import com.demo.hateos.dto.UserDTO;
import com.demo.hateos.entity.User;
import com.demo.hateos.service.UserServiceImpl;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserResource {

    private UserServiceImpl userService;

    public UserResource(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                  @RequestParam(required = false, defaultValue = "10") Integer size,
                                  @RequestParam(required = false) String[] sort,
                                  @RequestParam(required = false, defaultValue = "asc") String dir) {

        CollectionModel<UserDTO> users = userService.findAll(page, size, sort, dir);
        if (users != null) {
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{code}")
    public ResponseEntity findByCode(@PathVariable String code) {
        UserDTO userDTO = userService.findByCode(code);
        if (userDTO == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/{code}/cars")
    public ResponseEntity findUserCars(@PathVariable String code) {
        CollectionModel<CarDTO> cars = userService.findUserCars(code);
        if (cars != null) {
            return ResponseEntity.ok(cars);
        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity insertUser(@RequestBody User user) {
        UserDTO userDTO = userService.insert(user);
        return ResponseEntity
                .created(userDTO.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(userDTO);
    }
}
