package study.clipart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.clipart.domain.Member;
import study.clipart.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        checkingDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional
    public void update_name(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }
    @Transactional
    public void update_userId(Long id, String userId) {
        Member member = memberRepository.findOne(id);
        member.setUser_id(userId);
    }
    @Transactional
    public void update_Pwd(Long id, String pwd) {
        Member member = memberRepository.findOne(id);
        member.setPwd(pwd);
    }

    public void checkingDuplicateMember(Member member) {
        Member findMember = memberRepository.findByUsrID(member.getUser_id());
        if (findMember != null) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    public Member findByUserId(String id) {
        return memberRepository.findByUsrID(id);
    }
}
