package engine.model;

import animatedModel.Joint;
import animation.Animation;
import animation.Animator;
import engine.math.Matrix4f;
import engine.openglObjects.Vao;
import engine.texture.Texture;

public class Model {

	private final Vao model;
	private final Texture texture;
	
	private final Joint rootJoint;
	private final int jointCount;
	
	private final Animator animator;
	
	public Model(Vao model, Texture texture, Joint rootJoint, int jointCount) {
		this.model = model;
		this.texture = texture;
		this.rootJoint = rootJoint;
		this.jointCount = jointCount;
		this.animator = new Animator(this);
		rootJoint.calcInverseBindTransform(new Matrix4f());
	}
	
	public Vao getModel() {return model;}
	public Texture getTexture() {return texture;}
	public Joint getRootJoint() {return rootJoint;}
	
	public void delete() {
		model.delete();
		texture.delete();
	}
	
	public void update() {
		animator.update();
	}
	
	public void doAnimation(Animation animation) {
		animator.doAnimation(animation);
	}
	
	public Matrix4f[] getJointTransforms() {
		Matrix4f[] jointMatrices = new Matrix4f[jointCount];
		addJointsToArray(rootJoint, jointMatrices);
		return jointMatrices;
	}
	
	private void addJointsToArray(Joint headJoint, Matrix4f[] jointMatrices) {
		jointMatrices[headJoint.index] = headJoint.getAnimatedTransform();
		for (Joint childJoint : headJoint.children) {
			addJointsToArray(childJoint, jointMatrices);
		}
	}
}
