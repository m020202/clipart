package study.clipart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import study.clipart.domain.Member;
import study.clipart.service.MemberService;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm memberForm) {
        Member member = Member.create(memberForm.getName(), memberForm.getUser_id(), memberForm.getPwd());
        memberService.join(member);

        return "redirect:/";
    }
}
