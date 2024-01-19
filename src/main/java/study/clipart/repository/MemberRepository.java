package study.clipart.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.clipart.domain.Member;
import study.clipart.domain.QMember;

import java.util.List;

import static study.clipart.domain.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return jpaQueryFactory.selectFrom(member).fetch();
    }

    public Member findByUsrID(String userId) {
        Member findMember = jpaQueryFactory.selectFrom(member)
                .where(member.user_id.eq(userId)).fetchOne();
        return findMember;
    }
}
