package engine.shader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;

import engine.util.File;

public class ShaderProgram {

	private int programID;
	
	public ShaderProgram(File vertexShader, File fragmentShader, String... variables) {
		int vertexShaderID = loadShader(vertexShader, GL_VERTEX_SHADER);
		int fragmentShaderID = loadShader(fragmentShader, GL_FRAGMENT_SHADER);
		programID = glCreateProgram();
		glAttachShader(programID, vertexShaderID);
		glAttachShader(programID, fragmentShaderID);
		bindAttributes(variables);
		glLinkProgram(programID);
		glDetachShader(programID, vertexShaderID);
		glDetachShader(programID, fragmentShaderID);
		glDeleteShader(vertexShaderID);
		glDeleteShader(fragmentShaderID);
	}
	
	protected void storeAllUniformLocations(Uniform... uniforms) {
		for (Uniform uniform : uniforms) {
			uniform.storeUniformLocation(programID);
		}
		glValidateProgram(programID);
	}
	
	public void start() {
		glUseProgram(programID);
	}
	
	public void stop() {
		glUseProgram(0);
	}
	
	public void cleanUp() {
		stop();
		glDeleteProgram(programID);
	}
	
	private void bindAttributes(String[] variables) {
		for (int i = 0; i < variables.length; i++) {
			glBindAttribLocation(programID, i, variables[i]);
		}
	}
	
	private int loadShader(File file, int type) {
		StringBuilder shaderSource = new StringBuilder();
		try {
			BufferedReader reader = file.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				shaderSource.append(line).append("//\n");
			}
			reader.close();
		} catch (Exception e) {
			System.err.println("Could not read file.");
			e.printStackTrace();
			System.exit(-1);
		}
		int shaderID = glCreateShader(type);
		glShaderSource(shaderID, shaderSource);
		glCompileShader(shaderID);
		if (glGetShaderi(shaderID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.out.println(glGetShaderInfoLog(shaderID, 500));
			System.err.println("Could not compile shader "+ file);
			System.exit(-1);
		}
		return shaderID;
	}
}
