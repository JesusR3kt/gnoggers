package engine.window;

import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.glfw.GLFW.glfwSetTime;

public class Timer {

	private double lastFrame;
	private float delta;
	
	private static Timer instance;
	
	private Timer() {;}
	
	public static Timer getInstance() {
		if (instance == null) {
			instance = new Timer();
		}
		return instance;
	}
	
	/**
	 * initiate/reset the Timer
	 */
	public void init() {
		glfwSetTime(0);
	}

	/**
	 * recalculate the time past since the last frame (delta time)
	 */
	public void update() {
		double thisFrame = glfwGetTime();
		delta = (float) (thisFrame - lastFrame);
		lastFrame = thisFrame;
	}
	
	/**
	 * @return the time since the Timer got initiated in seconds.
	 */
	public double getTime() {return glfwGetTime();}
	
	/**
	 * @return the time past since the last frame (delta time)
	 */
	public float getDelta() {return delta;}
	
}
