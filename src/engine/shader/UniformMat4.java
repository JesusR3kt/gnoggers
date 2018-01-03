package engine.shader;

import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import engine.math.Matrix4f;

public class UniformMat4 extends Uniform {

	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	
	public UniformMat4(String name) {
		super(name);
	}
	
	public void loadMatrix(Matrix4f matrix) {
		matrix.store(matrixBuffer);
		matrixBuffer.flip();
		glUniformMatrix4fv(super.getLocation(), false, matrixBuffer);
	}
}
