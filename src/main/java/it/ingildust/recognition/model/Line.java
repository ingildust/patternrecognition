package it.ingildust.recognition.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

public class Line {

	private List<Point> points = new ArrayList<Point>();

	@JsonIgnore
	private Double mx = Double.NaN;

	@JsonIgnore
	private Double q = Double.NaN;

	// per rispettare lo standard delle api richieste
	@JsonValue
	public List<Point> getPoints() {
		return points;
	}

	public Double getMx() {
		return mx;
	}

	public Double getQ() {
		return q;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

	public void setMx(Double mx) {
		this.mx = mx;
	}

	public void setQ(Double q) {
		this.q = q;
	}

	public Line addPoint(Point p) {
		if (!points.isEmpty()) {
			mx = generateM(p, points.get(0));
			q = generateQ(p, points.get(0));
		}
		points.add(p);
		return this;
	}
	// per utilizzare correttamente i confronti
	@Override
	public boolean equals(Object o) {
		Line l = (Line) o;
		return (mx == l.getMx() && q == l.getQ());
	}

	public String key() {
		return mx + "," + q;
	}

	private double generateQ(Point p1, Point p2) {
		Double val = (p2.getX() * p1.getX() - p1.getY() * p2.getY()) / (p2.getX() - p1.getX());
		if (val == -0)
			return 0;
		if (val.isNaN())
			return 0;
		return val;
	}

	private double generateM(Point p1, Point p2) {
		Double val = ((p2.getY() - p1.getY()) / (p2.getX() - p1.getX()));
		if (val == -0)
			return 0;
		if (val.isNaN())
			return 0;
		return val;
	}

}
