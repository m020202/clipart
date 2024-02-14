package study.clipart.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {
    @Id @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private Long id;
    private String name;
    private String info;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Post> clipartList = new ArrayList<>();

    public void addClipart(Post post) {
        clipartList.add(post);
    }

    public static Category create(String name, String info) {
        Category category = new Category();
        category.setName(name);
        category.setInfo(info);
        return category;
    }
}
