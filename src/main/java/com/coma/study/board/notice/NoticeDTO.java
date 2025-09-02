package com.coma.study.board.notice;

import java.util.List;

import com.coma.study.board.BoardDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "notice")
public class NoticeDTO extends BoardDTO {
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "noticeDTO", cascade = CascadeType.ALL)
	private List<NoticeFileDTO> noticeFileDTOs;
}
