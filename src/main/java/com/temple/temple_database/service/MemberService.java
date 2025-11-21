package com.temple.temple_database.service;

import com.temple.temple_database.dto.MemberRequest;
import com.temple.temple_database.model.Member;
import com.temple.temple_database.model.Pooja;
import com.temple.temple_database.repository.MemberRepository;
import com.temple.temple_database.repository.PoojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Autowired
    private PoojaRepository poojaRepository;
    
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
    
    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }
    
    public Member createMember(MemberRequest request) {
        Pooja pooja = poojaRepository.findById(request.getPoojaId())
                .orElseThrow(() -> new RuntimeException("Pooja not found with id: " + request.getPoojaId()));
        
        Member member = new Member();
        member.setMemberName(request.getMemberName());
        member.setMemberPhoneNo(request.getMemberPhoneNo());
        member.setMemberDetails(request.getMemberDetails());
        member.setDate(request.getDate());
        member.setPooja(pooja);
        
        return memberRepository.save(member);
    }
    
    public Member updateMember(Long id, MemberRequest request) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));
        
        Pooja pooja = poojaRepository.findById(request.getPoojaId())
                .orElseThrow(() -> new RuntimeException("Pooja not found with id: " + request.getPoojaId()));
        
        member.setMemberName(request.getMemberName());
        member.setMemberPhoneNo(request.getMemberPhoneNo());
        member.setMemberDetails(request.getMemberDetails());
        member.setDate(request.getDate());
        member.setPooja(pooja);
        
        return memberRepository.save(member);
    }
    
    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new RuntimeException("Member not found with id: " + id);
        }
        memberRepository.deleteById(id);
    }
}




