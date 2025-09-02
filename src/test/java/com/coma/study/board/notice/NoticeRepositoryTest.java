package com.coma.study.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class NoticeRepositoryTest {
	@Autowired
	private NoticeRepository noticeRepository;
	
	@Test
	void test() throws Exception {
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setBoardTitle("여기는 ㅇㅅㅇ하는 사람 없나?");
		noticeDTO.setBoardContent("ㅇㅅㅇ");
		noticeDTO.setBoardWriter("ㅇㅇ");

//		List<NoticeFileDTO> list = new ArrayList<>();
//		
//		NoticeFileDTO noticeFileDTO = new NoticeFileDTO();
//		noticeFileDTO.setSavedName("Test Save 1");
//		noticeFileDTO.setOriginName("Test Origin 1");
//		noticeFileDTO.setNoticeDTO(noticeDTO);
//		list.add(noticeFileDTO);
//		
//		noticeFileDTO = new NoticeFileDTO();
//		noticeFileDTO.setSavedName("Test Save 2");
//		noticeFileDTO.setOriginName("Test Origin 2");
//		noticeFileDTO.setNoticeDTO(noticeDTO);
//		list.add(noticeFileDTO);
//		
//		noticeDTO.setNoticeFileDTOs(list);
		NoticeDTO result = noticeRepository.save(noticeDTO);
		
		assertNotNull(result);
	}
	
	@Test
	void test2() {
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setBoardNum(2L);
		
		noticeDTO = noticeRepository.findById(noticeDTO.getBoardNum()).get();
		
		log.info("{}", noticeDTO.getBoardTitle());
		log.info("{}", noticeDTO.getNoticeFileDTOs().getFirst().getSavedName());
		
		assertNotNull(noticeDTO);
	}
	
	@Test
	void test3() {
		noticeRepository.deleteById(1L);
	}
	
	@Test
	void test4() {
		for (int i = 0; i < 100; i++) {
			NoticeDTO noticeDTO = new NoticeDTO();
			noticeDTO.setBoardTitle("제 " + i + "의 아해가 무섭다고 그리오.");
			noticeDTO.setBoardContent("아해가 도로로 질주하오.");
			noticeDTO.setBoardWriter("이상");
			
			noticeRepository.save(noticeDTO);
		}
	}
	
	@Test
	void test5() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("boardNum").descending());
		
		List<NoticeDTO> result = noticeRepository.findByBoardTitleLike("%1_의%", pageable);
		result.forEach((dto) -> {
			log.info(dto.getBoardTitle());
		});
	}

}
