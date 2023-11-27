package com.b2.prj02.shop.order.entity;

import com.b2.prj02.shop.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_order")
public class Order {
    @javax.persistence.Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_idx")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_idx")
    private User user;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "order_date")
    private Timestamp orderDate;

    private Integer amount;
    private Double price;

    @Column(name = "product_option")
    private String option;


    public void setOption(String option) {
        this.option = option;
    }
}
