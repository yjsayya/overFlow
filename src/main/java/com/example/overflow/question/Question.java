package com.example.overflow.question;

import com.example.overflow.auditingEntity.AuditingEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Question")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int questionViews; //조회수

    @Column(nullable = false)
    private int questionVotes; //투표수

    //** 만약 answer랑 1:n 연관 매핑을 한다면
    // @JsonIgnore
    // @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    // private List<Answer> answers = new ArrayList<>();
    // **//

    public void addViews(){
        this.questionViews++;
    }

}