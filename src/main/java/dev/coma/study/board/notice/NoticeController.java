package dev.coma.study.board.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.coma.study.board.BoardDTO;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/notice/**")
@Slf4j
public class NoticeController {
	@Autowired
	private NoticeService noticeService;

	@GetMapping
	public Page<NoticeDTO> getNotice(@PageableDefault(size = 10, sort = "boardNum", direction = Sort.Direction.DESC) Pageable pageable) throws Exception {
		Page<NoticeDTO> noticeList = noticeService.getNoticeList(pageable);
		
		return noticeList;
	}
	
	@GetMapping("{boardNum}")
	public BoardDTO getNoticeDetail(@PathVariable("boardNum") Long boardNum) throws Exception {
		NoticeDTO noticeDTO = noticeService.getNoticeDetail(boardNum);
		
		return noticeDTO;
	}
	
	@PostMapping
	public boolean postNotice(NoticeDTO noticeDTO) {
		noticeDTO = noticeService.addNotice(noticeDTO);
		
		if (noticeDTO != null) return true;
		else return false;
	}
	
	@PutMapping("{boardNum}")
	public void putNotice(@PathVariable("boardNum") Long boardNum) {
		
	}
	
	@DeleteMapping("{boardNum}")
	public void deleteNotice(@PathVariable("boardNum") Long boardNum) {
		
	}
}
