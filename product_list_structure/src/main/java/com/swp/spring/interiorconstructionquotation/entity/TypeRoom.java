package com.swp.spring.interiorconstructionquotation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "type_room")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TypeRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long room_id;

    @OneToOne(mappedBy = "typeRoom")
    private CategoryProduct categoryProduct;

    @Column(name = "room_name")
    private String room_name;
}
