package com.example.controller;

import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.dto.noticeDTO;
import com.example.service.CustomerServiceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
public class CustomerServiceController {
	//고객센터 메인 페이지 이동
	@Autowired
	CustomerServiceService service;
	@RequestMapping("/cs")
	public String CustomerServiceMain(){
		return "CustomerService/csMain";
	}
	
	//공지사항 페이지 이동
	@RequestMapping("/cs/notice")
	public ModelAndView noticeUI() {
		ModelAndView mav = new ModelAndView();
		//서비스를 통해서 api 처리한 후 jsp에서 출력만 하도록
		mav.setViewName("CustomerService/notice");
		return mav;
	}//method

	//자주 묻는 질문으로 이동
	@RequestMapping("/cs/FAQUI")
	public String FAQUI() {
		return "CustomerService/FAQ";
	}
	
	//1대1 게시판으로 이동
//	@RequestMapping("/mypage/QnAListUI")
//	@RequestMapping("/cs/QnAListUI")
	@RequestMapping("/cs/QnAListUI")
	public String QnAList() {
		return "CustomerService/QnAList";
	}
	
	//공지사항 상세보기 - 넘어가기 전에 출력할 값을 받아와야한다.
	//그러려면 seq 통해서 값을 받아온다.
	@RequestMapping("/cs/noticeRetreive")
	public ModelAndView noticeRetreive(String seq,
		@RequestParam(required = false) String pageIndex,
		HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		//기본적으로 오류날 것을 가정하고 세팅해둔다.
		 mav.setViewName("redirect:notice");
		 session.setAttribute("mesg", "공지사항 오류");
		 
		 //pageIndex가 없으면 상세보기를 통해 넘어온 것이다.
		 //이 경우에는 페이지 인덱스에 해당하는 레코드 10개만 가져오는게 아니라 처음부터 찾아야한다.
		 noticeDTO dto = new noticeDTO();
		if(seq!=null)//seq가 제대로 넘어올 때만 처리
		{	
			
			//페이지인덱스를 통해 해당 페이지의 xml만 가져온다.
			String xmlData = "";
			
			//시퀀스 통해 검색해 noticeDTO에 넣어준다.
			
			//pageIndex가 있으면 searchSeq 사용
			if(pageIndex!=null) {
			xmlData = service.getXmlData("https://www.gogung.go.kr/openApiNoti.do?pageIndex="+pageIndex);
			dto = service.searchSeq(xmlData,seq);
			
			//pageIndex 가 없으면 내용을 키워드로 찾는 함수 재사용 (모든 xml 비교하는 단점)
			}else{
				for(int i = 1;i<=31;i++) {
					//xmlData => html(xml) 통으로 뽑아온다.(레코드 10개)
					xmlData = service.getXmlData("https://www.gogung.go.kr/openApiNoti.do?pageIndex="+i);
					//스위치문 사용 - 선택 타입으로 검색
			        	dto = service.searchSeq(xmlData, seq);
			        	//31번 동안 10개의 레코드씩 검색한다. 검색된 seq가 있으면 탈출, 없으면  
			        	if(dto.getSeq()!=null) {
			        		break;}
				}//for    
			}//else
			//dto에 결과가 잘 들어갔으면 request 통해 상세보기로 넘김
			//dto!=null 로 하면 왜 안될까?
			if(dto.getSeq()!=null) {
				//결과가 완전히 정상 완료되면 세팅한 경고 문구 삭제
				session.removeAttribute("mesg");
				mav.addObject("notice", dto);
				mav.setViewName("CustomerService/noticeRetreive");
				}//if
			}//if
		return mav;
	}//method
	
	//검색했을 경우 notice2로 넘어감.
	@RequestMapping("/cs/searchKeyWord")
	public String searchKeyWord(String type,String keyword,HttpServletRequest request
) {
		if(keyword.length()!=0) {
			//키워드 공백 제거하고 소문자로 설정
			keyword = keyword.trim().toLowerCase();
			ArrayList<ArrayList<noticeDTO>> resultList = new ArrayList<>();
		//한번에 10개의 레코드밖에 못받으므로 다음 페이지를 명시 후 다시 요청해, 모든 레코드를 뽑는다.
		for(int pageIndex = 1;pageIndex<=31;pageIndex++) {
		//xmlData => html(xml) 통으로 뽑아온다.(레코드 10개)
		String xmlData = service.getXmlData("https://www.gogung.go.kr/openApiNoti.do?pageIndex="+pageIndex);

		//xmlData에서 타입과 같은 xml 태그의 정보만 뽑는다.
		//service.getDTO(xmlData);
		
		//스위치문 사용 - 선택 타입으로 검색
		switch (type) {	//비어있지 않을 때만 result에 저장
        case "title": 	if(!service.titleSearch(xmlData, keyword).isEmpty()) {
        				resultList.add(service.titleSearch(xmlData, keyword));}
        	break;
        case "content": if(!service.contentSearch(xmlData, keyword).isEmpty()) {
        				resultList.add(service.contentSearch(xmlData, keyword));}
            break;
        case "full": 	if(!service.fullSearch(xmlData, keyword).isEmpty()) {
        				resultList.add(service.fullSearch(xmlData, keyword));}
            break;
        default:
            System.out.println("cscontontroller: 올바른 옵션을 선택하지 않았습니다.");
            break;
			}//switch
		}//for
		
		
		//arraylist로 두 번 감싸진 결과를 result에 몰아 넣는다.
		ArrayList<noticeDTO> result = new ArrayList<>();
		for (ArrayList<noticeDTO> arrayList : resultList) {
			for (noticeDTO dto : arrayList) {
				result.add(dto);
			}//for
		}//for
	    	request.setAttribute("result", result);
	    	
    	return "CustomerService/notice_search";
		}//if - keyword가 0이 아닐때
		else {//-keyword 입력 없으면 전체 목록 보기로 돌아감
			return "CustomerService/notice";

		}//else
	}//method
	
	
	
}//class
