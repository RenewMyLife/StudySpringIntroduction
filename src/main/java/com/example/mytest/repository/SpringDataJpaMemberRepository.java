package com.example.mytest.repository;

import com.example.mytest.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//내가 빈에 등록하는게 아니라 JpaRepository 이게 있으면 spring data jpa가 스프링 빈에 자동으로 등록
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
