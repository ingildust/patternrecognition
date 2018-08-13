package it.ingildust.recognition.controller;

import java.awt.Point;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiResponse;
import it.ingildust.recognition.model.Response;
import it.ingildust.recognition.service.RecognitionService;
import lombok.extern.java.Log;

@Log
@org.springframework.web.bind.annotation.RestController
public class RestController {

	
	
	@Autowired
	RecognitionService recognitionService;
	
	
	
	@RequestMapping(method = RequestMethod.POST, path = "/point", produces = "application/json")
	@ApiResponse(code = 200, message = "Success", response = String.class)
	public @ResponseBody Response addPoint(@RequestBody(required = true) Point point) {

		recognitionService.addPoint(point);

		return new Response();

	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/space", produces = "application/json")
	@ApiResponse(code = 200, message = "Success", response = String.class)
	public @ResponseBody Response getSpace() {
		
		
		List<Point> points = recognitionService.getSpace();

		return new Response(points);

	}
	

	@RequestMapping(method = RequestMethod.DELETE, path = "/space", produces = "application/json")
//	@ApiResponse(code = 200, message = "Success", response = String.class)
	public @ResponseBody Response cleanSpace() {
		
		
		recognitionService.cleanSpace();

		return new Response();

	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/lines", produces = "application/json")
	@ApiResponse(code = 200, message = "Success", response = String.class)
	public @ResponseBody Response getLines() {
		
		List<List<Point>> lines = recognitionService.getLines();

		return new Response(lines);

	}
}
