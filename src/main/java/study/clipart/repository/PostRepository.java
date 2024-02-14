package study.clipart.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.clipart.domain.Post;
import study.clipart.domain.QPost;

import java.util.List;

import static study.clipart.domain.QPost.post;


@Repository
@RequiredArgsConstructor
public class PostRepository {
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    public void save(Post post) {
        if (post.getId() == null) em.persist(post);
        else em.merge(post);
    }

    public Post findOne(Long id) {
        return em.find(Post.class, id);
    }

    public List<Post> findAll() {
        return jpaQueryFactory.selectFrom(post).fetch();
    }
}
