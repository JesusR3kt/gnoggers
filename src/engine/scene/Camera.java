package engine.scene;

import static org.lwjgl.glfw.GLFW.*;

import engine.math.Matrix4f;
import engine.math.Vector3f;
import engine.window.DisplayManager;
import engine.window.Timer;

public class Camera {

	private static final float CAMERA_SENSITIVITY = 0.3f;
	private static final float CAMERA_SPEED = 10f;

	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.2f;
	private static final float FAR_PLANE = 400;

	private Matrix4f projectionMatrix;
	private Matrix4f viewMatrix = new Matrix4f();

	private Vector3f position = new Vector3f(0, 0, 0);

	private float yaw = 0;
	private float pitch = 0;

	public Camera() {
		this.projectionMatrix = createProjectionMatrix();
	}

	public void move() {
		if (DisplayManager.getWindow().isButtonDown(0)) {
			yaw();
			pitch();
			DisplayManager.getWindow().showCursor(false);
		} else {
			DisplayManager.getWindow().showCursor(true);
		}
		if (DisplayManager.getWindow().isKeyDown(GLFW_KEY_W)) {
			walkForward();
	    }
	    if (DisplayManager.getWindow().isKeyDown(GLFW_KEY_S)) {
	        walkBackwards();
	    }
	    if (DisplayManager.getWindow().isKeyDown(GLFW_KEY_A)) {
	        strafeLeft();
	    }
	    if (DisplayManager.getWindow().isKeyDown(GLFW_KEY_D)) {
	        strafeRight();
	    }
	    
		updateViewMatrix();
	}

	private void walkForward() {
		float distance = CAMERA_SPEED * Timer.getInstance().getDelta();
	    position.x += distance * (float) Math.sin(Math.toRadians(yaw));
	    position.y -= distance * (float) Math.sin(Math.toRadians(pitch));
	    position.z -= distance * (float) Math.cos(Math.toRadians(yaw));
	}
	
	private void walkBackwards() {
		float distance = CAMERA_SPEED * Timer.getInstance().getDelta();
	    position.x -= distance * (float) Math.sin(Math.toRadians(yaw));
		position.y += distance * (float) Math.sin(Math.toRadians(pitch));
	    position.z += distance * (float) Math.cos(Math.toRadians(yaw));
	}
	
	private void strafeLeft() {
		float distance = CAMERA_SPEED * Timer.getInstance().getDelta();
	    position.x -= distance * (float) Math.sin(Math.toRadians(yaw + 90));
	    position.z += distance * (float) Math.cos(Math.toRadians(yaw + 90));
	}
	
	private void strafeRight() {
		float distance = CAMERA_SPEED * Timer.getInstance().getDelta();
	    position.x -= distance * (float) Math.sin(Math.toRadians(yaw - 90));
	    position.z += distance * (float) Math.cos(Math.toRadians(yaw - 90));
	}
	
	private void pitch() {
		this.pitch += DisplayManager.getWindow().getDY() * CAMERA_SENSITIVITY;
		if(this.pitch>90){
			this.pitch = 90;
		}
		else if(this.pitch<-90){
			this.pitch = -90;
		}
	}
	
	private void yaw() {
		this.yaw += DisplayManager.getWindow().getDX() * CAMERA_SENSITIVITY;
		this.yaw %= 360;
	}

	public Matrix4f getViewMatrix() {
		return viewMatrix;
	}

	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}

	public Matrix4f getProjectionViewMatrix() {
		return Matrix4f.mul(projectionMatrix, viewMatrix, null);
	}

	private void updateViewMatrix() {
		viewMatrix.setIdentity();
		Matrix4f.rotate((float) Math.toRadians(pitch), new Vector3f(1, 0, 0), viewMatrix, viewMatrix);
		Matrix4f.rotate((float) Math.toRadians(yaw), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
		Vector3f negativeCameraPos = new Vector3f(-position.x, -position.y, -position.z);
		Matrix4f.translate(negativeCameraPos, viewMatrix, viewMatrix);
	}

	private static Matrix4f createProjectionMatrix() {
		Matrix4f projectionMatrix = new Matrix4f();
		float aspectRatio = (float) DisplayManager.getWindow().getWidth() / (float) DisplayManager.getWindow().getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))));
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;

		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
		projectionMatrix.m33 = 0;
		return projectionMatrix;
	}
	
	public Vector3f getPosition() {return position;}
	public float getYaw() {return yaw;}
	public float getPitch() {return pitch;}
}
