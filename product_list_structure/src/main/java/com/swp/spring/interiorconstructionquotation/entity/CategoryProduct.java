package com.swp.spring.interiorconstructionquotation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category_product")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryProduct {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    @OneToOne
    @JoinColumn(name = "room_id")
    private TypeRoom typeRoom;

    @Column(name = "category_name")
    private String category_name;
}
