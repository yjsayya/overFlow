package com.example.overflow.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Table(indexes = {

})
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="member_id")
    private Long id;

    private String email;
    private String password;
    private String name;

    private String phone;
    private String imageURL;

    @LocalDate

    private boolean isDeleted;


}
