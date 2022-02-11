package GameMain;

import javafx.scene.text.Text;

public class GameEngine {
	
	//Game game;
	Text player1Score;
	Text player2Score;
	int player1ScoreValue, player2ScoreValue;
	
	/**
	 * Empty constructor
	 */
	public GameEngine() {
		
	}
	
	/**
	 * Constructor
	 * Takes main menu text fields of score
	 * @param player1Score text field from main menu of player 1 score
	 * @param player2Score text field from main menu of player 2 score
	 */
	public GameEngine(Text player1Score, Text player2Score) {
		this.player1Score = player1Score;
		this.player2Score = player2Score;
		this.player1ScoreValue = 0; this.player2ScoreValue = 0;
		this.player1Score.setText("Player 1: " + player1ScoreValue);
		this.player2Score.setText("Player 2: " + player2ScoreValue);
	}
	
	/**
	 * This method gets the player 1 score text
	 * @return Text corresponding to player 1 score
	 */
	public Text getPlayer1Score() {
		return player1Score;
	}
	
	/** This method gets the player 2 score text
	 * @return Text corresponding to player 2 score
	 */
	public Text getPlayer2Score() {
		return player2Score;
	}
	
	/**
	 * This method sets the player 1 score text
	 * @param player1Score the player 1 score text field
	 */
	public void setPlayer1Score(Text player1Score) {
		this.player1Score = player1Score;
	}
	
	/**
	 * This method sets the player 2 score text
	 * @param player2Score the player 2 score text field
	 */
	public void setPlayer2Score(Text player2Score) {
		this.player2Score = player2Score;
	}
	
	/**
	 * This method sets the player 1 score value and then sets the player 1 score text according to the value
	 * @param score the player 1 score integer to set
	 */
	public void setPlayer1ScoreValue(int score) {
		this.player1ScoreValue = score;
		this.player1Score.setText("Player 1: " + this.player1ScoreValue);
	}
	
	/**
	 * This method sets the player 2 score value and then sets the player 2 score text according to the value
	 * @param score the player 2 score integer to set
	 */
	public void setPlayer2ScoreValue(int score) {
		this.player2ScoreValue = score;
		this.player2Score.setText("Player 2: " + this.player2ScoreValue);
	}
	public void setPlayer1Value(int score) {
		this.player1ScoreValue = score;
	}
	 public void setPlayer2Value(int score) {
		this.player2ScoreValue = score;
	}
	public int getPlayer1ScoreValue() {
		return player1ScoreValue;
	}
	public int getPlayer2ScoreValue() {
		return player2ScoreValue;
	}
}