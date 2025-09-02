package com.coma.study.qna;

import com.coma.study.board.BoardDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "qna")
public class QnaDTO extends BoardDTO {
	private Long boardRef;
	private Long boardStep;
	private Long boardDepth;
}
