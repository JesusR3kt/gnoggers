package engine.renderer;

import static org.lwjgl.opengl.GL11.*;

import engine.scene.Scene;

public class Renderer {

	private AnimationRenderer renderer;
	
	protected Renderer(AnimationRenderer renderer) {
		this.renderer = renderer;
	}
	
	protected void renderScene(Scene scene) {
		prepare();
		renderer.render(scene);
	}
	
	private void prepare() {
		glClearColor(0, 0, 0, 1);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	protected void cleanUp( ) {
		renderer.cleanUp();
	}
}
