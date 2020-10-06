package service;

import model.Circle;
import model.Triangle;

public class ShapeService {
	Triangle triangle;
	Circle circle;
	
	public String readShape(String userId) {	
		System.out.println("Inside readShape");
		return "Aditi";
	}
	public Triangle getTriangle() {
		return triangle;
	}
	public void setTriangle(Triangle triangle) {
		this.triangle = triangle;
	}
	public Circle getCircle() {
		return circle;
	}
	public void setCircle(Circle circle) {
		this.circle = circle;
	}
	

}
