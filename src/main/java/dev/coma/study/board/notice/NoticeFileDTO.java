package dev.coma.study.board.notice;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dev.coma.study.board.BoardFileDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "notice_attach")
public class NoticeFileDTO extends BoardFileDTO {
	@ManyToOne
	@JoinColumn(name = "boardNum")
	@JsonIgnore
	private NoticeDTO noticeDTO;
}
