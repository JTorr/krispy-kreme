package com.juliekevin;

import java.util.Scanner;

public class KrispyKreme {

    public static void main(String [] args) {
        boolean quit = false;
        boolean won = false;
        
        System.out.println("Welcome to Krispy Kreme. What is your name?");
        Scanner scanner = new Scanner(System.in);
        
        Character self = new Character(scanner.nextLine(), "Secret Sugar Factory");
        storyIntro(self.name);

        
        System.out.println("You are currently at: " + self.getLocation());
        self.getInventory();

        while (!won && !quit) {
            System.out.print(self.getLocation() + " > ");
            String input = scanner.nextLine();
            //  TODO validation
            String [] words = input.trim().split(" ");
            String verb = words[0].trim();
            String noun = (words.length > 1) ?  words[1].trim() : "";
            if ("quit".equals(verb)) {
                System.out.println("Exiting...");
                quit = true;
            } else if ("go".equals(verb)) {
                System.out.println("Going to " + noun);
                self.setLocation(noun);
            } else if ("inventory".equals(verb)) {
                self.getInventory();
            } else if("buy".equals(verb)) {
            	System.out.println("Quantity?");
            	int quantity = scanner.nextInt();
            	self.getStash().buySweet(noun, quantity, self.getLocation(), self);
            } else if("sell".equals(verb)) {
            	System.out.println("Quantity?");
            	int quantity = scanner.nextInt();
            	self.getStash().sellSweet(noun, quantity, self.getLocation(), self);
            } else {
            	help();
            }
        }
        if (won) {
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
