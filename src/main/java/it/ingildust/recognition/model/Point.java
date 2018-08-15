package it.ingildust.recognition.model;

import lombok.Getter;
import lombok.Setter;

// lombok per getter e setter
@Getter
@Setter
public class Point {
	
	
	private double x;
	private double y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Point() {
		
	}
	// per utilizzare correttamente i confronti
	@Override
	public boolean equals(Object o) {
		Point p = (Point) o;
		return (x == p.getX() && y == p.getY());
	}

}
