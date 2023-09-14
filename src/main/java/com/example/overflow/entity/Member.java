package org.example.wow;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {
        @Index(columnList = "email"),
        @Index(columnList = "name"),
})
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Integer id;


    @Setter @Column(nullable = false, length = 50)
    private String email;

    @Setter @Column(nullable = false, length = 50)
    @JsonIgnore
    private String password;

    @Setter @Column(nullable = false, length = 50)
    private String name;

    @Setter @Column(length = 50)
    private String phone;

    @Setter @Column(name="image_url")
    private String imageURL;


    // 연관 관계 매서드
//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private List<Question> questionList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private List<Comment> commentList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private List<Answer> answerList = new ArrayList<>();

//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private List<Vote> voteList = new ArrayList<>();


    @Column(name="is_deleted", nullable = false, length = 10)
    private String isDeleted;

    @CreatedDate @Column(name="created_at")
    private LocalDate createdAt;




}