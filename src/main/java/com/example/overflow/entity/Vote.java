package org.example.wow;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Vote {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vote_id")
    private Integer id;

    // 연관 관계 메서드
    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;

    @OneToOne
    private Answer answer;

}