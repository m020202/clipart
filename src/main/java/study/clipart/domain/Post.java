package study.clipart.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Post {
    @Id @GeneratedValue
    @Column(name = "POST_ID")
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private String content;

    private LocalDateTime updateDate;

    public static Post create(String name, Member member, Category category, String content) {
        Post post = new Post();
        post.setName(name);
        post.setMember(member);
        post.setCategory(category);
        post.setContent(content);
        post.setUpdateDate(LocalDateTime.now());
        return post;
    }

    public void setCategory(Category category) {
        this.category = category;
        category.addClipart(this);
    }

    public void setMember(Member member) {
        this.member = member;
        member.addPost(this);
    }
}
