package study.clipart.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Clipart {
    @Id @GeneratedValue
    @Column(name = "CLIPART_ID")
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private LocalDateTime updateDate;

    private String originalFileName;
    private String saveFileName;

    public static Clipart create(String name, Member member, Category category, String originalFileName, String saveFileName) {
        Clipart clipart = new Clipart();
        clipart.setName(name);
        clipart.setMember(member);
        clipart.setCategory(category);
        clipart.setUpdateDate(LocalDateTime.now());
        clipart.setOriginalFileName(originalFileName);
        clipart.setSaveFileName(saveFileName);
        return clipart;
    }

    public void setCategory(Category category) {
        this.category = category;
        category.addClipart(this);
    }

    public void setMember(Member member) {
        this.member = member;
        member.addClipart(this);
    }
}
