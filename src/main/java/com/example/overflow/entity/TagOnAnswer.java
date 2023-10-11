package com.example.overflow.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
public class TagOnAnswer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tag_on_answerId")
    private Integer id;

    @ElementCollection
    @Column(name = "tag_name")
    private List<String> tagNames;

    @ManyToOne
    @JoinColumn(name="tag_id")
    private Tag tag;

    @ManyToOne
    @JoinColumn(name="answer_id")
    private Answer answer;

}