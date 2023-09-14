package org.example.wow;


import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class TagOnQuestion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tag_on_answerId")
    private Integer id;


    // 연관관계 메서드
    @ManyToOne
    @JoinColumn(name="tag_id")
    private Tag tag;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;


}