package com.juliekevin;

import java.util.Scanner;

public class Game {
	static Character player;
	static String status;

    public static void main(String [] args) {
    	Game.status = "active";
        
        System.out.println("Welcome to Krispy Kreme. What is your name?");
        Scanner scanner = new Scanner(System.in);
        
        Game.player = new Character(scanner.nextLine(), "Secret Sugar Factory");
        storyIntro(Game.player.name);

        
        System.out.println("You are currently at: " + player.getLocation());
        player.getInventory();

        while (Game.status.equals("active")) {
            System.out.print(player.getLocation() + " > ");
            String input = scanner.nextLine();
            //  TODO validation
            String [] words = input.trim().split(" ");
            String verb = words[0].trim();
            String noun = (words.length > 1) ?  words[1].trim() : "";
            if ("quit".equals(verb)) {
                System.out.println("Exiting...");
                Game.status = "quit";
            } else if ("go".equals(verb)) {
                System.out.println("Going to " + noun);
                player.setLocation(noun);
            } else if ("inventory".equals(verb)) {
                player.getInventory();
            } else if("buy".equals(verb)) {
            	System.out.println("Quantity?");
            	int quantity = scanner.nextInt();
            	player.getStash().buySweet(noun, quantity, player.getLocation(), player);
            } else if("sell".equals(verb)) {
            	System.out.println("Quantity?");
            	int quantity = scanner.nextInt();
            	player.getStash().sellSweet(noun, quantity, player.getLocation(), player);
            } else {
            	help();
            }
        }
        if (Game.status.equals("won")) {
            System.out.println("Congratulations you have won!");
        } else {
        	scanner.close();
            System.out.println("Sorry you have lost.  Better luck next time.");
        }
    }
    
	private static void help() {
		System.out.println("Type 'go' and the name of the location you want to visit.");
    	System.out.println("Type 'inventory' to check your inventory.\n\n");
	}
	
	private static void storyIntro(String name) {
		StringBuilder sb = new StringBuilder("In a world where Sugar is outlawed, Our Hero, ");
		sb.append(name);
		sb.append(" is the only hope!\n");
		sb.append("Buy and sell Candy, Donuts and other forbidden treats.\n");
		sb.append("But don't get caught! The Sugar Addicts are depending on you!\n\n");
		System.out.println(sb.toString());
		help();
	}
}
