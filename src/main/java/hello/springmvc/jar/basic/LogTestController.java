package hello.springmvc.jar.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LogTestController {

	@RequestMapping("/log-test")
	public String logTest(){
		String name = "Spring";

		log.trace("trace log = {}", name);	// 가장 레벨이 낮은 로그 정보
		log.debug("debug log = {}", name); 	// 개발 서버에서 확인하면 되는 정보
		log.info("info log= {}", name); 	// 운영 시스템에서도 봐야하는 중요한 비즈니스 정보
		log.warn("warn log = {}", name); 	// 잠재적 위험 요소
		log.error("error log = {}", name); 	// 알람을 받고 별도로 파일로 기록하고 분석하여 바로 대응해야하는 위험한 이슈

		// @Controller 애노테이션을 사용하는 경우, 문자열을 반환하면 문자열에 해당되는 이름의 뷰(View)를 찾고 뷰 리졸버가 동작한다.
		// @RestController 애노테이션을 사용하는 경우, 문자열을 반환하면 문자열 그대로 응답 HTTP 본문(body)에 넣고 응답을 내려준다.
		return "ok";
	}
}
