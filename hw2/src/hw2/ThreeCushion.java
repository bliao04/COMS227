package hw2;

import api.PlayerPosition; 
import api.BallType;
import static api.PlayerPosition.*;
import static api.BallType.*;

/**
 * Class that models the game of three-cushion billiards.
 * @author Brandon Liao
 */
public class ThreeCushion {
	
	/**
	 * Instance variable tracking the winner of the lag.
	 */
	private PlayerPosition lagWin;
	
	/**
	 * Instance variable tracking the number of points needed to win the game.
	 */
	private int pointsToW;
	
	/**
	 * Instance variable tracking the inning count.
	 */
	private int inningCount = 1; 
	
	/**
	 * Instance variable tracking the current player.
	 */
	private PlayerPosition curPlayer;
	
	/**
	 * Instance variable tracking if the current shot is the break shot or not.
	 */
	private boolean isBreakShot;
	
	/**
	 * Instance variable tracking the score of PLAYER_A
	 */
	private int playerAScore;
	
	/**
	 * Instance variable tracking the score of PLAYER_B
	 */
	private int playerBScore;
	
	/**
	 * Instance variable tracking which type of ball the PLAYER_A is using.
	 */
	private BallType playerACueBall;
	
	/**
	 * Instance variable tracking the type of ball PLAYER_B is using.
	 */
	private BallType playerBCueBall;
	
	/**
	 * Instance variable tracking if the lag winner has be chosen or not.
	 */
	private boolean lagWinnerChose = false;
	
	/**
	 * Instance variable tracking if a shot has been started or not.
	 */
	private boolean isShotStarted = false;
	
	/**
	 * Instance variable tracking if the inning is started or not.
	 */
	private boolean isInningStarted = false;
	
	/**
	 * Instance variable tracking if a foul has been committed or not. 
	 */
	private boolean isFoul = false;
	
	/**
	 * Instance variable tracking if the shot has met all requirements to score a point. 
	 */
	private boolean isShotValid = false;
	
	/**
	 * Instance variable tracking the amount of times the ball has hit the cushion.
	 */
	private int cushionImpactCount;
	
	/**
	 * Instance variable tracking if the red ball has been hit or not. 
	 */
	private boolean isRedHit;
	
	/**
	 * Instance variable keeping track of the amount of balls hit (not including cue ball) during the current inning.
	 */
	private int ballsHitCount = 0;
	
	/**
	 * Instance variable tracking if the shot is a bank shot or not.
	 */
	private boolean isBankShot;
	
	/**
	 * Instance variable tracking if 3 or more cushions were hit before the first ball.
	 */
	private boolean cushionsHitBeforeBall;

	/**
	 * Instance variable tracking if the game is over or not.
	 */
	private boolean isGameOver = false; 
	
	/**
	 * Creates a new game of three cushion billiards with a given lag winner and the predetermined number of points required to win the game.
	 * @param lagWinner
	 * @param pointsToWin
	 */
	public ThreeCushion(PlayerPosition lagWinner, int pointsToWin) {
		  lagWin = lagWinner;
		  pointsToW = pointsToWin;
		  
	}

	/**
	 * Returns a one-line string representation of the current game state. The
	 * format is:
	 * <p>
	 * <tt>Player A*: X Player B: Y, Inning: Z</tt>
	 * <p>
	 * The asterisks next to the player's name indicates which player is at the
	 * table this inning. The number after the player's name is their score. Z is
	 * the inning number. Other messages will appear at the end of the string.
	 * 
	 * @return one-line string representation of the game state
	 */
	public String toString() {
		String fmt = "Player A%s: %d, Player B%s: %d, Inning: %d %s%s";
		String playerATurn = "";
		String inningStatus = "";
		String playerBTurn = "";
		String gameStatus = "";
		if (getInningPlayer() == PLAYER_A) {
			playerATurn = "*";
		} else if (getInningPlayer() == PLAYER_B) {
			playerBTurn = "*";
		}
		if (isInningStarted()) {
			inningStatus = "started";
		} else {
			inningStatus = "not started";
		}
		if (isGameOver()) {
			gameStatus = ", game result final";
		}
		return String.format(fmt, playerATurn, getPlayerAScore(), playerBTurn, getPlayerBScore(), getInning(),
				inningStatus, gameStatus);
	}
	
	/**
	 * Indicates the given ball has impacted the given cushion.
	 */
	public void cueBallImpactCushion() {
		if ((isGameOver == false) && (isShotStarted == true)) {
			cushionImpactCount += 1;
		}
	}
	
	/**
	 * Indicates the player's cue ball has struck the given ball.
	 * @param ball
	 */
	public void cueBallStrike(BallType ball) {
		if ((isGameOver == false) && (isShotStarted == true)) {
			BallType playerCueBall;
			if (curPlayer == PLAYER_A) {
				playerCueBall = playerACueBall;
			} else {
				playerCueBall = playerBCueBall;
			}
			// Check to see if break shot is true.
			if (isBreakShot == true) {
				if ((ball == RED) && (cushionImpactCount == 0)) {
					isRedHit = true;
					ballsHitCount += 1;
				} else {
					isFoul = true;
					foul();
				}	
				isBreakShot = false;
				// Then check the number of balls hit if it is not a break shot.
				} else if (ballsHitCount == 0) {
					if (cushionImpactCount >= 3) {
						cushionsHitBeforeBall = true;
					}
					if (ball !=(playerCueBall)) {
						if (ball == RED) {
							isRedHit = true;
							ballsHitCount += 1;
						} else {
							ballsHitCount += 1;
						}
					} 
			// Then check the number of balls hit another ball (not the cue ball) has already been hit. 
			} else if (ballsHitCount == 1) {
				if (ball !=(playerCueBall)) {
					if (ball == RED) {
						if (isRedHit == false) {
							isRedHit = true;
							ballsHitCount +=1;
						}
					} else {
						if (isRedHit == true) {
							ballsHitCount +=1;
						}
					}
				}
			
			}
			//Checks to see if the current shot met all the requirements to be a bank shot.
			if ((cushionsHitBeforeBall == true) && (ballsHitCount == 2)) {
				isBankShot = true;
			}
		}
	}
	
	/**
	 * Indicates the cue stick has struck the given ball.
	 * @param ball
	 */
	public void cueStickStrike(BallType ball) {
		if (isGameOver == false)  {
			if (lagWinnerChose == true) {
				BallType playerCueBall;
				//Check the current player and sets the current players cue ball to whatever the current players cue ball is. 
				if (curPlayer == PLAYER_A) {
					playerCueBall = playerACueBall;
				} else {
					playerCueBall = playerBCueBall;
				}
				if (isShotStarted == false) {
					isShotStarted = true;
				if (isInningStarted == false) {
					isInningStarted = true;
				}
					//Check if the ball is the players cue ball, if not, foul. 
					if (playerCueBall == ball) {
						 isFoul = false;
					} else {
						isFoul = true;
						foul();
					}
				} else {
					isFoul = true;
					foul();
				}
				isBankShot = false;
			}
		}
	}
	
	/**
	 * A foul immediately ends the player's inning, even if the current shot has not yet ended.
	 */
	public void foul() {
		if (isGameOver == false) {
			if (lagWinnerChose == true) {
				isShotStarted = false;
				isInningStarted = false;
				inningCount += 1;
				if (curPlayer == PLAYER_A) {
					curPlayer = PLAYER_B;
				} else {
					curPlayer = PLAYER_A;
				}
			}
		}
	}
	
	/**
	 * Indicates that all balls have stopped motion.
	 */
	public void endShot() {
		if ((isGameOver == false) && (isShotStarted == true)) {
			isShotStarted = false;
			//Check to see if the shot is valid for scoring a point.
			if ((cushionImpactCount >= 3) && (ballsHitCount == 2) && (isRedHit == true)) {
				isShotValid = true;
			} else {
				isShotValid = false;
			}
			}
			//Check to see if the shot is valid and no foul has been committed, if either is not true, the shot does not score a point.
			if ((isFoul == false) && (isShotValid == true)) {
				if (curPlayer == PLAYER_A) {
					playerAScore += 1;
					if (playerAScore == 3) {
						isGameOver = true;
					}
				} else {
					playerBScore += 1;
					if (playerBScore == 3) {
						isGameOver = true;
					}
				}
			} else {
				if (isInningStarted == true) {
					isInningStarted = false;
					inningCount += 1;
				}
				//Check to see if a foul has been called before end shot.
				if (isFoul == false) {
					if (curPlayer == PLAYER_A) {
						curPlayer = PLAYER_B;
					} else {
						curPlayer = PLAYER_A;
					}
				}
			}
			ballsHitCount = 0;
			cushionImpactCount = 0;
			cushionsHitBeforeBall = false;
			isRedHit = false;
	}
	
	/**
	 * Gets the cue ball of the current player.
	 * @return
	 */
	public BallType getCueBall() {
		if (curPlayer == PLAYER_A) {
			return playerACueBall;
		} else {
			return playerBCueBall;
		}
	}
	
	/**
	 * Gets the inning number.
	 * @return
	 */
	public int getInning() {
		return inningCount;
	}
	
	/**
	 * Gets the current player.
	 * @return
	 */
	public PlayerPosition getInningPlayer() {
		return curPlayer;
	}
	
	/**
	 * Gets the number of points scored by Player A.
	 * @return
	 */
	public int getPlayerAScore() {
		return playerAScore;
	}
	
	/**
	 * Gets the number of points scored by Player B.
	 * @return
	 */
	public int getPlayerBScore() {
		return playerBScore;
	} 
	
	/**
	 * Returns true if and only if the most recently completed shot was a bank shot.
	 * @return
	 */
	public boolean isBankShot() {
		if ((isBankShot == true) && (isFoul == false)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns true if and only if this is the break shot (i.e., the first shot of the game).
	 * @return
	 */
	public boolean isBreakShot() {
		return isBreakShot;
	}
	
	/**
	 * Returns true if the game is over (i.e., one of the players has reached the designated number of points to win).
	 * @return
	 */
	public boolean isGameOver() {
		if ((playerAScore == pointsToW) || (playerBScore == pointsToW)) {
			isGameOver = true;
			isInningStarted = false;
		} else {
			isGameOver = false;
		}
		return isGameOver;
	}
	
	/**
	 * Returns true if the shooting player has taken their first shot of the inning.
	 * @return
	 */
	public boolean isInningStarted() {
		return isInningStarted;
	}
	
	/**
	 * Returns true if a shot has been taken (see cueStickStrike()), but not ended (see endShot()).
	 * @return
	 */
	public boolean isShotStarted() {
		return isShotStarted;
	}
	
	/**
	 * Sets whether the player that won the lag chooses to break (take first shot), or chooses the other player to break.
	 * @param selfBreak
	 * @param cueBall
	 */
	public void lagWinnerChooses(boolean selfBreak, BallType cueBall) {
		if (lagWinnerChose == false) {
			if (lagWin == PLAYER_A) {
				playerACueBall = cueBall;
				if (cueBall == WHITE) {
					playerBCueBall = YELLOW;
				} else if (cueBall == YELLOW) {
					playerBCueBall = WHITE;
				}
				if (selfBreak == true) {
					curPlayer = PLAYER_A;
				} else {
					curPlayer = PLAYER_B;
				}
			} else if (lagWin == PLAYER_B) {
				playerBCueBall = cueBall;
				if (cueBall == WHITE) {
					playerACueBall = YELLOW;
				} else if (cueBall == YELLOW) {
					playerACueBall = WHITE;
				}
				if (selfBreak == true) {
					curPlayer = PLAYER_B;
				} else {
					curPlayer = PLAYER_A;
				}
			}
		isBreakShot = true;
		lagWinnerChose = true;
		}
	}
}
