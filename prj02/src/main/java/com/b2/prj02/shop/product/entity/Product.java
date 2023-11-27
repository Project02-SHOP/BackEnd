package com.b2.prj02.shop.product.entity;

import com.b2.prj02.shop.category.entity.Category;
import com.b2.prj02.shop.option.entity.Option;
import com.b2.prj02.shop.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.DisabledException;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @javax.persistence.Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_idx")
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "user_idx")
    private User user;

    @Column(name = "product_name")
    private String productName;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_idx")
    private Category category;

    @Column(name = "product_Quantity")
    private Integer productQuantity;

    @Column(name = "register_date")
    private Timestamp registerDate;

    @Column(name = "sale_end_date")
    private Timestamp saleEndDate;

    private String img1;
    private String img2;
    private String img3;

    @OneToMany
    @JoinColumn(name = "option_idx")
    private List<Option> option;


    public void buy(Integer amount) {
        if(productQuantity<amount)
            throw new DisabledException(this.productName + "의 수량이 부족합니다.");
        this.productQuantity -= amount;
    }
}
