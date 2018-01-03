package engine.renderer;

import java.util.ArrayList;
import java.util.List;

import animation.Animation;
import engine.math.Vector3f;
import engine.model.Model;
import engine.scene.Camera;
import engine.scene.Scene;
import engine.util.File;
import engine.window.DisplayManager;
import loaders.AnimatedModelLoader;
import loaders.AnimationLoader;

public class Test {

	public static void main(String[] args) {
		RenderEngine engine = RenderEngine.init();

		Camera camera = new Camera();
		Model entity = AnimatedModelLoader.loadEntity(new File("res", "model.dae"),
				new File("res", "diffuse.png"));
		List<Model> models = new ArrayList<>();
		models.add(entity);
		Animation animation = AnimationLoader.loadAnimation(new File("res", "model.dae"));
		entity.doAnimation(animation);
		Scene scene = new Scene(models, camera);
		scene.setLightDirection(new Vector3f(0.2f, -0.3f, -0.8f));

		while (!DisplayManager.getWindow().shouldClose()) {
			scene.getCamera().move();
			for (Model model : scene.getModels()) {
				model.update();
			}
			engine.renderScene(scene);
			engine.update();
		}

		engine.close();
	}
	
}
