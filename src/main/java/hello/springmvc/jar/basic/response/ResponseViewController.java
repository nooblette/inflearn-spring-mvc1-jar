package hello.springmvc.jar.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {
	@RequestMapping("/response-view-v1")
	public ModelAndView responseViewV1(){
		ModelAndView modelAndView = new ModelAndView("response/hello")
			.addObject("data", "hello!");

		return modelAndView;
	}

	@RequestMapping("/response-view-v2")
	public String responseViewV2(Model model){
		model.addAttribute("data", "hello!");

		// @Controller 애노테이션을 사용하면서 문자열(String)을 반환하면 문자열을 뷰의 논리적 이름으로 인식하여 해당하는 이름을 갖는 뷰(View)를 찾는다.
		return "response/hello";
	}

	// 컨트룰러의 매핑 경로와 뷰의 경로가 동일하다면 View 혹은 문자열을 반환하지 않아도 컨트룰러 매핑 경로에 해당하는 이름의 뷰를 찾는다.
	// 너무 불명확해서 권장하지는 않는 방법
	@RequestMapping("/response/hello")
	public void responseViewV3(Model model){
		model.addAttribute("data", "hello!");
	}
}