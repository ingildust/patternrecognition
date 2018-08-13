package it.ingildust.recognition.service;

import java.util.List;

import it.ingildust.recognition.exception.DuplicatePointException;
import it.ingildust.recognition.model.Line;
import it.ingildust.recognition.model.Point;

public interface RecognitionService {
	
	void addPoint(Point point) throws DuplicatePointException;
	
	void cleanSpace();

	List<Point> getSpace();

	List<Line> getLines(int n);

	


}
