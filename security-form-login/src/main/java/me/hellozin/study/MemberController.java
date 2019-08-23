package me.hellozin.study;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/main")
    public String mainPage(@AuthenticationPrincipal User user,
                           Map<String, Object> model) {
        List<Member> members = memberRepository.findAll();
        model.put("members", members);
        model.put("currentMemberId", user.getUsername());
        return "homepage";
    }

    @GetMapping("/admin")
    public String adminPage(@AuthenticationPrincipal User user,
                            Map<String, Object> model) {
        model.put("currentAdminId", user.getUsername());
        return "adminpage";
    }

    @GetMapping("/member/new")
    public String memberJoinForm(Member memberForm) {
        return "memberJoinForm";
    }

    @PostMapping("/member/new")
    public String memberJoin(Member memberForm) {
        memberForm.setPassword(passwordEncoder.encode(memberForm.getPassword()));
        memberRepository.save(memberForm);
        return "redirect:/main";
    }

}
