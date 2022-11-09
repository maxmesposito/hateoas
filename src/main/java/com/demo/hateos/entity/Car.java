package com.demo.hateos.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String plate;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
