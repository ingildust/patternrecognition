package it.ingildust.recognition.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Line {
	
	private List<Point> points = new ArrayList<Point>();
	
	@JsonIgnore
	double mx;
	
	@JsonIgnore
	double q;

}
