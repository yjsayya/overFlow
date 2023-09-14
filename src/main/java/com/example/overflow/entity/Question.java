package org.example.wow;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "content"),
        @Index(columnList = "createdBy"),
        @Index(columnList = "createdAt")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="question_id")
    private Integer id;


    @Setter @Column(nullable = false)
    private String title;

    @Setter @Column(nullable = false, length = 10_000)
    private String content;

    // 연관 관계 메서드
    @ManyToOne(optional = false)
    @JoinColumn(name="member_id")
    private Member member;

//    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
//    private List<Comment> commentList = new ArrayList<>();

//    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
//    private List<Vote> voteList = new ArrayList<>();

//    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
//    private List<Answer> answerList = new ArrayList<>();

//    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
//    private List<TagOnQuestion> tagOnQuestions = new ArrayList<>();


    @CreatedDate
    @Column(nullable = false, updatable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate createdAt;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDate updatedAt;

    @LastModifiedBy
    @Column(nullable = false, length = 100)
    private String updatedBy;


    protected Question() {}

    private Question(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Question of(String title, String content) {
        return new Question(title, content);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}