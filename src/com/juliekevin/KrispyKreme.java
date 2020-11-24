package com.juliekevin;

import java.util.Scanner;

public class KrispyKreme {

    public static void main(String [] args) {
        boolean quit = false;
        boolean won = false;
        String location = "the gates of hell";
        String inventory = "not a damn thing";

        Scanner scanner = new Scanner(System.in);

        //  Really rough first crack
        System.out.println("Welcome to Krispy Kreme.");
        System.out.println("You are currently at: " + location);
        System.out.println("Your inventory currently contains" + inventory);

        while (!won && !quit) {
            System.out.print(location + " > ");
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
                location = noun;
            } else if ("inventory".equals(verb)) {
                System.out.println("Your inventory currently contains " + inventory);
            }
        }
        if (won) {
            System.out.println("Congratulations you have won!");
        } else {
            System.out.println("Sorry you have lost.  Better luck next time.");
        }
    }
}
