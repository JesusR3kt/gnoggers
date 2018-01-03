package engine.renderer;

import engine.scene.Scene;
import engine.window.DisplayManager;

public class RenderEngine {

	private Renderer renderer;
	
	public RenderEngine(Renderer renderer) {
		this.renderer = renderer;
	}
	
	public void update() {
		DisplayManager.updateDisplay();
	}
	
	public void renderScene(Scene scene) {
		renderer.renderScene(scene);
	}
	
	public void close() {
		renderer.cleanUp();
		DisplayManager.closeDisplay();
	}
	
	public static RenderEngine init() {
		DisplayManager.createDisplay(1280, 720, false, "lol");
		AnimationRenderer animationRenderer = new AnimationRenderer();
		Renderer renderer = new Renderer(animationRenderer);
		return new RenderEngine(renderer);
	}
}
