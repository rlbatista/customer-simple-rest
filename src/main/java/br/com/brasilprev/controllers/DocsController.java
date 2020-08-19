package br.com.brasilprev.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.annotations.ApiOperation;

@Controller
public class DocsController {
	@ApiOperation(value = "Show docs page (this page)")
	@GetMapping({"/", "/doc", "/docs"})
	public String showSwaagerDocsPage() {
		return "redirect:swagger-ui.html";
	}
}
