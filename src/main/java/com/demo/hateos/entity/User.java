package com.demo.hateos.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@ToString
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    //tax code
    @Column(unique = true, nullable = false)
    private String code;

    private String address;

    @OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    private List<Car> cars = new ArrayList<>();
}
