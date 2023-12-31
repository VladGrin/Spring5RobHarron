package com.apress.prospring5.ch16.web;

import com.apress.prospring5.ch16.entities.Singer;
import com.apress.prospring5.ch16.services.SingerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/singers")
public class SingerController {

	@Autowired SingerService singerService;

	@GetMapping
	public String list(Model uiModel) {
		log.info("Listing singers");
		List<Singer> singers = singerService.findAll();
		uiModel.addAttribute("singers", singers);
		log.info("No. of singers: " + singers.size());
		return "singers";
	}

	@GetMapping(value = "/{id}")
	public String show(@PathVariable("id") Long id, Model uiModel) {
		Singer singer = singerService.findById(id);
		uiModel.addAttribute("singer", singer);
		return "show";
	}

	@GetMapping(value = "/edit/{id}")
	public String updateForm(@PathVariable Long id, Model model) {
		model.addAttribute("singer", singerService.findById(id));
		return "update";
	}

	@GetMapping(value = "/new")
	public String createForm(Model uiModel) {
		Singer singer = new Singer();
		uiModel.addAttribute("singer", singer);
		return "update";
	}

	@PostMapping
	public String saveSinger(@Valid Singer singer) {
		singerService.save(singer);
		return "redirect:/singers/" + singer.getId();
	}
}
