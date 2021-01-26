package utils;

import java.util.List;

import com.juliekevin.Game;

public class GameUtils {

	public static int getRand(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
	
	public static String getRandName() {
		List<String> names = Game.readNameList();
		try {
			int rand = getRand(1, names.size() - 1);
			return names.get(rand);
		} catch(Exception e) {
			System.out.println("ERROR: Name list was not loaded.");
			return "Joe";
		}
	}
}
