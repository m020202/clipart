package study.clipart.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.clipart.domain.Category;
import study.clipart.domain.QCategory;

import java.util.List;

import static study.clipart.domain.QCategory.category;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    public void save(Category category) {
        if (category.getId() == null) em.persist(category);
        em.merge(category);
    }

    public Category findOne(Long id) {
        return em.find(Category.class, id);
    }

    public List<Category> findAll() {
        return jpaQueryFactory.selectFrom(category).fetch();
    }
}
