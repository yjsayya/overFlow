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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdBy"),
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Answer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="answer_id")
    private Integer id;

    @Setter @Column(nullable = false, length = 10_000)
    private String content;

    // 연관관계
    @ManyToOne(optional = false)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(optional = false)
    @JoinColumn(name="question_id")
    private Question question;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
    private List<TagOnAnswer> tagOnAnswers = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name="voite_id")
    private Vote vote;

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



    protected Answer() {}

    private Answer(String content) {
        this.content = content;
    }

    public Answer of(String content) {
        return new Answer(content);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(id, answer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}