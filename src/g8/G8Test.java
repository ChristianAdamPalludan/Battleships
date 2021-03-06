package g8;

import battleship.implementations.Battleships;
import battleship.interfaces.BattleshipsPlayer;
import tournament.game.GameInstance;
import tournament.game.GameResult;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Adam
 */
public class G8Test {
    public static void main(String[] args) {
        
         BattleshipsPlayer player1 = new g8.G8AI();
        BattleshipsPlayer player2 = new battleship.examples.SystematicShotPlayer();
        GameInstance<BattleshipsPlayer> game = Battleships.getSingleGameInstance();
        GameResult res = game.run(player1, player2);
        System.out.println("Result: ");
        System.out.println("player1 major (Points for the game): " + res.majorPointsA);
        System.out.println("player2 major (Points for the game): " + res.majorPointsB);
        System.out.println("player1 minor (Rounds won): " + res.minorPointsA);
        System.out.println("player2 minor (Rounds won): " + res.minorPointsB);
        
    }
    
}
