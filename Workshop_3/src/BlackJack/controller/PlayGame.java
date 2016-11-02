package BlackJack.controller;

import BlackJack.view.IView;
import BlackJack.model.Game;
import BlackJack.model.Observer;

public class PlayGame implements Observer {
	private Game a_game;
	private IView a_view;
	private int[] menuOptionsArray;

	public PlayGame(Game a_game, IView a_view){
		this.a_game = a_game;
		this.a_view =a_view;
		a_game.getDealer().addObserver(this);
		a_game.getPlayer().addObserver(this);
	}

	public boolean Play() throws InterruptedException {
		print();
		if (a_game.IsGameOver())
		{
			a_view.DisplayGameOver(a_game.IsDealerWinner());
		}

		menuOptionsArray = a_view.getMenuArray();
		int input = a_view.GetInput();

		if (input == menuOptionsArray[0])
		{
			a_game.NewGame();
		}
		else if (input == menuOptionsArray[1])
		{
			a_game.Hit();
		}
		else if (input == menuOptionsArray[2])
		{
			a_game.Stand();
		}
		return input != menuOptionsArray[3];
	}
	private void print(){
		a_view.DisplayWelcomeMessage();
		a_view.DisplayDealerHand(a_game.GetDealerHand(), a_game.GetDealerScore());
		a_view.DisplayPlayerHand(a_game.GetPlayerHand(), a_game.GetPlayerScore());
	}
	@Override
	public void Update() {
		print();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
