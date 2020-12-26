package utils;

public class GameUtils {

	public static int getRand(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
}
