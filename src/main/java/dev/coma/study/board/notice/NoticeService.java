package dev.coma.study.board.notice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {
	@Autowired
	private NoticeRepository noticeRepository;
	
	public Page<NoticeDTO> getNoticeList(Pageable pageable) throws Exception {
		Page<NoticeDTO> noticeList = noticeRepository.findAll(pageable);
		
		return noticeList;
	}
	
	public NoticeDTO getNoticeDetail(Long boardNum) throws Exception {
		Optional<NoticeDTO> result = noticeRepository.findById(boardNum);
		
		return result.orElseThrow();
	}

	public NoticeDTO addNotice(NoticeDTO noticeDTO) {
		return noticeRepository.saveAndFlush(noticeDTO);
	}
}
