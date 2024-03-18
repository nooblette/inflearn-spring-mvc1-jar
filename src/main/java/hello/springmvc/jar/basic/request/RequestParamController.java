package hello.springmvc.jar.basic.request;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RequestParamController {
	@RequestMapping("/request-param-v1")
	public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		Integer age = Integer.parseInt(request.getParameter("age"));
		log.info("name = {}, age = {}", username, age);

		response.getWriter().write("ok");
	}

	@ResponseBody // "ok" 라는 문자열(String)에 해당하는 뷰를 찾지 않고 "ok"를 응답 HTTP message body에 내려준다
	@RequestMapping("/request-param-v2")
	public String requestParam2(
		@RequestParam("username") String memberName,
		@RequestParam("age") int memberAge
		// @RequestParam("HTTP 요청의 key 이름") 타입 변수명 형태
 	) {
		log.info("name = {}, age = {}", memberName, memberAge);
		return "ok"; // @Controller 애노테이션을 사용하면서 문자열(String)을 내리는 경우 뷰의 이름으로 인식하고 문자열의 해당하는 뷰를 찾는다.
	}

	@ResponseBody
	@RequestMapping("/request-param-v3")
	public String requestParam3(
		@RequestParam String username, // HTTP 파라미터 이름과 변수명을 동일하게 사용하는 경우 @RequestParam의 매개변수인 ("HTTP 파라미터 이름")는 생략할 수 있다.
		@RequestParam int age
	) {
		log.info("name = {}, age = {}", username, age);
		return "ok";
	}

	@ResponseBody
	@RequestMapping("/request-param-v4")
	public String requestParam4(String username, int age) { // @RequestParam 애노테이션도 생략할 수는 있다. 하지면 명확하게 HTTP 요청을 받는다는 점을 나타내기 위해 굳이 생략을 안하는 것도 좋다.
		log.info("name = {}, age = {}", username, age);
		return "ok";
	}

	@ResponseBody
	@RequestMapping("/request-param-required")
	public String requestParamRequired(
		@RequestParam(required = true) String username, // HTTP 요청 중 username 파라미터는 필수 값, 없으면 오류 발생
		@RequestParam(required = false) Integer age // HTTP 요청 중 age 파라미터는 선택 값, 없어도 된다.
	) {
		log.info("name = {}, age = {}", username, age);
		return "ok";
	}

	@ResponseBody
	@RequestMapping("/request-param-default")
	public String requestParamDefault(
		@RequestParam(required = true, defaultValue = "guest") String username, // HTTP 요청 중 username 파라미터가 누락하면 기본값(defaultValue)를 넣어준다.
		@RequestParam(required = false, defaultValue = "-1") int age
	) {
		log.info("name = {}, age = {}", username, age);
		return "ok";
	}

	@ResponseBody
	@RequestMapping("/request-param-map")
	public String requestParamMap(@RequestParam Map<String, Object> paramMap) { // 모든 요청 파라미터를 Map으로 받는다.
		log.info("name = {}, age = {}", paramMap.get("username"), paramMap.get("age"));
		return "ok";
	}
}
