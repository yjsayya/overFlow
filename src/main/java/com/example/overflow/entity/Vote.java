package com.example.overflow.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Vote {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vote_id")
    private Integer id;

    @Column
    private int Upvote;

    // 연관 관계 메서드
    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

}