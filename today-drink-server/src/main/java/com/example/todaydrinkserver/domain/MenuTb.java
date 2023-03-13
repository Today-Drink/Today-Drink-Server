package com.example.todaydrinkserver.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="menu_tb")
@Getter
@Setter
public class MenuTb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long menuId;

    @Column
    private String menuName;
    @Column
    private int menuPrice;
    @Column
    private String menuImage;
    @Column
    private String shopName;

}
