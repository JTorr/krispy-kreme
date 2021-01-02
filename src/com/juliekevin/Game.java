package com.juliekevin;

import java.util.Scanner;

import com.juliekevin.model.Command;
import com.juliekevin.model.SweetList;

public class Game {
	static Character player;
	static String status;
	static SweetList sweetList = new SweetList();;

    public static void main(String [] args) {      
    	
    	Game.status = "active";
    	Parser parser = new Parser();
        
        System.out.println("Welcome to Krispy Kreme. What is your name?");
        Scanner scanner = new Scanner(System.in);
        
        Game.player = new Character(scanner.nextLine(), "Secret Sugar Factory");
        CommandHandler handler = new CommandHandler(player);
        storyIntro(Game.player.name);

        
        System.out.println("You are currently at: " + player.getLocation().getName());
        System.out.println(player.getInventory());

        while (Game.status.equals("active")) {  
        	processInput(parser, scanner, handler);                 
        }
            
        if (Game.status.equals("won")) {
            System.out.println("Congratulations you have won!");
            scanner.close();
        } else {
        	scanner.close();
            System.out.println("Sorry you have lost.  Better luck next time.");
        }
    }
    
    private static void processInput(Parser parser, Scanner scanner, CommandHandler handler) {
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
            	handler.visitLocation(cmd.getNoun());
                break;
            case "inventory":
            	System.out.println(player.getInventory());
            	break;
            case "buy":
            	handler.buySweet(cmd.getNoun(), cmd.getQty());
            	break;
            case "sell":
            	handler.sellSweet(cmd.getNoun(), cmd.getQty());
            	break;
            case "help":
            	handler.viewItem("help");
            	break;
            case "view":
            	handler.viewItem(cmd.getNoun());
            	break;
            default:
            	handler.viewItem("help");
            }
        } catch(Exception e) {
        	System.out.println(e.getMessage());
        }
    }

	
	private static void storyIntro(String name) {
		StringBuilder sb = new StringBuilder("In a world where Sugar is outlawed, Our Hero, ");
		sb.append(name);
		sb.append(" is the only hope!\n");
		sb.append("Buy and sell Candy, Donuts and other forbidden treats.\n");
		sb.append("But don't get caught! The Sugar Addicts are depending on you!\n\n");
		sb.append("Type 'help' to see instructions.");
		System.out.println(sb.toString());
	}
	
	public static SweetList getSweetList() {
		return sweetList;
	}
}
