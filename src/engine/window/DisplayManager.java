package engine.window;

public class DisplayManager {

	private static Window mainWindow;
	private static Timer timer;
	
	public static void createDisplay(int width, int height, boolean fullscreen, String title) {
		mainWindow = new Window(width, height, fullscreen, title);
		timer = Timer.getInstance();
    	timer.init();
	}
	
	public static void updateDisplay() {
		mainWindow.update();
		timer.update();
	}
	
	public static void closeDisplay() {
		if (mainWindow != null) {
			mainWindow.destroy();
		}
	}
	
	public static Window getWindow() {return mainWindow;}

	
}
