package it.ingildust.recognition.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Point {
	
	
	private double x;
	private double y;
	
	public boolean equals(Object o) {
		Point p = (Point) o;
		return (x == p.getX() && y == p.getY());
	}

}
