package com.juliekevin;

import java.util.Scanner;
import com.juliekevin.*;
import com.juliekevin.Character;

public class KrispyKreme {

    public static void main(String [] args) {
        boolean quit = false;
        boolean won = false;
        
        System.out.println("Welcome to Krispy Kreme. What is your name?");
        Scanner scanner = new Scanner(System.in);
        
        Character self = new Character(scanner.nextLine(), "The depths of Hell!");
        


        //  Really rough first crack
        
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
            	self.stash.buyDrug(noun, quantity, self.getLocation(), self);
            } else {
            	help();
            }
        }
        if (won) {
            System.out.println("Congratulations you have won!");
        } else {
            System.out.println("Sorry you have lost.  Better luck next time.");
        }
    }
    
	private static void help() {
		System.out.println("Type 'go' and the name of the location you want to visit.");
    	System.out.println("Type 'inventory' to check your inventory.");
	}
}
