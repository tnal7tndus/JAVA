package com.example.demo.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService {
	   private JavaMailSender emailSender;
	   
	    public void sendMail(String id) throws MessagingException {
	        MimeMessage message = emailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);

	        //제목, 내용 설정
	        helper.setSubject("회원 가입 축하드립니다");
	        helper.setText("토마토팜 회원이 되신 걸 축하드립니다", false);

	        // 참조자 설정
	        helper.setCc("tomatofarm01234@gmail.com");
	        
	        // 발신자 설정(연동된 구글 계정으로 고정)
	        helper.setFrom("tomatofarm01234@gmail.com");

	        // 로컬 첨부 파일 설정
//	        File file = new File("파일 경로");
//	        FileItem fileItem = new DiskFileItem("mainFile", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());
//	        InputStream input = new FileInputStream(file);
//	        OutputStream os = fileItem.getOutputStream();
//	        IOUtils.copy(input, os);
//	        MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
//	        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//	        helper.addAttachment(MimeUtility.encodeText(fileName, "UTF-8", "B"), new ByteArrayResource(IOUtils.toByteArray(multipartFile.getInputStream())));
	        
	         // AWS S3 첨부 파일 설정
//	        File file = new File("loginbg.jpeg");
//	        FileUtils.copyURLToFile(new URL("https://s3.ap-northeast-2.amazonaws.com/cloudtechflow.com/image/image/image.jpeg"), file);
//	        FileItem fileItem = new DiskFileItem("mainFile", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());
//	        InputStream input = new FileInputStream(file);
//	        OutputStream os = fileItem.getOutputStream();
//	        IOUtils.copy(input, os);
//	        MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
//	        List<MultipartFile> multipartFileList = Arrays.asList(multipartFile);

	        //메일 전송(setTo 파라미터에 문자열 리스트를 넘기면 한번에 여러명에게 전송 가능)
	        helper.setTo(id+"@naver.com");
	        emailSender.send(message);
	    }
}
