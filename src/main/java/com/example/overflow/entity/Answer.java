package com.example.overflow.entity;

import com.example.overflow.auditingEntity.AuditingEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdBy"),
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Answer extends AuditingEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="answer_id")
    private Integer id;

    @Setter @Column(nullable = false, length = 10_000)
    private String content;

    // 연관관계
//    @ManyToOne(optional = false)
//    @JoinColumn(name="member_id")
//    private Member member;

    @ManyToOne(optional = false)
    @JoinColumn(name="question_id")
    private Question question;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
    private List<TagOnAnswer> tagOnAnswers = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name="voite_id")
    private Vote vote;


}