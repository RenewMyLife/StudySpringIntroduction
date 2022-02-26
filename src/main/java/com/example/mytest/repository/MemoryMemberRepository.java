package com.example.mytest.repository;

import com.example.mytest.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {

    //실무에서는 동시성 문제가 있을 수 있어 concurent hashmap 사용
    private static Map<Long, Member> store = new HashMap<>();
    //실무에서는 역시 동시성 문제가 있을 수 있어 atomiclong 사용
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
//        return Optional.empty();
        //오래된 버전
//        return store.get(id);
        //요즘 추세
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
//        return Optional.empty();
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
