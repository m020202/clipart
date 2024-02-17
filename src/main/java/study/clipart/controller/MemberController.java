package study.clipart.controller;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.clipart.domain.Member;
import study.clipart.service.MemberService;


@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/new-member")
    public CreateMemberResponse createMember(@RequestBody @Valid CreateMemberRequest request) {
        Member member = Member.create(request.getName(), request.getUser_id(), request.getPwd());
        memberService.join(member);
        return new CreateMemberResponse(member);
    }

    @GetMapping("/edit-member/{id}")
    public MemberDTO member (@PathVariable("id") Long id) {
        Member findMember = memberService.findOne(id);
        return new MemberDTO(findMember);
    }

    @PostMapping("/edit-member/{id}")
    public EditMemberResponse editMember (@PathVariable("id") Long id, @RequestBody @Valid EditMemberRequest request) {
        memberService.update_name(id, request.getName());
        memberService.update_userId(id, request.getUser_id());
        memberService.update_Pwd(id, request.getPwd());
        Member updateMember = memberService.findOne(id);
        return new EditMemberResponse(updateMember);
    }

    @Data
    static class EditMemberResponse {
        private Long id;
        private String name;
        public EditMemberResponse(Member member) {
            this.id = member.getId();
            this.name = member.getName();
        }
    }

    @Data
    static class EditMemberRequest {
        private String name;
        private String user_id;
        private String pwd;
    }

    @Data
    static class MemberDTO {
        private String name;
        private String user_id;
        private String pwd;

        public MemberDTO(Member member) {
            this.name = member.getName();
            this.user_id = member.getUser_id();
            this.pwd = member.getPwd();
        }
    }

    @Data
    static class CreateMemberResponse {
        private Long id;
        private String name;
        public CreateMemberResponse(Member member){
            this.id = member.getId();
            this.name = member.getName();
        }
    }

    @Data
    static class CreateMemberRequest {
        private String name;
        private String user_id;
        private String pwd;
    }

}
