package it.ingildust.recognition;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.ingildust.recognition.exception.DuplicatePointException;
import it.ingildust.recognition.model.Line;
import it.ingildust.recognition.model.Point;
import it.ingildust.recognition.service.RecognitionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecognitionApplicationTests {

	@Autowired
	private RecognitionService recognitionService;
	
	@Test
	public void testEmpySet() {
		
		List<Point> p = recognitionService.getSpace();
		assertTrue(p.isEmpty());
	}
	
	
	@Test
	public void testPoints() throws DuplicatePointException {
		recognitionService.addPoint(new Point(0,0));
		recognitionService.addPoint(new Point(0,1));
		recognitionService.addPoint(new Point(1,0));
		recognitionService.addPoint(new Point(1,1));
		recognitionService.addPoint(new Point(2,2));
		List<Point> p = recognitionService.getSpace();
		assertTrue(p.size()==5);
		
		List<Line> l = recognitionService.getLines(2);
		assertTrue(l.size()==8);
		
		l = recognitionService.getLines(3);
		assertTrue(l.size()==1);
	}
	
	@Test
	public void testDeleteSet() throws DuplicatePointException {
		testPoints();
		recognitionService.cleanSpace();
		List<Point> p = recognitionService.getSpace();
		assertTrue(p.isEmpty());
	}

}
