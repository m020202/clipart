package study.clipart.service;

import org.aspectj.lang.annotation.RequiredTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import study.clipart.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired MemberService memberService;

    @Test
    public void 회원가입() throws Exception {
        Member member = Member.create("ans","m020202", "answjddnr1");

        Long saveId = memberService.join(member);

        assertThat(saveId).isEqualTo(member.getId());
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        Member member1 = Member.create("ans","m020202","answjddnr1");
        Member member2 = Member.create("kim", "victok", "answjddnr1");

        memberService.join(member1);
        memberService.join(member2);
    }

    @Test
    public void 회원_모두_조회() throws Exception {
        Member member1 = Member.create("ans","m020202","answjddnr1");
        Member member2 = Member.create("kim", "victok", "answjddnr1");
        memberService.join(member1);
        memberService.join(member2);

        List<Member> members = memberService.findMembers();

        assertThat(members.size()).isEqualTo(2);
    }

    @Test
    public void 회원_아이디로_조회() throws Exception {
        Member member1 = Member.create("ans","m020202","answjddnr1");
        Member member2 = Member.create("kim", "victok", "answjddnr1");
        memberService.join(member1);
        memberService.join(member2);

        Member findMember = memberService.findByUserId("m020202");

        assertThat(findMember.getName()).isEqualTo("ans");
    }

    @Test
    public void 회원_단건_조회() throws Exception {
        Member member1 = Member.create("ans","m020202","answjddnr1");
        Member member2 = Member.create("kim", "victok", "answjddnr1");
        memberService.join(member1);
        memberService.join(member2);

        Member findMember = memberService.findOne(member2.getId());

        assertThat(findMember.getName()).isEqualTo("kim");
    }

    @Test
    public void 회원수정() throws Exception {
        Member member1 = Member.create("ans","m020202","answjddnr1");
        Member member2 = Member.create("kim", "victok", "answjddnr1");
        memberService.join(member1);
        memberService.join(member2);

        memberService.update_name(member1.getId(),"lee");

        Member findMember = memberService.findOne(member1.getId());

        assertThat(findMember.getName()).isEqualTo("lee");
    }
}