package study.clipart.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;
    private String user_id;
    private String pwd;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();

    public void addPost(Post post) {
        postList.add(post);
    }

    public static Member create(String name, String user_id, String pwd) {
        Member member = new Member();
        member.setName(name);
        member.setUser_id(user_id);
        member.setPwd(pwd);
        return member;
    }
}
