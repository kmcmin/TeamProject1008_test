package com.example.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.dto.UserDTO;
import com.example.service.EmailService;
import com.example.service.KakaoService;
import com.example.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService service;
	
	@Autowired
	KakaoService kakaoService;

	@Autowired
	EmailService emailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping("/test")
	public String test() {
		return "NewFile";
	}
	//기능: 회원가입폼으로 이동
	@RequestMapping("/register/addBuyerUI")
	public String addBuyerUI(HttpSession session){
		if(session.getAttribute("login")!=null) {
			session.setAttribute("mesg", "이미 가입된 회원입니다.");
//			return "redirect:main";
			return "main";
		}
		return"user/Buyer";
	}//method
	
	//기능: 회원가입
	@RequestMapping(value = "/register/addBuyer",method = RequestMethod.POST)
	public ModelAndView addBuyer(UserDTO dto,HttpSession session){
		//비밀번호 단방향 암호화
		dto.setUser_pw(passwordEncoder.encode(dto.getUser_pw()));
		
		//회원가입 실패는 나중에 필요 시 구현.
		service.insertBuyer(dto);
		if(dto.getUser_nickname().length()==0) {
			service.insertNcknm(dto);
		}
		ModelAndView mav = new ModelAndView();
		session.setAttribute("mesg", "회원가입이 완료되었습니다.");
		mav.setViewName("redirect:/main");
		return mav;
	}//method
	
	//기능: 로그인폼으로 이동
	@GetMapping("/login/UI")
	public String loginUI(HttpSession session,HttpServletRequest request){
		if(	session.getAttribute("login")!=null) {
			session.setAttribute("mesg", "이미 로그인된 회원입니다.");
		return("redirect:/main");
		}
	    
		return "login/login";	
	}//method
	
	
	/* 로그인 에러메세지 처리*/
	@GetMapping("/authlogin")
	public String login(@RequestParam(value = "exception", required = false) String exception,
						HttpSession session) {
		
		session.setAttribute("warning", exception);
		return("redirect:/login/UI");
	}
	@GetMapping("/denied")
    public String accessDenied(@RequestParam(value = "exception",required = false) String exception,
    		HttpSession session){
		session.setAttribute("warning", exception);
		return("redirect:/login/UI");
    }
	
	
	//기능: 회원 로그인. // form 통해 넘어왔다. 
	//remember: 쿠키 - 아이디 저장 
//	@RequestMapping(value = "/login/Member",method = RequestMethod.POST)
//	public ModelAndView loginMember(
//						
//			HttpSession session,HttpServletRequest request,HttpServletResponse response,
//			String user_id,String user_pw
//			//@RequestParam(name="remember",required = false)boolean remember
//			 ){
//			ModelAndView mav = new ModelAndView();
//			UserDTO dto = new UserDTO(user_id,user_pw);
//			//로그인해서 객체에 정보담기
//			//db에서 비번 값 꺼내오기
//			String EncodePw = service.pwForLogin(user_id);
//			
//			//입력 비번 값이 암호화된 비번의 값과 같다면
//			//로그인 성공
//			
//			//여기 문법 잘 기억하기
//			if(passwordEncoder.matches(user_pw,EncodePw)) {
//				dto = service.selectBuyerByid(user_id);
//				//로그인 한 회원정보 session에 저장
//				session.setAttribute("login", dto);
////				if(remember) { //아이디 저장 클릭했을 경우 쿠키에 저장
//					
////					Cookie c = new Cookie("user_id",user_id);
////					c.setMaxAge(60*60*24*100); //100일
////					response.addCookie(c);//응답 통해 보내기
////				}else { //아이디 저장 클릭 안했으면 쿠키 취소
////					//user_id에 무슨 값을 넣어줘도 어차피 사라지지만 혹시 모르니까 null 지정
////					Cookie c = new Cookie("user_id",null);
////					c.setMaxAge(0); //쿠키의 유효시간을 만료시켜 삭제	
////					response.addCookie(c);
////					}//else
//				mav.setViewName("redirect:/main");
////				mav.setViewName("main");
//			}else {//로그인 실패
//				session.setAttribute("warning", "아이디/비밀번호 확인해주세요");
//				mav.setViewName("redirect:UI");
//			}
//			return mav;
//		}//method
//시큐리티 사용 시 사용 안해도 될 듯함 	
//	//기능: 로그아웃. 일반+카카오 로그아웃 통합.
//	@RequestMapping("/logout")
//  	public ModelAndView logout(HttpSession session) {
//		System.out.println("여기 실행되나?");
//  		ModelAndView mav = new ModelAndView(); 
//  		if(session.getAttribute("code")==null) {//카카오유저가 아니라면
//  			session.invalidate();
//  			mav.setViewName("redirect:/main");
//  		}
//  		else {
//    	mav.setViewName("redirect:https://kauth.kakao.com/oauth/logout?client_id=7a1205ee947ccd366f1de0b2e595c6d5" 
//    			+"&logout_redirect_uri=http://localhost:8093/app/logout/Done");
//  		}
//  		
//  		return mav;
//  	}
//		//기능: 카카오 로그아웃 마무리. 카카오 로그아웃 시 호출됨
//		@RequestMapping("/logout/Done")
//		public ModelAndView logoutDone(HttpSession session) {
//		//넘어와서 session 없애줘야 주소 우회 시에도 정상 작동
//		ModelAndView mav = new ModelAndView(); 
//		session.invalidate();
//		mav.setViewName("redirect:/main");
//		return mav;
//	}
//		//기능: 아이디 찾기 페이지로 이동
//		@RequestMapping("/login/findIdUI")
//		public String findIdUI()
//		{	
//			return "login/FindId";
//		}
		
		//기능: 아이디 찾기 페이지 통해서 아이디 찾기
		@PostMapping("/login/findId")
		public ModelAndView findId(String user_name,String email1,String email2)
		{	
			//이름과 이메일이 맞는게 있으면 아이디를 보여준다.
			String user_email = email1+"@"+email2;
			ModelAndView mav = new ModelAndView(); 
			
			//이름과 이메일 통해 아이디 찾기
			UserDTO dto = new UserDTO();
			dto.setUser_name(user_name);
			dto.setUser_email(user_email);
		
			if(service.findId(dto)==null) {
				String str =  "확인된 정보가 없습니다.";
				str += 	"<br><a href='/app/login/findIdUI'>아이디 찾기</a><br>";
				mav.addObject("findId",str);
			
			} else{//dto 값은 있는데 카카오 계정이라면
				dto = service.findId(dto);
				if(dto.getUser_type().equals("kakao")) {
					mav.addObject("findId", "카카오 간편 로그인으로 가입한 계정입니다.");
					
					
					//dto값이 카카오 계정이 아니면
				}else if(dto.getUser_type().equals("member")){
				mav.addObject("findId", "고객님의 아이디는 "+dto.getUser_id()+"입니다.");
				}//else if
				else {
					String str =  "회원 유형을 확인해주세요";
					str += 	"<br><a href='/app/login/findIdUI'>아이디 찾기</a><br>";
					mav.addObject("findId",str);
				
				}
			}//else 
			mav.setViewName("login/FindId");
			return mav;
		}//method
		
		//기능: 비밀번호 찾기 페이지로 이동
		@RequestMapping("/login/findPwUI")
		public String findPwUI()
		{
			return "login/FindPw";
		}
		//기능: 비밀번호 찾기 - 비밀번호 재설정 페이지로 이동
		@PostMapping("/login/findPw")
		public ModelAndView findPw(HttpSession session,String user_id,String email1,String email2)
		{
			ModelAndView mav = new ModelAndView();
			String user_email = email1+"@"+email2;
			UserDTO dto = new UserDTO();
			dto.setUser_id(user_id);
			dto.setUser_email(user_email);
			//파싱 확인 끝.
			dto = service.findPw(dto);
			//맞는게 있으면 id 정보를 저장하고
			if(dto!=null) {
				mav.addObject("user_id",user_id);
				mav.setViewName("login/resetPw");
			}else {
				mav.setViewName("redirect:findPwUI");
				session.setAttribute("mesg","사용자 정보가 없습니다.");
			}//else
			return mav;
		}

		//기능: 비밀번호 재설정
		@PostMapping("/login/resetPw")
		public ModelAndView resetPw(HttpSession session,String user_id,String user_pw)
		{
			ModelAndView mav = new ModelAndView();
			UserDTO dto = new UserDTO();
			dto.setUser_id(user_id);
			dto = service.selectBuyerByid(user_id);
			//System.out.println("selectById하고 "+dto);
			//id를 기준으로 정보를 찾아오는데 그 값이 없으면
			if(dto==null) {
				session.setAttribute("mesg","사용자 정보가 없습니다. 다시 시도해주세요.");
			}else { //있으면 정상 진행
				//비밀번호 단방향 암호화
				user_pw = passwordEncoder.encode(user_pw);
				dto.setUser_pw(user_pw);
				//비밀번호 재설정
				service.updatePw(dto);
				mav.addObject("user_id","아이디 확인 완료");
				session.setAttribute("mesg","비밀번호 재설정이 완료되었습니다.");
			}//else
			mav.setViewName("login/resetPw");
			return mav;
		}

		//기능: 이메일 인증코드 발송
		@ResponseBody
		@PostMapping("/mailConfirm")
//	    public ResponseEntity<String> sendVerificationEmail(@RequestParam String toEmail) {
			public ResponseEntity<String> sendVerificationEmail(String user_email,HttpServletRequest request) {
	       
			try {
	            String confirmationCode = emailService.sendSimpleMessage(user_email);
	            return ResponseEntity.ok("Verification email sent with code: " + confirmationCode);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.badRequest().body("Failed to send verification email.");
	        }//catch
	    }//method
		
		@RequestMapping("/childEmail_verification")
		//기능: 이메일 인증 창으로 이동
		public ModelAndView childEmail_verification(String emailcode) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("emailcode",emailcode);
			mav.setViewName("user/childEmail_verification");
			return mav;
		}
	
}
