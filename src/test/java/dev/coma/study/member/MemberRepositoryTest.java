package dev.coma.study.member;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.coma.study.member.MemberDTO;
import dev.coma.study.member.MemberRepository;
import dev.coma.study.member.MemberRoleDTO;
import dev.coma.study.member.RoleDTO;
import dev.coma.study.member.RoleRepository;

@SpringBootTest
class MemberRepositoryTest {
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	void test() {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberId("user");
		memberDTO.setMemberPw(passwordEncoder.encode("0000"));
		
		RoleDTO roleDTO = roleRepository.findById(3L).get();
		
		MemberRoleDTO memberRoleDTO = new MemberRoleDTO();
		memberRoleDTO.setMemberDTO(memberDTO);
		memberRoleDTO.setRoleDTO(roleDTO);
		
		List<MemberRoleDTO> list = new ArrayList<>();
		list.add(memberRoleDTO);
		
		memberDTO.setMemberRoleDTOs(list);
		
		memberRepository.save(memberDTO);
	}

}
