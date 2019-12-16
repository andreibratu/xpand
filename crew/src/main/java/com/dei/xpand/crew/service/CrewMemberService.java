package com.dei.xpand.crew.service;

import com.dei.xpand.crew.domain.CrewMember;
import com.dei.xpand.crew.repository.CrewMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrewMemberService
{
    private final CrewMemberRepository crewMemberRepository;

    public CrewMemberService(CrewMemberRepository crewMemberRepository) {
        this.crewMemberRepository = crewMemberRepository;
    }

    public List<CrewMember> getAllCrewMembers()
    {
        return crewMemberRepository.findAll();
    }

    public CrewMember createCrewMember(CrewMember crewMember)
    {
        return crewMemberRepository.save(crewMember);
    }
}
