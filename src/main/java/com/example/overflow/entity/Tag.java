package com.example.overflow.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Table(indexes = {
        @Index(columnList = "tagName"),
        @Index(columnList = "content"),
})
@Entity
public class Tag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tag_id")
    private Integer id;

    @Setter @Column(nullable = false, length = 50)
    private String tagName;

    @Setter @Column(nullable = false)
    private String content;

    // 연관 관계 매서드
    @OneToMany(mappedBy = "tag")
    private List<TagOnQuestion> tagOnQuestions = new ArrayList<>();

    @OneToMany(mappedBy = "tag")
    private List<TagOnAnswer> tagOnAnswers = new ArrayList<>();




}