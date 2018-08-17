package it.ingildust.recognition.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import it.ingildust.recognition.exception.DuplicatePointException;
import it.ingildust.recognition.model.Line;
import it.ingildust.recognition.model.Point;
import it.ingildust.recognition.service.RecognitionService;
import lombok.extern.java.Log;

@Log
public class RecognitionServiceImpl implements RecognitionService {

	// il mio database
	List<Line> lines = new ArrayList<Line>();

	@Override
	public void addPoint(Point param) throws DuplicatePointException {

		log.info("start -> total lines in memory: " + lines.size());

		// caso T0, aggiungo il punto
		if (lines.isEmpty()) {
			Line first = new Line().addPoint(param);
			lines.add(first);
			return;
		}

		// caso T1, solo un punto presente, cerco una funzione con un solo punto
		if (lines.get(0).getPoints().size() == 1) {
			Line notInit = lines.get(0);
			if (notInit.getPoints().contains(param))
				throw new DuplicatePointException();
			notInit.addPoint(param);
			return;
		}
		
		// punti conosciuti
		List<Point> knownPoints = new ArrayList<Point>();
		// punti sconosciuti
		List<Point> unknownPoints = new ArrayList<Point>();
		
		// scorro tutte le rette esistenti
		for (Line l : lines) {		
			// se il parametro appartiene alla retta, aggiungo il punto
			if (l.isCollinear(param)) {
				// se il mio punto e' gia' presenti, eccezione
				if (l.getPoints().contains(param))
					throw new DuplicatePointException();
				// aggiungo tutti i punti della linea tra quelli conosciuti
				knownPoints.addAll(l.getPoints());
				// aggiungo il mio parametro alla retta
				l.addPoint(param);
			}else {
				// aggiungo tutti i punti alla lista dei punti sconosciuti
				unknownPoints.addAll(l.getPoints());
			}
		}
		// rimuovo eventuali punti di intersezione con rette gia' elaborate
		unknownPoints.removeAll(knownPoints);
		// lista per controllo duplicati (e rollback)
		List<Line> nuovelinee = new ArrayList<Line>();
		// creo una nuova retta per ogni punto (univoco)
		for (Point point : unknownPoints.stream().distinct().collect(Collectors.toList())) {
			// se esiste gia' eccezione
			if (point.equals(param))
				throw new DuplicatePointException();
			// creo la nuova retta
			Line newline = new Line().addPoint(param).addPoint(point);
			nuovelinee.add(newline);
		}
		// aggiungo tutte le rette
		lines.addAll(nuovelinee);

		log.info("end -> total lines in memory: " + lines.size());
	}

	@Override
	public List<Point> getSpace() {
		List<Point> out = new ArrayList<Point>();
		for (Line l : lines) {
			out.addAll(l.getPoints());
		}
		// filtro i risultati doppi
		return out.stream().distinct().collect(Collectors.toList());

	}

	@Override
	public List<Line> getLines(int n) {
		// filtro la dimensione dei punti
		return lines.stream().filter(l -> l.getPoints().size() >= n).collect(Collectors.toList());
	}

	@Override
	public void cleanSpace() {
		lines = null;
		// flushall!
		lines = new ArrayList<Line>();
	}

}
