package com.juliekevin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.juliekevin.model.SweetList;

import utils.CsvReader;

public class Game {
	static Character player;
	static String status;
	static int currentDay = 1;
	static SweetList sweetList = new SweetList();;
	static String filePath = new File("").getAbsolutePath();
	static List<String> NameList;
	public static Scanner scanner = new Scanner(System.in);

    public static void main(String [] args) {  
    	Game.createNameList();
    	Game.status = "active";
    	Parser parser = new Parser();
        
        System.out.println("Welcome to Krispy Kreme. What is your name?");       
        
        Game.player = new Character(scanner.nextLine(), "Secret Sugar Factory");
        CommandHandler handler = new CommandHandler(player);
        storyIntro(Game.player.name);

    	System.out.println("What would you like to do?");
        getAvailableActions();

        while (Game.status.equals("active")) {  
        	handler.processInput(parser, scanner);                 
        }
            
        if (Game.status.equals("won")) {
            System.out.println("Congratulations you have won!");
            scanner.close();
        } else {
        	scanner.close();
            System.out.println("Sorry you have lost.  Better luck next time.");
        }
    }
    
    public static void getAvailableActions() {
    	System.out.println("- View help");
    	System.out.println("- View inventory");
    	System.out.println("- View location");
    	System.out.println("- View supplier");
    	System.out.println("- View junkie");
    	System.out.println("- Go (location)");
    	System.out.println("- Buy (item) (qty)");
    	System.out.println("- Sell (item) (qty)");
    	System.out.println("- Get loan (amt)");
    	System.out.println("- Pay loan (amt)\n");

    }
	
	private static void storyIntro(String name) {
		StringBuilder sb = new StringBuilder("In a world where Sugar is outlawed, Our Hero, ");
		sb.append(name);
		sb.append(" is the only hope!\n");
		sb.append("Buy and sell Candy, Donuts and other forbidden treats.\n");
		sb.append("But don't get caught! The Sugar Addicts are depending on you!\n\n");
		System.out.println(sb.toString());
	}
	
	public static SweetList getSweetList() {
		return sweetList;
	}
	
	public static List<String> readNameList() {
		if(Game.NameList != null) {
			return Game.NameList;
		} else {
			Game.createNameList();
			return Game.NameList;
		}
		
	}
	
	public static Character getPlayer() {
		return player;
	}
	
	public static int getDay() {
		return currentDay;
	}
	
	public static void incrementDay() {
		currentDay += 1;
	}
	
	private static void createNameList() {
    	try {
    		NameList = CsvReader.readCsv(filePath.concat("/src/utils/NameData.csv"));
    	} catch(Exception e) {
    		NameList = new ArrayList<String>();
    		NameList.add("Bob");
    		System.out.println("Failed to read CSV file containing random names.");
    	}
	}
}
