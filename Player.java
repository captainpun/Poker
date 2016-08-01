package Poker;

public class Player {

	private int money;
	private String name;
	
	Player(String name, int startAmt){
		this.name = name;
		this.money = startAmt;
	}
	
	public int getMoney(){
		return money;
	}
	
	public void addMoney(int amt){
		money += amt;
	}
	
	public void deductMoney(int amt) throws InsufficientMoneyException{
		if (money > amt){
			money -= amt;
		} else{
			throw new InsufficientMoneyException(amt, this);
		}
	}
	
	public String getName(){
		return this.name;
	}
	
	public String toString(){
		return getName() + "; Stack: " + getMoney();
	}
}

class InsufficientMoneyException extends Exception{
	private int overdrawn;
	
	public InsufficientMoneyException(int amt, Player p){
		int overdrawn = amt - p.getMoney();
	}
	
	public int overdrawnAmt(){
		return overdrawn;
	}
}