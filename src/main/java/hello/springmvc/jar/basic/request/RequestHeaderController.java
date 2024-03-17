package hello.springmvc.jar.basic.request;

import java.util.Locale;

import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RequestHeaderController {
	@RequestMapping("/headers")
	public String header(
		// 스프링의 애노테이션 기반 컨트룰러를 다양한 파라미터를 전달받을 수 있다(인터페이스로 정형화되지 않음)
		HttpServletRequest request,
		HttpServletResponse response,
		HttpMethod httpMethod,
		Locale locale,
		@RequestHeader MultiValueMap<String, String> headerMap, // HTTP 요청의 모든 헤더를 다 받는다.
		@RequestHeader("host") String host, // HTTP 요청 중 키가 "host"인 헤더의 값만 받는다.
		@CookieValue(value = "myCookie", required = false) String cookie // HTTP 요청 중 쿠키 헤더의 값만 받는다.
	) {
		log.info("request={}", request);
		log.info("response={}", response);
		log.info("httpMethod={}", httpMethod);
		log.info("locale={}", locale);
		log.info("headerMap={}", headerMap);
		log.info("header host={}", host);
		log.info("myCookie={}", cookie);

		return "ok";
	}
}
