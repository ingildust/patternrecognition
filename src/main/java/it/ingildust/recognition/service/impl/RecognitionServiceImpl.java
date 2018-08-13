package it.ingildust.recognition.service.impl;

import java.util.ArrayList;
import java.util.List;

import it.ingildust.recognition.exception.DuplicatePointException;
import it.ingildust.recognition.model.Line;
import it.ingildust.recognition.model.Point;
import it.ingildust.recognition.service.RecognitionService;
import lombok.extern.java.Log;

@Log
public class RecognitionServiceImpl implements RecognitionService{
	
	List<Line> lines = new ArrayList<Line>();

	@Override
	public void addPoint(Point param) throws DuplicatePointException {
		
		log.info("start -> total lines in memory: "+lines.size());
		boolean found = false;
		// confronto il mio parametro con tutti i punti già presenti
		for (Point p : getSpace()) {
			
			if (param.getX() == p.getY() && param.getY() == p.getY()) {
				throw new DuplicatePointException();
			}
		
			double m = getM(param, p);
			double q = getQ(param, p);
			
			for (Line line : lines) {
				if (m == line.getMx() && q == line.getQ()){
					line.getPoints().add(param);
					found =true;
				}
				if (line.getPoints().size()<2) {
					line.setMx(getM(param, line.getPoints().get(0)));
					line.setQ(getQ(param, line.getPoints().get(0)));
					line.getPoints().add(param);
					found =true;
				}
				
				
			}
			
			Line newline = new Line();
			newline.getPoints().add(param);
			newline.getPoints().add(p);
			newline.setMx(getM(param, p));
			newline.setQ(getQ(param, p));
			found =true;
			lines.add(newline);
			
		}
		if (!found) {
			Line newline = new Line();
			newline.getPoints().add(param);
			lines.add(newline);
		}
		
		log.info("end -> total lines in memory: "+lines.size());
	}

	private double getQ(Point param, Point p) {
		return (p.getX()*param.getX() - param.getY()*p.getY())/(p.getX()-param.getX());
	}

	private double getM(Point param, Point p) {
		return ((p.getY()-param.getY())/(p.getX()-param.getX()));
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
	public List<Line> getLines(int n) {
		return lines;
	}

	@Override
	public void cleanSpace() {
		lines = new ArrayList<Line>();
	}


}
