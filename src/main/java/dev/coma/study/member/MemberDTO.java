package dev.coma.study.member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "member")
public class MemberDTO implements UserDetails {
	@Id
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberEmail;
	@Temporal(TemporalType.DATE)
	private LocalDate memberBirth;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "memberDTO", cascade = CascadeType.ALL)
	private List<MemberRoleDTO> memberRoleDTOs;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> list = new ArrayList<>();
		this.memberRoleDTOs.forEach((memberRoleDTO) -> {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(memberRoleDTO.getRoleDTO().getRoleName());
			list.add(authority);
		});
		
		return list;
	}

	@Override
	public String getPassword() {
		return memberPw;
	}

	@Override
	public String getUsername() {
		return memberId;
	}
}
