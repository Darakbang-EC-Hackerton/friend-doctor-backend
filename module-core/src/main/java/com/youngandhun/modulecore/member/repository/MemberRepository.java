package com.youngandhun.modulecore.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youngandhun.modulecore.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
