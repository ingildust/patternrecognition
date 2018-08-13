package it.ingildust.recognition.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiResponse;
import it.ingildust.recognition.exception.DuplicatePointException;
import it.ingildust.recognition.model.Line;
import it.ingildust.recognition.model.Point;
import it.ingildust.recognition.model.Response;
import it.ingildust.recognition.model.Response.EXIT_CODE;
import it.ingildust.recognition.service.RecognitionService;
import lombok.extern.java.Log;

@Log
@org.springframework.web.bind.annotation.RestController
public class RestController {

	
	
	@Autowired
	RecognitionService recognitionService;
	
	
	
	@RequestMapping(method = RequestMethod.POST, path = "/point", produces = "application/json")
	@ApiResponse(code = 200, message = "Success", response = Response.class)
	public @ResponseBody Response addPoint(@RequestBody(required = true) Point point) {
		
		
		try {
			log.info("addPoint: "+point);
			recognitionService.addPoint(point);
			return new Response();
		} catch (DuplicatePointException e) {
			log.warning("exception "+e);
			return new Response(EXIT_CODE.KO, "punto gia' presente");
		}catch (Exception e1) {
			log.warning("exception "+e1);
			return new Response(EXIT_CODE.KO, e1.getMessage());
		}finally {
			log.info("addPoint end");
		}
		

	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/space", produces = "application/json")
	@ApiResponse(code = 200, message = "Success", response = Response.class)
	public @ResponseBody Response getSpace() {
		
		try {
			log.info("getSpace start");
			List<Point> points = recognitionService.getSpace();
			return new Response(points);
		} catch (Exception e) {
			log.warning("exception "+e);
			return new Response(EXIT_CODE.KO, e.getMessage());
		}finally {
			log.info("getSpace end");
		}
		

	}
	

	@RequestMapping(method = RequestMethod.DELETE, path = "/space", produces = "application/json")
	@ApiResponse(code = 200, message = "Success", response = Response.class)
	public @ResponseBody Response cleanSpace() {
		
		try {
			log.info("cleanSpace start");
			recognitionService.cleanSpace();
			
			return new Response();
		} catch (Exception e) {
			log.warning("exception "+e);
			return new Response(EXIT_CODE.KO, e.getMessage());
		}finally {
			log.info("cleanSpace end");
		}

	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/lines/{n}", produces = "application/json")
	@ApiResponse(code = 200, message = "Success", response = Response.class)
	public @ResponseBody Response getLines(@PathVariable("n") int n) {
		
		
		try {
			log.info("getLines "+n);
			List<Line> lines = recognitionService.getLines(n);
			
			return new Response(lines);
		} catch (Exception e) {
			log.warning("exception "+e);
			return new Response(EXIT_CODE.KO, e.getMessage());
		}finally {
			log.info("getSpace end");
		}

	}
}
