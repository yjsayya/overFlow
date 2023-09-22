package com.example.overflow.entity;

import com.example.overflow.auditingEntity.AuditingEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "content"),
        @Index(columnList = "createdBy"),
        @Index(columnList = "createdAt")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Question extends AuditingEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="question_id")
    private Integer questionId;

    @Setter @Column(nullable = false)
    private String title;

    @Setter @Column(nullable = false, length = 10_000)
    private String content;

    @Column(nullable = false)
    private int questionViews; //조회수

    @Column(nullable = false)
    private int questionVotes; //투표수

    @Column(nullable = false)
    private int answerCount = 0; // 답변 개수

    // 연관 관계 메서드
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

//    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
//    private List<Comment> commentList = new ArrayList<>();

//    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
//    private List<Vote> voteList = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answerList = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<TagOnQuestion> tagOnQuestions = new ArrayList<>();

    private Question(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(questionId, question.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId);
    }

    public void addAnswersCount() {
        answerCount += 1;
    }

    public void subtractAnswersCount() {
        answerCount -= 1;
    }

}