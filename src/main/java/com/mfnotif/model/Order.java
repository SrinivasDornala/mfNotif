package com.mfnotif.model;

import lombok.*;
import javax.persistence.*;

@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "userid")
    private long userId;
    @Column(name = "name")
    private String name;
    @Column(name ="notify")
    private String notify;
}
