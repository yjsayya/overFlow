package com.example.overflow.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class TagOnQuestion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tag_on_answerId")
    private Integer id;

    @ElementCollection
    @Column(name = "tag_name")
    private List<String> tagNames;

    // 연관관계 메서드
    @ManyToOne
    @JoinColumn(name="tag_id")
    private Tag tag;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;


}