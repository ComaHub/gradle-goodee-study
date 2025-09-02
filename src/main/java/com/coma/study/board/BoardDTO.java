package com.coma.study.board;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass // 테이블을 만들지 않고 상속만 할 때 사용
// @Entity // 해당 DTO를 JPA가 관리하고 있음을 명시
// @Table(name = "notice")
public class BoardDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardNum;
	private String boardTitle;
	private String boardWriter;
	@Lob
	// @Column(columnDefinition = "LONGTEXT") // 위랑 같음
	private String boardContent;
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private LocalDateTime boardDate;
	@Column(insertable = false)
	@ColumnDefault("0")
	private Long boardHit;
	
	@Transient // DB에 column으로 반영되지 않음
	private String kind;
}
