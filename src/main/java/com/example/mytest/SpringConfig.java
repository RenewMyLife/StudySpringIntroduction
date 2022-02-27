package com.example.mytest;

import com.example.mytest.repository.*;
import com.example.mytest.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    //jpa 추가 후 해당 부분 주석 처리 가능, spring data jpa가 자동으로 만들고 등록하기 때문에 인젝션 받을 수 있다.
    //    private final DataSource dataSource;
//    private final EntityManager em;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource, EntityManager em) {
//        this.dataSource = dataSource;
//        this.em = em;
//    }
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(this.memberRepository);

    }
//    @Bean
//    public MemberRepository memberRepository(){
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }


}
