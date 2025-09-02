package com.coma.study.member;

import java.time.LocalDate;
import java.util.List;

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
public class MemberDTO {
	@Id
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberEmail;
	@Temporal(TemporalType.DATE)
	private LocalDate memberBirth;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "memberDTO", cascade = CascadeType.ALL)
	private List<MemberRoleDTO> memberRoleDTOs;
}
