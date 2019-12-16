package com.dei.xpand.crew.controller;

import com.dei.xpand.crew.domain.CrewMember;
import com.dei.xpand.crew.service.CrewMemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CrewMemberController
{
    private final CrewMemberService crewMemberService;

    public CrewMemberController(CrewMemberService crewMemberService) {
        this.crewMemberService = crewMemberService;
    }

    @RequestMapping(value = "/member", method = RequestMethod.GET)
    public ResponseEntity<?> findAll()
    {
        List<CrewMember> crewMembers = crewMemberService.getAllCrewMembers();
        return ResponseEntity.ok(crewMembers.stream()
                .map(CrewMemberOutputDTO::fromCrewMember)
                .collect(Collectors.toList()));
    }

    @RequestMapping(value = "/member", method = RequestMethod.POST)
    public CrewMemberOutputDTO createCrewMember(@RequestBody @Valid CrewMemberInputDTO dto)
    {
        CrewMember crewMember = CrewMemberInputDTO.toDomainEntity(dto);
        CrewMember result = crewMemberService.createCrewMember(crewMember);
        return CrewMemberOutputDTO.fromCrewMember(result);
    }
}
