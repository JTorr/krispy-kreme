package com.juliekevin;

import java.util.Scanner;

import com.juliekevin.model.Command;

public class Game {
	static Character player;
	static String status;

    public static void main(String [] args) {
    	Game.status = "active";
    	Parser parser = new Parser();
        
        System.out.println("Welcome to Krispy Kreme. What is your name?");
        Scanner scanner = new Scanner(System.in);
        
        Game.player = new Character(scanner.nextLine(), "Secret Sugar Factory");
        storyIntro(Game.player.name);

        
        System.out.println("You are currently at: " + player.getLocation().getName());
        System.out.println(player.getInventory());

        while (Game.status.equals("active")) {  
        	processInput(parser, scanner);                 
        }
            
        if (Game.status.equals("won")) {
            System.out.println("Congratulations you have won!");
            scanner.close();
        } else {
        	scanner.close();
            System.out.println("Sorry you have lost.  Better luck next time.");
        }
    }
    
    private static void processInput(Parser parser, Scanner scanner) {
    	try {
    		System.out.print(player.getLocation().getName() + " > ");
            String input = scanner.nextLine();
            Command cmd = parser.parse(input);
            
            switch(cmd.getVerb()) {
            case "quit": 
            	System.out.println("Exiting...");
                Game.status = "quit";
                break;
            case "go":
            	System.out.println("Going to " + cmd.getNoun());
                player.setLocation(cmd.getNoun());
                break;
            case "inventory":
            	System.out.println(player.getInventory());
            	break;
            case "buy":
            	player.getStash().buySweet(cmd.getNoun(), cmd.getQty(), player.getLocation(), player);
            	break;
            case "sell":
            	player.getStash().sellSweet(cmd.getNoun(), cmd.getQty(), player.getLocation(), player);
            	break;
            case "help":
            	help();
            	break;
            default:
            	help();
            }
        } catch(Exception e) {
        	System.out.println(e.getMessage());
        }
    }
    
	private static void help() {
		System.out.println("Enter commands in the following format: verb + noun (optional) + quantity(optional)");
		System.out.println("Example commands:\n 'go MyCity',\n 'buy donuts 3',\n 'sell 3 donuts',\n 'inventory',\n 'help'\n");
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
