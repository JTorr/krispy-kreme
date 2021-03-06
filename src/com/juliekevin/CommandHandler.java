package com.juliekevin;

import java.util.List;
import java.util.Scanner;

import com.juliekevin.model.Area;
import com.juliekevin.model.CoinPurse;
import com.juliekevin.model.Command;
import com.juliekevin.model.EventGenerator;
import com.juliekevin.model.Gang;
import com.juliekevin.model.Loan;
import com.juliekevin.model.Supplier;

public class CommandHandler {
	Character self;
	EventGenerator EG;

	public CommandHandler(Character self) {
		this.self = self;
		this.EG = new EventGenerator(self);
	}
	
	public void processInput(Parser parser, Scanner scanner) {
    	try {
    		System.out.print("Day " + Game.getDay() + ", " + self.getLocation().getName() + " > ");
            String input = scanner.nextLine();
            Command cmd = parser.parse(input);
            
            switch(cmd.getVerb()) {
            case "quit": 
            	System.out.println("Exiting...");
                Game.status = "quit";
                break;
            case "go":
            	visitLocation(cmd.getNoun());
                break;
            case "inventory":
            	System.out.println(self.getInventory());
            	break;
            case "buy":
            	buySweet(cmd.getNoun(), cmd.getQty());
            	break;
            case "sell":
            	sellSweet(cmd.getNoun(), cmd.getQty());
            	break;
            case "help":
            	viewItem("help");
            	break;
            case "view":
            	viewItem(cmd.getNoun());
            	break;
            case "get":
            	getItem(cmd.getNoun(), cmd.getQty());
            	break;
            case "pay":
            	payLoan(cmd.getQty());
            	break;
            default:
            	viewItem("help");
            }
        } catch(Exception e) {
        	System.out.println(e.getMessage());
        }
    }

	public void buySweet(String name, int quantity) {
		Location location = this.self.getLocation();
		Supplier sup = location.getSupplier();
		Stash stashList = this.self.getStash();

		int supplierQty = sup.getAvailableQty(name);

		if (supplierQty == 0) {
			System.out.println("That Sweet is not available from this supplier.");
			return;
		}

		if (quantity > supplierQty) {
			System.out.println("Supplier only has " + supplierQty + " available.");
			return;
		}

		Stash supplierStash = sup.getInventory();

		if (supplierStash.containsItem(name)) {
			Sweet sweet = Game.sweetList.findByName(name);
			String price = CoinPurse.getLocalPrice(sweet.getPrice(), location.getPriceMod());

			String total = CoinPurse.getTotalPrice(price, quantity);
			try {
				self.wallet.spendMoney(total);
				int currentQty = 0;
				if (stashList.containsItem(name)) {
					currentQty = stashList.getItemQty(name);
				}
				supplierStash.setQty(name, supplierQty - quantity);
				stashList.addNewItem(name, currentQty + quantity);
				stashList.setLastPurchasePrice(name, price);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Price is " + total);
				System.out.println("Your funds: " + self.wallet.getMoney());
				System.out.println("Insufficient funds!!!");
				return;
			}
			System.out.println("You now have " + quantity + " of " + sweet.getName() + ".");

			System.out.println("You have $" + self.wallet.getMoney() + ".");
		} else {
			System.out.println("Sweet name not found.");
		}
	}
	
	public void getItem(String noun, int qty) {
		if(noun.equalsIgnoreCase("loan")) {
			getLoan(qty);
		} else {
			System.out.println("'Get' command not recognized.");
		}
	}
	
	public void getLoan(int amt) {
		List<Gang> areaGangs = self.getArea().getGangs();
		if(areaGangs.size() < 1) {
			System.out.println("Sorry, there are no gangs available to make you a loan.");
			return;
		}
		System.out.println("Which gang do you want to request a loan from?");
		for(int i=0; i < areaGangs.size(); i++) {
			Gang gang = areaGangs.get(i);
			System.out.println(gang.getName() + ": reputation " + gang.getReputation());
		}
		System.out.print(" > ");
        String input = Game.scanner.nextLine();
        Gang gang = self.getArea().findGangByName(input);
        if(gang == null) {
        	System.out.println("Gang name not found. Please try again.");
        	return;
        }
        
        Boolean loanMade = gang.createNewLoan(amt);
        if(loanMade) {
        	System.out.println(gang.getName() + " has loaned you $" + amt);
            System.out.println("Pay at least 1/10th of the loan amount every 10 days, or you'll regret it!");

        }
     }
	
	public void payLoan(int amt) {
		Character player = Game.getPlayer();
		if(player.loans.size() < 1) {
			System.out.println("You do not have any outstanding loans.");
			return;
		}
		System.out.println("Which gang do you want to repay a loan to?");
		List<Loan> loans = player.getLoans();
		for(int i=0; i < loans.size(); i++) {
			Loan loan = loans.get(i);
			System.out.print(loan.getGangOwed().getName() + ", total owed: " + loan.getAmtOwed());
			System.out.println(" , min payment: " + loan.getMinPayment());
		}
		System.out.print(" > ");
        String input = Game.scanner.nextLine();
        Gang gang = self.getArea().findGangByName(input);
        if(gang == null) {
        	System.out.println("Gang name not found. Please try again.");
        	return;
        } else {
        	payGang(player, gang, amt);
        }
	}
	
	private void payGang(Character player, Gang gang, int amt) {
		try {
			Loan loan = player.findLoan(gang);
			if(loan == null) {
				System.out.println("Loan not found.");
				return;
			}
			player.wallet.spendMoney(Integer.toString(amt));
			loan.makePayment(amt);
		} catch(Exception e) {
			System.out.println("Loan payment could not be completed. Please ensure you have enough funds.");
			System.out.println(e.getMessage());
		}
	}

	public void sellSweet(String name, int quantity) {
		Location location = this.self.getLocation();
		Stash stashList = this.self.getStash();

		if (!stashList.containsItem(name)) {
			System.out.println("Sweet does not exist in your inventory.");
			return;
		}
		
		Sweet sweet = Game.sweetList.findByName(name);
		if (stashList.getItemQty(name) < quantity) {
			System.out.println("Insufficient quantity of " + sweet.getName() + ".");
			System.out.println("You have " + stashList.getItemQty(name) + " of " + sweet.getName() + ".");
			return;
		}

		if (!location.junkieWillBuy(name, quantity)) {
			System.out.println("Sweet Junkies at this location are not interested in that item / quantity.");
			return;
		}
		
		// Sale will be unsuccessful if Buyer is under-cover cop
		Boolean successfulSale = location.junkie.purchaseSweet(quantity);
		if(!successfulSale) {
			return;
		}

		String price = CoinPurse.getLocalPrice(sweet.getPrice(), location.getPriceMod());
		String total = CoinPurse.getTotalPrice(price, quantity);
		self.wallet.earnMoney(total);

		int newQty = stashList.getItemQty(name) - quantity;
		stashList.addNewItem(name, newQty);


		System.out.println("You sold " + quantity + " of " + sweet.getName());
		System.out.println("You earned $" + total.toString() + " and now have $" + self.wallet.getMoney());
	}

	public void visitLocation(String loc) {
		System.out.println("Going to " + loc + "\n");
		Area area = self.getArea();
		area.visitLocation(loc);
		Game.incrementDay();
		EG.checkForEvents();
	}

	public void viewItem(String noun) {
		if (noun.matches("supplier|good|price")) {
			viewSupplier();
		} else if (noun.matches("inventory|stash")) {
			printInventory();
		} else if (noun.matches("location")) {
			self.getLocation().printLocationDetails();
		} else if (noun.matches("junkie")) {
			self.getLocation().junkie.printBuyRequest();
		} else if (noun.matches("help")) {
			printHelp();
		} else {
			System.out.println("Command not recognized.");
			printHelp();
		}
	}

	private void printInventory() {
		System.out.println(self.getInventory());
	}

	private void printHelp() {
		System.out.println("Enter commands in the following format: verb + noun (optional) + quantity(optional)");
		System.out.println(
				"Example commands:\n 'go MyCity',\n 'buy donuts 3',\n 'sell 3 donuts',\n 'inventory',\n 'help'\n");
		System.out.println("\nCommon actions include: ");
		Game.getAvailableActions();
	}

	private void viewSupplier() {
		this.self.getLocation().checkItemsForSale();
	}
}
