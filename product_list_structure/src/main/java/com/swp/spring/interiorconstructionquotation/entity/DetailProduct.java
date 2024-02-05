package com.swp.spring.interiorconstructionquotation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "detail_product")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DetailProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "type_id")
    @NotBlank(message = "Thông tin bắt buộc")
    private TypeProduct typeProduct;

    @Column(name = "width")
    private double width;

    @Column(name = "length")
    private double length;

    @Column(name = "height")
    private double height;

    @Column(name = "unit_price")
    private double unit_price;

    @Column(name = "unit")
    private int unit;

    @OneToMany(mappedBy = "detailProduct")
    private Set<ProductImage> productImages;
}

