package it.ingildust.recognition.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import it.ingildust.recognition.exception.DuplicatePointException;
import it.ingildust.recognition.model.Line;
import it.ingildust.recognition.model.Point;
import it.ingildust.recognition.service.RecognitionService;
import lombok.extern.java.Log;

@Log
public class RecognitionServiceImpl implements RecognitionService{
	
	// il mio database
	List<Line> lines = new ArrayList<Line>();

	@Override
	public void addPoint(Point param) throws DuplicatePointException {
		
		log.info("start -> total lines in memory: "+lines.size());
		
		// caso T0, aggiungo il punto
		if (lines.isEmpty()) {
			lines.add(new Line().addPoint(param));
			return;
		}
		
		List<Point> allpoints = getSpace();
		// se esiste un punto con le stesse coordinate, allora il punto non verra' aggiunto
		if (allpoints.contains(param)) throw new DuplicatePointException();
		
		// confronto il mio parametro con tutti i punti già presenti
		for (Point p : allpoints) {
			boolean found=false;
//			if (param.equals(p)) throw new DuplicatePointException();
			// calcolo f(x)=mx+q 
			
			Line newline = new Line().setMx(getM(param, p))
					.setQ(getQ(param, p)).addPoint(param).addPoint(p);
			
			// cerco se esiste una funzione con le stesse caratteristiche
			for (Line line : lines) {
				if (newline.equals(line)){
					line.getPoints().add(param);
					found =true;
					// esiste, aggiungo il punto alla funzione
				}
				if (line.getPoints().size()<2) {
					line
					.setMx(getM(param, line.getPoints().get(0)))
					.setQ(getQ(param, line.getPoints().get(0)))
					.addPoint(param);
					found =true;
				}
				
				
			}
			
			// la mia funzione e' nuova, la aggiungo
			if (!found) {
				
				lines.add(newline);
			}
			
		}
		
		
		log.info("end -> total lines in memory: "+lines.size());
	}

	private double getQ(Point p1, Point p2) {
		return (p2.getX()*p1.getX() - p1.getY()*p2.getY())/(p2.getX()-p1.getX());
	}

	private double getM(Point p1, Point p2) {
		return ((p2.getY()-p1.getY())/(p2.getX()-p1.getX()));
	}

	@Override
	public List<Point> getSpace() {
		List<Point> out = new ArrayList<Point>();
		for (Line l :lines) {
			out.addAll(l.getPoints());
		}
		
		return out.stream()
			     .distinct()
			     .collect(Collectors.toList());
		
	}

	@Override
	public List<Line> getLines(int n) {
		return lines.stream()
                .filter(l -> l.getPoints().size()>=n)
                .collect(Collectors.toList());
	}

	@Override
	public void cleanSpace() {
		lines=null;
		lines = new ArrayList<Line>();
	}


}
