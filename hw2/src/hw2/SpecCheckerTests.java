package hw2;

import static api.BallType.RED;
import static api.BallType.WHITE;
import static api.BallType.YELLOW;
import static api.PlayerPosition.PLAYER_A;
import static api.PlayerPosition.PLAYER_B;



public class SpecCheckerTests {
	public static void main(String args[]) {

		ThreeCushion game = new ThreeCushion(PLAYER_B, 3);
		game.lagWinnerChooses(true, WHITE);
		game.cueStickStrike(WHITE);
		game.cueBallStrike(RED);
		game.cueBallImpactCushion();
		game.cueBallImpactCushion();
		game.cueBallImpactCushion();
		game.cueBallStrike(YELLOW);
		game.endShot();
		System.out.println(game.getPlayerBScore());
		game.cueStickStrike(WHITE);
		game.cueBallImpactCushion();
		game.cueBallStrike(WHITE);
		game.cueBallImpactCushion();
		game.cueBallStrike(WHITE);
		game.cueBallImpactCushion();
		game.cueBallStrike(RED);
		game.endShot();
		System.out.println(game.getPlayerBScore());
		
		ThreeCushion game2 = new ThreeCushion(PLAYER_A, 3);
		game2.lagWinnerChooses(true, WHITE);
		game2.cueStickStrike(WHITE);
		game2.cueBallStrike(RED);
		game2.cueBallImpactCushion();
		game2.cueBallImpactCushion();
		game2.cueBallImpactCushion();
		game2.cueBallStrike(YELLOW);
		game2.endShot();
		game2.cueStickStrike(WHITE);
		game2.cueBallStrike(RED);
		game2.cueBallImpactCushion();
		game2.cueBallImpactCushion();
		game2.cueBallImpactCushion();
		game2.cueBallStrike(YELLOW);
		game2.endShot();
		game2.cueStickStrike(WHITE);
		game2.cueBallStrike(RED);
		game2.cueBallImpactCushion();
		game2.cueBallImpactCushion();
		game2.cueBallImpactCushion();
		game2.cueBallStrike(YELLOW);
		game2.endShot();
		System.out.println(game2.getPlayerBScore());
		System.out.println(game2.isGameOver());
		game2.foul();
		System.out.println(game2.isShotStarted());
		System.out.println(game2.isInningStarted());
		System.out.println(game2.getInning());
		
	}

}
