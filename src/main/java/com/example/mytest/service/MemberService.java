package com.example.mytest.service;

import com.example.mytest.domain.Member;
import com.example.mytest.repository.MemberRepository;
import com.example.mytest.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//class에 커서 가져다 놓고 Ctrl+Shift+T 하면 Test 생성해줌
//@Service
public class MemberService {
    //원본
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;

    //위에서 Art+Insert
//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원 가입
     *
     * @param member
     * @return
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원은 안된다.
        //단축키 설명 : Ctrl+Art+V(커맨드+옵션+V)
        //아래 주석 코드 맨 오른쪽에서 위 단축키 쓰면 아래 코드처럼 바뀜
////        memberRepository.findByName(member.getName());
////        Optional<Member> byName = memberRepository.findByName(member.getName());
//        Optional<Member> result = memberRepository.findByName(member.getName());
////        result.ifPresent(m->{
////            throw new IllegalStateException("이미 존재하는 회원입니다.");
////        });
//        //누가 적지만 if == null로는 이제 개발 잘 안한다.
//        //그리고 ifPresent로 바로 쓰는 경우도 드물다.
////        result.orElseGet() <-- 이렇게 많이 씀
//        memberRepository.save(member);

        //위처럼 해도 되지만 현업에서는 코드를 깔끔하게 하기 위해 따로 result 반환은 안하는 경우가 대부분
//        memberRepository.findByName(member.getName())
//                .ifPresent(m -> {
//                    throw new IllegalStateException("이미 존재하는 회원입니다.");
//                });
        //그리고 이렇게 로직이 쭉 나오는 경우는 메소드로 뽑는게 좋다.
        //단축키 설명 : 위 lambda 드래그 후 Ctrl+Art+Shift+T 하면 refactor this 메뉴 나옴
        //내가 쓰는건 Extract Method Ctrl+Art+M
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //service는 business에 의존적이게 네이밍 설계
    //repository는 기계적으로 단순히 개발스럽게 설계

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memgerId) {
        return memberRepository.findById(memgerId);
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }


}
