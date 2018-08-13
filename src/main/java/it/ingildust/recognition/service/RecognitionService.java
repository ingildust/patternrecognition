package it.ingildust.recognition.service;

import java.awt.Point;
import java.util.List;

public interface RecognitionService {
	
	void addPoint(Point point);
	
	void cleanSpace();

	List<Point> getSpace();

	List<List<Point>> getLines();

	


}
