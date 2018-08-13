package it.ingildust.recognition.service.impl;

import java.util.ArrayList;
import java.util.List;

import it.ingildust.recognition.model.Line;
import it.ingildust.recognition.model.Point;
import it.ingildust.recognition.service.RecognitionService;

public class RecognitionServiceImpl implements RecognitionService{
	
	List<Line> lines = new ArrayList<Line>();

	@Override
	public void addPoint(Point param) {
		boolean found = false;
		// confronto il mio parametro con tutti i punti già presenti
		for (Point p : getSpace()) {
		
			double m = ((p.getY()-param.getY())/(p.getX()-param.getX()));
			double q= (p.getX()*param.getX() - param.getY()*p.getY())/(p.getX()-param.getX());
			
			for (Line line : lines) {
				if (m == line.getMx() && q == line.getQ()){
					line.getPoints().add(param);
					found =true;
				}
				if (!found && line.getPoints().size()<2) {
					line.getPoints().add(param);
					line.setMx(m);
					line.setQ(q);
					found =true;
				}
				
				
			}
			
		}
		if (!found) {
			Line newline = new Line();
			newline.getPoints().add(param);
		}
		
		
	}

	@Override
	public List<Point> getSpace() {
		List<Point> out = new ArrayList<Point>();
		for (Line l :lines) {
			out.addAll(l.getPoints());
		}
		return out;
	}

	@Override
	public List<Line> getLines() {
		return lines;
	}

	@Override
	public void cleanSpace() {
		lines = new ArrayList<Line>();
	}

}
