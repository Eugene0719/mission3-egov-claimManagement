package egovframework.example.sample.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import egovframework.example.sample.service.TestService;
import egovframework.example.sample.service.TestVo;

@Controller
public class TestController {
	@Autowired
	private TestService service;

	@GetMapping("/list.do")
	public String list(Model model) throws Exception {
		List<TestVo> list = service.list();
		model.addAttribute("list", list);
		return "/sample/list";
	}
}
