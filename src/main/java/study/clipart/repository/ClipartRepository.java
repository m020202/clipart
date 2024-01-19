package study.clipart.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.clipart.domain.Clipart;
import study.clipart.domain.QClipart;

import java.util.List;

import static study.clipart.domain.QClipart.clipart;

@Repository
@RequiredArgsConstructor
public class ClipartRepository {
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    public void save(Clipart clipart) {
        if (clipart.getId() == null) em.persist(clipart);
        else em.merge(clipart);
    }

    public Clipart findOne(Long id) {
        return em.find(Clipart.class, id);
    }

    public List<Clipart> findAll() {
        return jpaQueryFactory.selectFrom(clipart).fetch();
    }
}
