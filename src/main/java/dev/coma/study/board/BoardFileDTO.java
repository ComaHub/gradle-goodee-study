package dev.coma.study.board;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
public class BoardFileDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fileNum;
	private String originName;
	private String savedName;
}
