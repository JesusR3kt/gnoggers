package engine.shader;

import static org.lwjgl.opengl.GL20.*;

public class UniformSampler extends Uniform {

	private int texUnit;
	private boolean used = false;
	
	public UniformSampler(String name) {
		super(name);
	}
	
	public void loadTexUnit(int texUnit) {
		if (!used || this.texUnit != texUnit) {
			glUniform1i(super.getLocation(), texUnit);
			used = true;
			this.texUnit = texUnit;
		}
	}
}
