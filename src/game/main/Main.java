package game.main;

import engine.window.Timer;

public class Main {
	
	public void loop() {
		Timer.getInstance();
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.loop();
	}
}
