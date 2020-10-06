package edu.spring.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.spring.mvc.service.AddService;

@Controller
public class AddController {

	@RequestMapping("/add")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
		int a = Integer.parseInt(request.getParameter("t1"));
		int b = Integer.parseInt(request.getParameter("t2"));
		
		//compute thre result from a different service
		AddService addService = new AddService();
		int  k  = addService.add(a, b);
		
		
		ModelAndView mv= new ModelAndView();
		mv.setViewName("display.jsp");
		mv.addObject("result", k);
		return mv;
	}
}
