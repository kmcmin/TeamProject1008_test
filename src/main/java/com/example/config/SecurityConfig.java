package com.example.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;


@Configuration
@EnableWebSecurity
public class SecurityConfig { 
	
//	
//	 @Autowired
//	  private UserService service;
//	  
//	  private PasswordEncoder passwordEncoder;
	
//	@Autowired
//	private UserDetailsService userDetailsService;

//	@Bean  
//	public BCryptPasswordEncoder encodePWD() {   // 시큐리티가 들고있는 함수
//	    return new BCryptPasswordEncoder();
//	}
	
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
	    CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
	    accessDeniedHandler.setErrorPage("/denied");
	    return accessDeniedHandler;
	}
	
	/* 로그인 실패 핸들러 의존성 주입 */
	@Autowired
	private AuthenticationFailureHandler customFailureHandler;
//	  
	  protected void configure(HttpSecurity http) throws Exception {
	        http.csrf().disable();
	   }
//	  
	  @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		  //System.out.println("WebSecurityConfig 호출 확인");
		  http  .csrf().disable()
                .authorizeRequests()
                    //여기에 로그인 없이 접근 가능하게할 주소를 쓴다.
                	.antMatchers("/","/main","/productList","/loginUI","/logout","/login123","/childEmail_verification","/mailConfirm","/addBuyerUI","/addBuyer","/main","/resources/**","/resources/css/**","/resources/js/**","/resources/images/**","/resources/img/**","/resources/mapper/**").permitAll()
                	.antMatchers("/cart", "/cartAdd", "/cartUpdate","/cartDel","/cartAllDel","/cartCheckDel",
                    		"/order","/map","/orderDone","/orderList","/orderConfirm","/orderView","/orderDelete","/delCVS",
                    		"/myPage","/logout","/logoutDone").access("hasRole('USER') or hasRole('ADMIN')")
                	 //.anyRequest().permitAll()
//hasAuthority() : 해당 URL에 진입하기 위해서 Authorization(인가, ex)권한이 ADMIN인 유저만 진입 가능)이 필요함
                    // .antMatchers("/security-login/admin/**").hasAuthority(UserRole.ADMIN.name())
                    .and()
                    .formLogin(login -> login
                    .loginPage("/login/UI")
                    //실제 로그인이 처리되는 위치. 
                    .loginProcessingUrl("/login")
                    .usernameParameter("user_id")
                    .passwordParameter("user_pw")                    
                    .defaultSuccessUrl("/main",true)
                    //.failureUrl("/login/UI")
                    .failureHandler(customFailureHandler)/* 로그인 실패 핸들러 */
                    .permitAll()
                    .and())
                    .exceptionHandling()
                    .accessDeniedHandler(accessDeniedHandler())
                    .and()
                    .logout()
                    //.logoutUrl("/logout")
                    .logoutSuccessHandler((request, response, authentication) -> {
                        response.sendRedirect("/app/main");
//                   .and()
//                .logout()
//                    .permitAll();
                    });
		  
	        return http.build();
    }
	  
//
//
//	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	        auth.userDetailsService(userDetailsService).passwordEncoder(encodePWD());
//	    }
//	  
	  
//	  //비밀번호 암호화 관한 내용
//	    @Bean
//	    public BCryptPasswordEncoder getPasswordEncoder(){
//	        return new BCryptPasswordEncoder();
//	    }
	    
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {
			
			chain.doFilter(request, response);
	    }
		
		@Bean public HttpFirewall defaultHttpFirewall() {
	        
			return new DefaultHttpFirewall();
	    }
}

