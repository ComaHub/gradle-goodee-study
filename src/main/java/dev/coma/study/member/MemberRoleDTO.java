package dev.coma.study.member;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "member_role")
@IdClass(MemberRoleDTO.PK.class)
public class MemberRoleDTO {

	@Id
	@ManyToOne
	@JoinColumn(name = "memberId", insertable = false, updatable = false)
	private MemberDTO memberDTO;
	@Id
	@ManyToOne
	@JoinColumn(name = "roleNum", insertable = false, updatable = false)
	private RoleDTO roleDTO;
	
	@NoArgsConstructor
	@AllArgsConstructor
	@EqualsAndHashCode
	public static class PK implements Serializable {
		private String memberDTO;
		private Long roleDTO;
	}
	
}
