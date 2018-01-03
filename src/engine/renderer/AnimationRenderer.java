package engine.renderer;

import static org.lwjgl.opengl.GL11.*;

import engine.math.Vector3f;
import engine.model.Model;
import engine.scene.Camera;
import engine.scene.Scene;
import engine.shader.AnimationShader;
import loaders.OpenGlUtils;

public class AnimationRenderer {

	private AnimationShader shader;
	
	public AnimationRenderer() {
		this.shader = new AnimationShader();
	}
	
	public void render(Scene scene) {
		prepare(scene.getCamera(), scene.getLightDirection());
		for (Model entity : scene.getModels()) {
			renderEntity(entity);
		}
		finish();
	}
	
	private void renderEntity(Model entity) {
		entity.getTexture().bindToUnit(0);
		entity.getModel().bind(0, 1, 2, 3, 4);
		shader.getJointTransforms().loadMatrixArray(entity.getJointTransforms());
		glDrawElements(GL_TRIANGLES, entity.getModel().getIndexCount(), GL_UNSIGNED_INT, 0);
		entity.getModel().unbind(0, 1, 2, 3, 4);
	}
	
	public void cleanUp() {
		shader.cleanUp();
	}
	
	private void prepare(Camera camera, Vector3f light) {
		shader.start();
		shader.getProjectionViewMatrix().loadMatrix(camera.getProjectionViewMatrix());
		shader.getLightDirection().loadVec3(light);
		OpenGlUtils.antialias(true);
		OpenGlUtils.disableBlending();
		OpenGlUtils.enableDepthTesting(true);
	}
	
	private void finish() {
		shader.stop();
	}
}