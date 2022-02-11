package Networking;

import javafx.scene.text.Text;

// See GameMain.GameEngine for documentation.

public class GameEngine {
	
	Text player1Score;
	Text player2Score;
	int player1ScoreValue, player2ScoreValue;
	
	public GameEngine() {
		
	}
	
	public GameEngine(Text player1Score, Text player2Score) {
		this.player1Score = player1Score;
		this.player2Score = player2Score;
		this.player1ScoreValue = 0; this.player2ScoreValue = 0;
		this.player1Score.setText("Player 1: " + player1ScoreValue);
		this.player2Score.setText("Player 2: " + player2ScoreValue);
	}
	
	
	public Text getPlayer1Score() {
		return player1Score;
	}
	
	public Text getPlayer2Score() {
		return player2Score;
	}
	
	public void setPlayer1Score(Text player1Score) {
		this.player1Score = player1Score;
	}
	
	public void setPlayer2Score(Text player2Score) {
		this.player2Score = player2Score;
	}
	
	public void setPlayer1ScoreValue(int score) {
		this.player1ScoreValue = score;
		this.player1Score.setText("Player 1: " + this.player1ScoreValue);
	}
	
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
