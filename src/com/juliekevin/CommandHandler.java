package com.juliekevin;

import com.juliekevin.model.Area;
import com.juliekevin.model.CoinPurse;
import com.juliekevin.model.Supplier;

public class CommandHandler {
	Character self;

	public CommandHandler(Character self) {
		this.self = self;
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

		String price = CoinPurse.getLocalPrice(sweet.getPrice(), location.getPriceMod());
		String total = CoinPurse.getTotalPrice(price, quantity);
		self.wallet.earnMoney(total);

		int newQty = stashList.getItemQty(name) - quantity;
		stashList.addNewItem(name, newQty);
		
		location.junkie.purchaseSweet(quantity);

		System.out.println("You sold " + quantity + " of " + sweet.getName());
		System.out.println("You earned $" + total.toString() + " and now have $" + self.wallet.getMoney());
	}

	public void visitLocation(String loc) {
		System.out.println("Going to " + loc);
		Area area = self.getArea();
		area.visitLocation(loc);
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
	}

	private void viewSupplier() {
		this.self.getLocation().checkItemsForSale();
	}
}
