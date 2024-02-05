package com.swp.spring.interiorconstructionquotation.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "type_product")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TypeProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long type_id;

    @OneToOne
    @JoinColumn(name = "category_id")
    private CategoryProduct categoryProduct;

    @Column(name = "type_name")
    private String type_name;
}
