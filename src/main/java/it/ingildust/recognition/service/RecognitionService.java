package it.ingildust.recognition.service;

import java.util.List;

import it.ingildust.recognition.exception.DuplicatePointException;
import it.ingildust.recognition.model.Line;
import it.ingildust.recognition.model.Point;

public interface RecognitionService {
	
	
	/**
	 * Aggiunge un nuovo punto alla base dati
	 *
	 * @param  point un punto passato dall'utente
	 */
	void addPoint(Point point) throws DuplicatePointException;
	
	/**
	 * Cancella la memoria dei punti
	 */
	void cleanSpace();
	
	/**
	 * Restituisce lo spazio dei punti
	 * @return     la lista dei punti
	 */
	List<Point> getSpace();

	/**
	 * Restituisce i punti che si trovano su una retta composta da n o piu' punti
	 * @param  n il numero dei punti 
	 * @return     la lista delle rette
	 */
	List<Line> getLines(int n);

	


}
