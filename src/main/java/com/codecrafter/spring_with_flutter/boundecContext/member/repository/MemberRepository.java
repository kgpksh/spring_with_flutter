package com.codecrafter.spring_with_flutter.boundecContext.member.repository;

import com.codecrafter.spring_with_flutter.boundecContext.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberByUsername(String username);
}
