package utils;

import java.util.List;

import com.juliekevin.Game;

public class GameUtils {

	public static int getRand(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
	
	public static String getRandName() {
		List<String> names = Game.readNameList();
		int rand = getRand(1, names.size() - 1);
		return names.get(rand);
	}
}
