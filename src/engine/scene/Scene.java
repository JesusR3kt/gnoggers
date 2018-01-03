package engine.scene;

import java.util.List;

import engine.math.Vector3f;
import engine.model.Model;

public class Scene {

	private final Camera camera;
	private final List<Model> models;
	private Vector3f lightDirection = new Vector3f(0, -1, 0);
	
	public Scene(List<Model> models, Camera camera) {
		this.models = models;
		this.camera = camera;
	}
	
	public void setLightDirection(Vector3f lightDirection) {
		this.lightDirection.set(lightDirection);
	}
	
	public Camera getCamera() {return camera;}
	public List<Model> getModels() {return models;}
	public Vector3f getLightDirection() {return lightDirection;}
}
