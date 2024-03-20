package hello.springmvc.jar.basic.request;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import hello.springmvc.jar.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RequestBodyJsonController {
	/**
	 * content-Type: application/json
	 * messageBody: {"username": "hello", "age": 20}
	 */

	private ObjectMapper objectMapper = new ObjectMapper(); // 문자열(String)을 객체(Object)로 매핑하기위해 사용

	@PostMapping("/request-body-json-v1")
	public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// HTTP 요청 본문(message body) 내용(JSON 형식)을 InputStream(Byte 기반)으로 가져옴
		ServletInputStream inputStream = request.getInputStream();

		// Byte 기반의 InputStream 형태로 된 HTTP 요청 본문(message body)을 JSON 형식의 문자열(String)으로 변환
		// JSON 형식의 데이터도 결국 문자열로 전달된다.
		String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
		log.info("messageBody={}", messageBody);

		// JSON 형식의 문자열(String)을 HelloData 객체로 변환
		HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
		log.info("helloData={}", helloData);

		response.getWriter().write("ok");
	}

	@ResponseBody
	@PostMapping("/request-body-json-v2")
	public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
		// JSON 형식의 문자열(String)을 HelloData 객체로 변환
		HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
		log.info("helloData={}", helloData);

		return "ok";
	}

	@ResponseBody
	@PostMapping("/request-body-json-v3")
	public String requestBodyJsonV3(@RequestBody HelloData helloData){
		log.info("helloData={}", helloData);

		return "ok";
	}

	@ResponseBody
	@PostMapping("/request-body-json-v4")
	public String requestBodyJsonV4(HttpEntity<HelloData> dataHttpEntity){
		HelloData helloData = dataHttpEntity.getBody();
		log.info("helloData={}", helloData);

		return "ok";
	}

	@ResponseBody
	@PostMapping("/request-body-json-v5")
	public HelloData requestBodyJsonV5(@RequestBody HelloData helloData){
		log.info("helloData={}", helloData);

		// HTTP message convertor는 응답을 내려줄때도 동작한다.
		// 1. HTTP message convertor가 HelloData 객체를 JSON 형식의 문자열(String)으로 변환해준다.
		// 2. helloData 객체를 문자열(String)로 변환한 결과를 HTTP 응답 message 본문에 그대로 넣어준다.
		return helloData;
	}
}
