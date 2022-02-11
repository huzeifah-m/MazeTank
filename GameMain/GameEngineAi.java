package GameMain;

import javafx.scene.text.Text;

public class GameEngineAi {

	//Game game;
	Text player1Score;
	Text playerAiScore;
	int player1ScoreValue, playerAiScoreValue;

	/**
	 * Empty constructor
	 */
	public GameEngineAi() {

	}

	/**
	 * Takes main menu text fields of score
	 * @param player1Score text field from main menu of player 1 score
	 * @param playerAiScore text field from main menu of player AI score
	 */
	public GameEngineAi(Text player1Score, Text playerAiScore) {
		this.player1Score = player1Score;
		this.playerAiScore = playerAiScore;
		this.player1ScoreValue = 0; this.playerAiScoreValue = 0;
		this.player1Score.setText("Player 1: " + player1ScoreValue);
		this.playerAiScore.setText("Player Ai: " + playerAiScoreValue);
	}

	/** 
	 * This method gets the player 1 score text
	 * @return Text corresponding to player 1 score
	 */
	public Text getPlayer1Score() {
		return player1Score;
	}

	/**
	 * This method gets the player AI score text
	 * @return Text corresponding to player AI score
	 */
	public Text getPlayerAiScore() {
		return playerAiScore;
	}

	/**
	 * This method sets the player 1 score text
	 * @param player1Score the player 1 score text field
	 */
	public void setPlayer1Score(Text player1Score) {
		this.player1Score = player1Score;
	}

	/**
	 * This method sets the player AI score text
	 * @param playerAiScore the player AI score text field
	 */
	public void setPlayerAiScore(Text playerAiScore) {
		this.playerAiScore = playerAiScore;
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
	 * This method sets the player AI score value and then sets the player AI score text according to the value
	 * @param score the player AI score integer to set
	 */
	public void setPlayerAiScoreValue(int score) {
		this.playerAiScoreValue = score;
		this.playerAiScore.setText("Player Ai: " + this.playerAiScoreValue);
	}
	public void setPlayer1Value(int score) {
		this.player1ScoreValue = score;
	}
	 public void setPlayerAiValue(int score) {
		this.playerAiScoreValue = score;
	}
	public int getPlayer1ScoreValue() {
		return player1ScoreValue;
	}
	public int getPlayerAiScoreValue() {
		return playerAiScoreValue;
	}
}