package com.b2.prj02.order.entity;

import com.b2.prj02.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order")
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

    private Integer amount;
    private Double price;

    private String option;


    public void setOption(String option) {
        this.option = option;
    }
}
