package com.example.overflow.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class TagOnAnswer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tag_on_answerId")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="tag_id")
    private Tag tag;

    @ManyToOne
    @JoinColumn(name="answer_id")
    private Answer answer;




}