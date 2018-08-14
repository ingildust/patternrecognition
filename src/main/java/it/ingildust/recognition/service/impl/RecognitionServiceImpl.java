package it.ingildust.recognition.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.ingildust.recognition.exception.DuplicatePointException;
import it.ingildust.recognition.model.Line;
import it.ingildust.recognition.model.Point;
import it.ingildust.recognition.service.RecognitionService;
import lombok.extern.java.Log;

@Log
public class RecognitionServiceImpl implements RecognitionService {

	// il mio database
	Map<String, Line> lines = new HashMap<String, Line>();

	@Override
	public void addPoint(Point param) throws DuplicatePointException {

		log.info("start -> total lines in memory: " + lines.size());

		// caso T0, aggiungo il punto
		if (lines.isEmpty()) {
			Line first = new Line().addPoint(param);
			lines.put(first.key(), first);
			return;
		}

		List<Point> allpoints = getSpace();
		// se esiste un punto con le stesse coordinate, allora il punto non verra'
		// aggiunto
		if (allpoints.contains(param))
			throw new DuplicatePointException();

		// caso T1, solo un punto presente, cerco una funzione non inizializzata (mx=0,
		// q=0)
		if (lines.containsKey(new Line().key())) {
			Line notInit = lines.get(new Line().key());
			lines.remove(notInit.key());
			notInit.addPoint(param);
			lines.put(notInit.key(), notInit);
			return;
		}

		// confronto il mio parametro con tutti i punti già presenti
		for (Point p : allpoints) {
			// calcolo f(x)=mx+q

			Line newline = new Line().addPoint(param).addPoint(p);

			// cerco se esiste una funzione con le stesse caratteristiche
			if (lines.containsKey(newline.key())) {
				Line line = lines.get(newline.key());
				if (!line.getPoints().contains(param)) {
					line.addPoint(param);
				}
			} else {
				// la mia funzione e' nuova, la aggiungo
				lines.put(newline.key(), newline);
			}
		}

		log.info("end -> total lines in memory: " + lines.size());
	}

	@Override
	public List<Point> getSpace() {
		List<Point> out = new ArrayList<Point>();
		for (Line l : lines.values()) {
			out.addAll(l.getPoints());
		}

		return out.stream().distinct().collect(Collectors.toList());

	}

	@Override
	public List<Line> getLines(int n) {
		return lines.values().stream().filter(l -> l.getPoints().size() >= n).collect(Collectors.toList());
	}

	@Override
	public void cleanSpace() {
		lines = null;
		lines = new HashMap<String, Line>();
	}

}
