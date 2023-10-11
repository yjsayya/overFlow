package com.example.overflow.entity;

import com.example.overflow.auditingEntity.AuditingEntity;
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
        @Index(columnList = "content"),
        @Index(columnList = "createdBy"),
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Comment extends AuditingEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Integer id;

    @Setter @Column(nullable = false)
    private String content;


    // 연관 메서드
    @ManyToOne(optional = false)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(optional = false)
    @JoinColumn(name="question_id")
    private Question question;



    protected Comment() {}

    private Comment(String content) {
        this.content = content;
    }

    public Comment of(String content) {
        return new Comment(content);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}