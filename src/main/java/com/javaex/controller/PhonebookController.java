package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.PhonebookService;
import com.javaex.vo.PersonVo;

@Controller
public class PhonebookController {

	@Autowired
	private PhonebookService phonebookService;
	
	// 생성자
	// 메소드gs

	// 메소드일반/////////////////////////////
	/* 수정 */
	@RequestMapping(value="/edit", method = {RequestMethod.GET, RequestMethod.POST})
	public String edit(@ModelAttribute PersonVo personVo) {
		System.out.println("PhonebookController.edit()");
		
		phonebookService.exeEditPerson(personVo);
		
		return "redirect:/list";
	}
	
	/* 수정폼 */
	@RequestMapping(value="/editform", method = {RequestMethod.GET, RequestMethod.POST})
	public String editForm(@RequestParam(value="no") int no, Model model) {
		System.out.println("PhonebookController.editForm()");
		
		PersonVo personVo = phonebookService.exeEditForm(no);
		model.addAttribute("personVo", personVo);
		
		return "editForm";
	}
	
	
	/* 삭제 */
	@RequestMapping(value="/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam(value="no") int no) {
		System.out.println("PhonebookController.delete()");
		
		phonebookService.exePersonDelete(no);
	
		return "redirect:/list";
	}
	
	/* 리스트 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("PhonebookController.list()");

		List<PersonVo> personList = phonebookService.exeGetPersonList();
		model.addAttribute("personList", personList);
		
		return "list";
	}

	/* 등록폼 */
	@RequestMapping(value = "/writeform", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("PhonebookController.writeForm()");
		
		return "writeForm";
	}

	/* 등록 */
	/* vo로 받을때 */
	@RequestMapping(value = "write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("PhonebookController.write()");

		// phonebookDao의 메소드를 이용해서 저장한다
		//int count = phonebookDao.insertPerson(personVo);
		//System.out.println(count);
	
		phonebookService.exeWritePerson(personVo);
		
		
		// 리스트로 리다이렉트
		/* "redirect:http://localhost:8888/phonebook3/list" */
		return "redirect:/list";
		
	}

	/* 등록 */
	/* 파라미터 1개씩 받을때 */
	@RequestMapping(value = "/write2", method = { RequestMethod.GET, RequestMethod.POST })
	public String write2(@RequestParam(value = "name") String name, @RequestParam("hp") String hp,
			@RequestParam("company") String company) {
		System.out.println("PhonebookController.write2()");

		PersonVo personVo = new PersonVo();
		System.out.println(personVo);

		return "";
	}

	// 리스트

}
