package com.example.mytest.repository;

import com.example.mytest.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class MemoryMemberRepositoryTest {
    //    MemberRepository repository = new MemoryMemberRepository();
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //Test는 순서가 보장 되지 않으며 각 테스트는 순서에 의존 관계없이 정산적으로 동작 하여야 한다.
    //현업에서의 관례, MSA 설계를 위한, 그리고 Clean Code를 위한 기본적인 것
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        System.out.println("result = " + (result == member));
//      Assertions.assertEquals(result, member);
        //요즘은 assertThat으로 많이 씀
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member = new Member();
        member.setName("spring1");
        repository.save(member);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);

    }
}
