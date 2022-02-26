package com.example.mytest.service;

import com.example.mytest.domain.Member;
import com.example.mytest.repository.MemberRepository;
import com.example.mytest.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

//    MemberService memberService = new MemberService();
//    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
    //애매에에에 하다. 2개를 쓸 이유가 없다. 다른 인스턴스 이기 때문에 내용물이 달라지거나 해서 예상치 못하는 오류가 발생할 수 있다.
    //현재 MemoryMemberRepository에 repository가 static으로 등록되어 있기 때문에 문제가 없지만 그게 아니라면 바로 오류 발생한다.
    //다른 repository를 쓰고 있는것이다. 같은 repository 쓰도록 변경해야한다.
    //변경 사항은 MemberService.class 를 참조


    //테스트 할 땐 그냥 편하게 필드인젝션도 함
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    //given:뭔가 주어짐
    //when:이걸 실행했을 때
    //then:결과가 이게 나와야해

    //Test는 정상이 아니라 예외를 테스트 하는게 더 중요하다.
    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("tester");


        //when
        Long savdId = memberService.join(member);

        //then
        //Assertions가 2개가 있음 assertThat은 core에 있음
//        org.assertj.core.api.Assertions
//        org.junit.jupiter.api.Assertions
        Member findMember = memberService.findOne(savdId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void rejectJoin() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
//        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail("예외가 발생해야 합니다.");
//        }catch (IllegalStateException e){
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.asd");
//        }
        //보통 위처럼 할 수 있는데 굉장히 애매하다.

        memberService.join(member1);
//        assertThrows(IllegalStateException.class, ()->memberService.join(member2));
//        assertThrows(NullPointerException.class, ()->memberService.join(member2));
        //위 주석에서 Ctrl+Art+V
        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(illegalStateException.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then
    }

}