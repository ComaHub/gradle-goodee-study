package com.coma.study.board.notice;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeDTO, Long> {
	
	public List<NoticeDTO> findByBoardTitleLike(String like, Pageable pageable);
	
}
