package it.ingildust.recognition.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Line {
	
	private List<Point> points = new ArrayList<Point>();
	
	@JsonIgnore
	private double mx;
	
	@JsonIgnore
	private double q;


	public Line setMx(double mx) {
		this.mx = mx;
		return this;
	}

	public Line setQ(double q) {
		this.q = q;
		return this;
	}
	
	public Line addPoint(Point p) {
		points.add(p);
		return this;
	}
	
	public boolean equals(Object o) {
		Line l = (Line) o;
		return (mx == l.getMx() && q == l.getQ());
	}

}
