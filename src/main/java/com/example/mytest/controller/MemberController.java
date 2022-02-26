package com.example.mytest.controller;

import com.example.mytest.domain.Member;
import com.example.mytest.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    //여러 곳에서 MemberService를 가져다 쓸 것인데 이걸 여기서 따로 New 해서 생성할 필요가 없다.
//    private final MemberService memberService = new MemberService();
    //그래서 container로 등록한다.
    private final MemberService memberService;

    //자동으로 연결을 시켜주는 것이지 자동으로 등록까지 해어 주진 않는다.
    //그래서 MemberService를 @Service Annotaion을 통해 Spring이 올라올 때 service임을 인지하고 spring container에 memberService를 등록한다.
    //위 작업을 하고 나서 실행 시키면 spring bean에 등록되어 있는 MemberService 객체를 가져와서 넣어준다.(DI)
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    //스프링 빈을 등록하는 방법 2가지
    //Component 스캔

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
