package hello.springmvc.jar.basic.request;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RequestBodyStringController {
	@PostMapping("/request-body-string-v1")
	public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletInputStream inputStream = request.getInputStream(); // 스트림은 byte code 기반으로 이루어져 있음
		String messageBody = StreamUtils.copyToString(inputStream,
			StandardCharsets.UTF_8);// byte code 기반인 스트림을 어떤 인코딩 방식(e.g. UTF-8)으로 변환하는지 명시한다.

		log.info("messageBody={}", messageBody);

		response.getWriter().write("ok");

	}

	@PostMapping("/request-body-string-v2")
	public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
		// 서블릿(HttpServletRequest) 자체가 필요한게 아니라 그 중 InputStream 클래스만 필요하므로 InputStream 클래스를 매개변수로 바로 받는다.
		String messageBody = StreamUtils.copyToString(inputStream,
			StandardCharsets.UTF_8);// byte code 기반인 스트림을 어떤 인코딩 방식(e.g. UTF-8)으로 변환하는지 명시한다.

		log.info("messageBody={}", messageBody);

		// 응답 또한, 서블릿(HttpServletResponse) 자체가 필요한게 아니므로 Writer 클래스를 바로 받는다.
		responseWriter.write("ok");
	}

	@PostMapping("/request-body-string-v3")
	public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity){
		// HttpEntity<String> : 스프링이 알아서 http message Body에 있는 코드를 String으로 바꿔서 전달해준다(message Convertor 동작)
		// 아래 코드를 스프링이 대신 수행해준다.
		// - String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
		String body = httpEntity.getBody();
		log.info("messageBody={}", body);

		return new HttpEntity<>("ok");
	}

	@ResponseBody // @ResponseBody : requestBodyStringV4 메서드가 반환하는 문자열을 HTTP 응답 메시지 본문에 바로 넣는다.
	@PostMapping("/request-body-string-v4")
	public String requestBodyStringV4(@RequestBody String body /* @RequestBody : HTTP 요청 메시지 본문을 바로 받는다*/){
		log.info("messageBody={}", body);

		return "ok";
	}
}
