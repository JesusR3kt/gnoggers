package engine.window;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {
	
	private int width;
	private int height;
	
	private long id;
	private GLCapabilities capabilities;

	private double[] cursorPosX;
	private double[] cursorPosY;
	
	public Window(int width, int height, boolean fullscreen, String title) {
		GLFWErrorCallback.createPrint(System.err).set();
		if (!glfwInit()) {
			throw new IllegalStateException("Unable to initialize GLFW");
		}
		
		this.width = width;
		this.height = height;
		this.cursorPosX = new double[1];
		this.cursorPosY = new double[1];
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
		
		glfwWindowHint(GLFW_SAMPLES, 2);

		long primaryMonitor = glfwGetPrimaryMonitor();

		id = glfwCreateWindow(width, height, title, fullscreen ? primaryMonitor : NULL, NULL);
		
		if (id == NULL) {
			throw new RuntimeException("Failed to create the GLFW window");
		}

		
		glfwSetKeyCallback(id, keyCallback);
		glfwSetCharCallback(id, charCallback);

		try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1);
			IntBuffer pHeight = stack.mallocInt(1);
			glfwGetWindowSize(id, pWidth, pHeight);
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
			glfwSetWindowPos(id, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);
		}
		glfwMakeContextCurrent(id);
		glfwShowWindow(id);
		
		capabilities = GL.createCapabilities();
	}
	
	private GLFWKeyCallback keyCallback = new GLFWKeyCallback() {
		@Override
		public void invoke(long window, int key, int scancode, int action, int mods) {}
	};
	
	private int thisChar;
	private int lastChar;
	
	public double getDX() {
		return deltaCursorX;
	}
	
	public double getDY() {
		return deltaCursorY;
	}
	
	private double lastCursorX;
	private double lastCursorY;
	private double thisCursorX;
	private double thisCursorY;
	private double deltaCursorX;
	private double deltaCursorY;
	
	private GLFWCharCallback charCallback = new GLFWCharCallback() {
		@Override
		public void invoke(long window, int codepoint) {
			thisChar = (char) codepoint;
		}
	};
	
	public boolean isKeyDown(int key) {
		return glfwGetKey(id, key) == GLFW_PRESS;
	}
	
	public boolean isButtonDown(int button) {
		return glfwGetMouseButton(id, button) == GLFW_PRESS;
	}
	
	private void updateTextInput() {
		lastChar = thisChar;
		thisChar = '\u0000';
	}
	
	public void update() {
		glfwSwapBuffers(id);
		glfwPollEvents();
		updateTextInput();
		updateCursorPos();
		updateCursorDelta();
	}
	
	public void close() {
		glfwSetWindowShouldClose(id, true);
	}
	
	public boolean shouldClose() {
		return glfwWindowShouldClose(id);
	}
	
	public void destroy() {
		glfwFreeCallbacks(id);
		close();
		glfwDestroyWindow(id);
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}

	public GLCapabilities getCapabilities() {
		return capabilities;
	}
	
	public String getTextInput() {
		char c = (char) lastChar;
		if (c == '\u0000') {
			return "";
		}
		return String.valueOf(c);
	}
	
	private void updateCursorPos() {
		glfwGetCursorPos(id, cursorPosX, cursorPosY);
	}
	
	public void updateCursorDelta() {
		thisCursorX = getCursorX();
		thisCursorY = getCursorY();
		deltaCursorX = thisCursorX - lastCursorX;
		deltaCursorY = thisCursorY - lastCursorY;
		lastCursorX = thisCursorX;
		lastCursorY = thisCursorY;
	}

	public void showCursor(boolean show){
		if (show) {
			glfwSetInputMode(id, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
		} else {
			glfwSetInputMode(id, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
		}
	}
	
	public double getCursorX() {
		return cursorPosX[0];
	}
	
	public double getCursorY() {
		return cursorPosY[0];
	}
	
	public float getCursorGLX() {
		return (float) (2 * (cursorPosX[0] / this.getWidth()) - 1);
	}
	
	public float getCursorGLY() {
		return (float) (2 * ((this.getHeight() - cursorPosY[0]) / this.getHeight()) - 1);
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
}
