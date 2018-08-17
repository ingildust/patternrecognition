package it.ingildust.recognition.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

public class Line {

	private List<Point> points = new ArrayList<Point>();

	// per rispettare lo standard delle api richieste
	@JsonValue
	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

	public Line addPoint(Point p) {
		points.add(p);
		return this;
	}

	public boolean isCollinear(Point p3) {
		
		Point p1 = points.get(0);
		Point p2 = points.get(1);
		
		 double mat = p1.getX() * 
				 (p2.getY() - p3.getY()) + 
				 p2.getX() * 
				 ( p3.getY() - p1.getY()) + 
				 p3.getX() * 
				 (p1.getY() - p2.getY());
		return mat==0;
	}

}
