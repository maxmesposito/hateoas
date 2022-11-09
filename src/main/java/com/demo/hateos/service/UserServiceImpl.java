package com.demo.hateos.service;

import com.demo.hateos.assembler.CarAssembler;
import com.demo.hateos.assembler.UserAssembler;
import com.demo.hateos.dto.CarDTO;
import com.demo.hateos.dto.UserDTO;
import com.demo.hateos.entity.User;
import com.demo.hateos.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;

    private final UserAssembler userAssembler;

    private final CarAssembler carAssembler;

    private final PagedResourcesAssembler pagedResourcesAssembler;


    public UserServiceImpl(UserRepository userRepository,
                           UserAssembler userAssembler,
                           PagedResourcesAssembler pagedResourcesAssembler,
                           CarAssembler carAssembler) {
        this.userRepository = userRepository;
        this.userAssembler = userAssembler;
        this.carAssembler = carAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    public CollectionModel<UserDTO> findAll(int page, int size, String[] sort, String dir) {
        PageRequest pageRequest;
        Sort.Direction direction;
        if (sort == null) {
            pageRequest = PageRequest.of(page, size);
        } else {
            if (dir.equalsIgnoreCase("asc")) direction = Sort.Direction.ASC;
            else direction = Sort.Direction.DESC;
            pageRequest = PageRequest.of(page, size, Sort.by(direction, sort));
        }
        Page<User> users = userRepository.findAll(pageRequest);
        if (!CollectionUtils.isEmpty(users.getContent())){
            return pagedResourcesAssembler.toModel(users, userAssembler);
        }
        return null;
    }

    public UserDTO findByCode(String code) {
        User user = userRepository.findByCode(code).orElse(null);
        if (user != null) {
            return userAssembler.toModel(user);
        }
        return null;
    }

    public CollectionModel<CarDTO> findUserCars(String code) {
        User user = userRepository.findByCode(code).orElse(null);
        if (user != null && (!CollectionUtils.isEmpty(user.getCars()))) {
            return carAssembler.toCollectionModel(user.getCars());
        }
        return null;
    }

    @Transactional
    public UserDTO insert(User user) {
        user.getCars().forEach(car -> car.setUser(user));
        return userAssembler.toModel(userRepository.save(user));
    }
}
