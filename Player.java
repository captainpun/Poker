package Poker;

public class Player {

	private int money;
	private String name;
	
	Player(String name, int startAmt){
		this.name = name;
		this.money = startAmt;
	}
	
	public void getMoney(){
		return money;
	}
	
	public void addMoney(int amt){
		money += amt;
	}
}
