package engine.shader;

import engine.util.File;

public class AnimationShader extends ShaderProgram {

	private static final int MAX_JOINTS = 50;
	private static final int DIFFUSE_TEX_UNIT = 0;
	
	private static final File VERTEX_SHADER = new File("engine/shader", "vertex.glsl");
	private static final File FRAGMENT_SHADER = new File("engine/shader", "fragment.glsl");
	
	private UniformMat4 projectionViewMatrix = new UniformMat4("projectionViewMatrix");
	private UniformVec3 lightDirection = new UniformVec3("lightDirection");
	protected UniformMat4Array jointTransforms = new UniformMat4Array("jointTransforms", MAX_JOINTS);
	private UniformSampler diffuseMap = new UniformSampler("diffuseMap");
	
	public AnimationShader() {
		super(VERTEX_SHADER, FRAGMENT_SHADER, "in_position", "in_textureCoords", "in_normal", "in_jointIndices", "in_weights");
		super.storeAllUniformLocations(projectionViewMatrix, diffuseMap, lightDirection, jointTransforms);
		connectTextureUnits();
	}
	
	private void connectTextureUnits() {
		super.start();
		diffuseMap.loadTexUnit(DIFFUSE_TEX_UNIT);
		super.stop();
	}
	
	public UniformMat4 getProjectionViewMatrix() {return projectionViewMatrix;}
	public UniformMat4Array getJointTransforms() {return jointTransforms;}
	public UniformVec3 getLightDirection() {return lightDirection;}
}
