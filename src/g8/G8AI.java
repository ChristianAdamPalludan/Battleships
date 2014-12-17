/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g8;

import battleship.interfaces.BattleshipsPlayer;
import battleship.interfaces.Board;
import battleship.interfaces.Fleet;
import battleship.interfaces.Position;
import battleship.interfaces.Ship;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 *
 * @author Ben
 */
public class G8AI implements BattleshipsPlayer {
    
    ArrayList<Position> posit = new ArrayList();
    private final static Random rnd = new Random();
    private int sizeX;
    private int sizeY;
    private int threes =1;
    private int place =1;
    private int column = 2;
    private int row = 2;
    private int nextX;
    private int nextY;

    public G8AI() {

    }

    @Override
    public void placeShips(Fleet fleet, Board board) {
      
            
        switch (place) {
            case 1:
                vertical(fleet, board);
                place++;
                break;
            case 2:
                horizontal(fleet, board);
                place++;
                break;
            case 3:
                systemHorizontal(fleet, board);
                place ++;
                break;
            case 4:
                systemVertical(fleet, board);
                place =1;
                break;
            
        
        }
    }
            
            
            
            
            

    public void vertical(Fleet fleet, Board board) {
        sizeX = board.sizeX();
        sizeY = board.sizeY();
        for (int i = 0; i < fleet.getNumberOfShips(); ++i) {

            Ship s = fleet.getShip(i);
            boolean vertical = true;
            Position pos;
            if (vertical) {

                int x = rnd.nextInt(sizeX);
                int y = rnd.nextInt(sizeY - (s.size() - 1));
                pos = new Position(x, y);
            } else {
                int x = rnd.nextInt(sizeX - (s.size() - 1));

                int y = rnd.nextInt(sizeY);
                pos = new Position(x, y);
            }
            board.placeShip(pos, s, vertical);
        }
    }

    public void horizontal(Fleet fleet, Board board) {
        sizeX = board.sizeX();
        sizeY = board.sizeY();
        for (int i = 0; i < fleet.getNumberOfShips(); ++i) {

            Ship s = fleet.getShip(i);
            boolean vertical = false;
            Position pos;
            if (vertical) {
                int x = rnd.nextInt(sizeX);
                int y = rnd.nextInt(sizeY - (s.size() - 1));
                pos = new Position(x, y);
            } else {
                int x = rnd.nextInt(sizeX - (s.size() - 1));
                int y = rnd.nextInt(sizeY);
                pos = new Position(x, y);
            }
            board.placeShip(pos, s, vertical);
        }
    }
    public void systemVertical(Fleet fleet, Board board){
         nextX = 1;
        sizeX = board.sizeX();
        sizeY = board.sizeY();
        for(int i = 0; i < fleet.getNumberOfShips(); ++i)
        {
            Ship s = fleet.getShip(i);
            boolean vertical = true;
            Position pos;

                int x = nextX;
                int y = rnd.nextInt(sizeY-(s.size()-1));
                pos = new Position(x, y);

                nextX++;
            board.placeShip(pos, s, vertical);
        }
        
    }
    public void systemHorizontal(Fleet fleet, Board board){
        nextY = 1;
        sizeX = board.sizeX();
        sizeY = board.sizeY();
        for(int i = 0; i < fleet.getNumberOfShips(); ++i)
        {
            Ship s = fleet.getShip(i);
            boolean vertical = false;
            Position pos;
                int y = nextY;
               int x = rnd.nextInt(sizeX-(s.size()-1));
                
                
                pos = new Position(x, y);

                nextY++;
            board.placeShip(pos, s, vertical);
        }
    }
    public void twoShips(Fleet fleet, Board board){
        nextY = 2;
        sizeX = board.sizeX();
        sizeY = board.sizeY();
        for(int i = 0; i < 2; ++i)
        {
            Ship s = fleet.getShip(i);
            boolean vertical = false;
            Position pos;
                int y = nextY;
               int x = rnd.nextInt(sizeX-(s.size()-1));
                
                
                pos = new Position(x, y);

                nextY++;
            board.placeShip(pos, s, vertical);
        }
    }
    public void systemMix(Fleet fleet, Board board){
          nextX = 1;
        nextY = 1;
        sizeX = board.sizeX();
        sizeY = board.sizeY();
        for(int i = 0; i < fleet.getNumberOfShips(); ++i)
        {
            Ship s = fleet.getShip(i);
            boolean vertical = rnd.nextBoolean();
            Position pos;
            if(vertical)
            {
//                int x = rnd.nextInt(sizeX);
                int x = nextX;
                int y = rnd.nextInt(sizeY-(s.size()-1));
                pos = new Position(x, y);
            }
            else
            {
                int x = rnd.nextInt(sizeX-(s.size()-1));
//                int y = rnd.nextInt(sizeY);
                int y = nextY;
                pos = new Position(x, y);
            }
            board.placeShip(pos, s, vertical);
                nextY++;
                nextX++;
        }
    }
    public void checkCoords(){
        
    }

    @Override
    public void incoming(Position pos) {

        //Do nothing
    }

    @Override
    public Position getFireCoordinates(Fleet enemyShips) {

         Position shot = new Position(nextX, nextY);
        ++nextX;
        if(nextX >= sizeX)
        {
            nextX = 0; 
            ++nextY;
            if(nextY >= sizeY)
            {
                nextY = 0;
            }
        }
        return shot;

    }

    @Override
    public void hitFeedBack(boolean hit, Fleet enemyShips) {
        //Do nothing
    }

    @Override
    public void startMatch(int rounds) {
        //Do nothing
    }

    @Override
    public void startRound(int round) {
        //Do nothing
    }

    @Override
    public void endRound(int round, int points, int enemyPoints) {
        //Do nothing
    }

    @Override
    public void endMatch(int won, int lost, int draw) {
        //Do nothing
    }
}
