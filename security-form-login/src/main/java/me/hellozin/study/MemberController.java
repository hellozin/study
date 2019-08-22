package me.hellozin.study;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/main")
    public String mainPage() {
        return "Main";
    }

    @GetMapping("/member/new")
    public String memberJoinForm(Member member) {
        return "MemberJoinForm";
    }

    @PostMapping("/member/new")
    public String memberJoin(Member member) {
        /* Member Authentication */
        return "redirect:/main";
    }

}
