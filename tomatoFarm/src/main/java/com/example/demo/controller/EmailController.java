package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.EmailPostDTO;
import com.example.demo.domain.EmailResponseDTO;
import com.example.demo.entity.EmailMessage;
import com.example.demo.service.EmailServiceNew;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sendmail")
@RequiredArgsConstructor
public class EmailController {
//    private final EmailService emailService;
	private final EmailServiceNew emailService;

	// 임시 비밀번호 발급
	@PostMapping("/password")
	public ResponseEntity sendPasswordMail(@RequestBody EmailPostDTO emailPostDto) {
		EmailMessage emailMessage = EmailMessage.builder()
//                .to(emailPostDto.getEmail())
				.to("dydgusc66@naver.com").subject("토마토팜 임시비밀번호 테스트").build();

		emailService.sendMail(emailMessage, "password");

		return ResponseEntity.ok().build();
	}

	// 회원가입 이메일 인증 - 요청 시 body로 인증번호 반환하도록 작성하였음
	@PostMapping("/email")
//    public ResponseEntity sendJoinMail(@RequestBody EmailPostDTO emailPostDto) {
//        EmailMessage emailMessage = EmailMessage.builder()
////                .to(emailPostDto.getEmail())
//                .to("dydgusc66@naver.com")
//                .subject("토마토팜 테스트")
//                .build();
//
//        String code = emailService.sendMail(emailMessage, "email");
//
//        EmailResponseDTO emailResponseDto = new EmailResponseDTO();
//        emailResponseDto.setCode(code);
//
//        return ResponseEntity.ok(emailResponseDto);
//    }

	@GetMapping("/email")
	public void sendtest(@RequestBody EmailPostDTO emailPostDto) {
		EmailMessage emailMessage = EmailMessage.builder()
//             .to(emailPostDto.getEmail())
				.to("dydgusc66@naver.com")
				.subject("토마토팜 테스트")
				.build();

		String code = emailService.sendMail(emailMessage, "email");

		EmailResponseDTO emailResponseDto = new EmailResponseDTO();
		emailResponseDto.setCode(code);
	}

//	@GetMapping("/emailtest")
//	public void emailTest() throws MessagingException {
//		System.out.print("\n**********************]\n");
//		emailService.sendMail("dydgusc66");
//	}
}
