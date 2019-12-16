package com.dei.xpand.crew.repository;

import com.dei.xpand.crew.domain.CrewMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrewMemberRepository extends JpaRepository<CrewMember, Long>
{
    @Override
    List<CrewMember> findAll();

    @Override
    CrewMember getOne(Long aLong);

    @Override
    <S extends CrewMember> S save(S s);
}
