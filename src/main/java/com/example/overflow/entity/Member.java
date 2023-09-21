package com.example.overflow.entity;

import com.example.overflow.auditingEntity.AuditingEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {
        @Index(columnList = "email"),
        @Index(columnList = "userName"),
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Member extends AuditingEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Integer id;

    @Setter @Column(nullable = false, length = 255)
    private String email;

    @Setter @Column(nullable = false, length = 255)
    @JsonIgnore
    private String password;

    @Setter @Column(nullable = false, length = 255)
    private String userName;

    @Setter @Column(length = 255)
    private String phone;

    @Setter @Column(name="image_url")
    private String imageURL;


    // 연관 관계 매서드
    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Question> questionList = new ArrayList<>();

//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private List<Comment> commentList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private List<Answer> answerList = new ArrayList<>();

//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private List<Vote> voteList = new ArrayList<>();




    @Column(name="is_deleted", length = 10)
    private String isDeleted;


}