package engine.shader;

import static org.lwjgl.opengl.GL20.*;
import engine.math.Vector3f;

public class UniformVec3 extends Uniform {

	private float x;
	private float y;
	private float z;
	private boolean used = false;
	
	public UniformVec3(String name) {
		super(name);
	}
	
	public void loadVec3(Vector3f vector) {
		loadVec3(vector.x, vector.y, vector.z);
	}
	
	public void loadVec3(float x, float y, float z) {
		if (!used || this.x != x || this.y != y || this.z != z) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.used = true;
			glUniform3f(super.getLocation(), x, y, z);
		}
	}
}
