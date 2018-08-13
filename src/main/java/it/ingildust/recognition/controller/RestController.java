package it.ingildust.recognition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import it.ingildust.recognition.model.Response;
//import io.swagger.annotations.ApiResponse;
import it.ingildust.recognition.service.RecognitionService;

public class RestController {

	
	
	@Autowired
	RecognitionService recognitionService;
	
	
	
	@RequestMapping(method = RequestMethod.POST, path = "/point", produces = "application/json")
//	@ApiResponse(code = 200, message = "Success", response = String.class)
	public @ResponseBody Response addPoint(@RequestBody(required = true) String version) {


		return new Response();

	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/space", produces = "application/json")
//	@ApiResponse(code = 200, message = "Success", response = String.class)
	public @ResponseBody Response getSpace() {

		return null;

	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/lines", produces = "application/json")
//	@ApiResponse(code = 200, message = "Success", response = String.class)
	public @ResponseBody Response getLines() {

		return null;

	}
}
