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
import java.util.List;
import java.util.Random;

/**
 *
 * @author Ben
 */
public class G8AI implements BattleshipsPlayer {

    ArrayList<Position> specialfire;
    private int[][] shotMap;
    private boolean[][] shipMap;
    private boolean vertical;
    private final static Random rnd = new Random();
    private int sizeX;
    private int sizeY;
    private int threes = 1;
    private int place = 1;
    private int column = 2;
    private int row = 2;
    private int nextX;
    private int nextY;
    private int hitX;
    private int hitY;
    private int x;
    private int y;
    private int count;

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
                getRandomPos(fleet, board);
                place++;
                break;
            case 3:
                horizontal(fleet, board);

                place = 1;
                break;

        }
    }

    public void getRandomPos(Fleet fleet, Board board) {
        sizeX = board.sizeX();
        sizeY = board.sizeY();
        shipMap = new boolean[sizeX][sizeY];
        for (int i = 0; i < fleet.getNumberOfShips(); ++i) {

            Ship s = fleet.getShip(i);
            vertical = rnd.nextBoolean();
            Position pos;
            if (vertical) {

                x = rnd.nextInt(sizeX);
                y = rnd.nextInt(sizeY - (s.size() - 1));
                testShip();
                pos = new Position(x, y);
            } else {
                x = rnd.nextInt(sizeX - (s.size() - 1));
                y = rnd.nextInt(sizeY);
                testShip();
                pos = new Position(x, y);

            }
            board.placeShip(pos, s, vertical);
        }
    }

    public void testShip() {
        if (shipMap[x][y] == false) {
            markShips();
        } else {

        }

    }

    public void markShips() {
//        Position pos;
        if (vertical) {
            for (int j = 0; j < y; j++) {
                shipMap[x][y] = true;
            }
        } else {
            for (int j = 0; j < x; j++) {
                shipMap[x][y] = true;
            }
        }
//        pos = new Position(x, y);
//        board.placeShip(pos, null, vertical);
    }

    public void vertical(Fleet fleet, Board board) {
        sizeX = board.sizeX();
        sizeY = board.sizeY();
        for (int i = 0; i < fleet.getNumberOfShips(); ++i) {

            Ship s = fleet.getShip(i);
            boolean vertical = true;
            Position pos;
            if (vertical) {

                x = rnd.nextInt(sizeX);
                y = rnd.nextInt(sizeY - (s.size() - 1));
                pos = new Position(x, y);
            } else {
                x = rnd.nextInt(sizeX - (s.size() - 1));

                y = rnd.nextInt(sizeY);
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
                x = rnd.nextInt(sizeX);
                y = rnd.nextInt(sizeY - (s.size() - 1));
                pos = new Position(x, y);
            } else {
                x = rnd.nextInt(sizeX - (s.size() - 1));
                y = rnd.nextInt(sizeY);
                pos = new Position(x, y);

            }
            board.placeShip(pos, s, vertical);
        }
    }

    public void systemVertical(Fleet fleet, Board board) {
        nextX = 1;
        sizeX = board.sizeX();
        sizeY = board.sizeY();
        for (int i = 0; i < fleet.getNumberOfShips(); ++i) {
            Ship s = fleet.getShip(i);
            boolean vertical = true;
            Position pos;

            x = nextX;
            y = rnd.nextInt(sizeY - (s.size() - 1));
            pos = new Position(x, y);

            nextX++;
            board.placeShip(pos, s, vertical);
        }

    }

    public void systemHorizontal(Fleet fleet, Board board) {
        nextY = 1;
        sizeX = board.sizeX();
        sizeY = board.sizeY();
        for (int i = 0; i < fleet.getNumberOfShips(); ++i) {
            Ship s = fleet.getShip(i);
            boolean vertical = false;
            Position pos;
            y = nextY;
            x = rnd.nextInt(sizeX - (s.size() - 1));

            pos = new Position(x, y);

            nextY++;
            board.placeShip(pos, s, vertical);
        }
    }

    @Override
    public void incoming(Position pos) {

        //Do nothing
    }

    @Override
    public Position getFireCoordinates(Fleet enemyShips) {
        
        Position shot = new Position(nextX, nextY);
        
//        if (specialfire.size() != 0) {
//            for (int i = 0; i < specialfire.size(); i++) {
//                shot = specialfire.get(i);
//                System.out.println("shooting");
//                specialfire.remove(i);
//            }
//
//        } 
//        else {
//        }
 
            ++nextX;
            if (nextX >= sizeX) {
                nextX = 0;
                ++nextY;
                if (nextY >= sizeY) {
                    nextY = 0;
                }
            }
//        }

        return shot;
    }

    @Override
    public void hitFeedBack(boolean hit, Fleet enemyShips) {
//        if (hit) 
//            count++;
//            System.out.println(count);
//            specialfire.add(new Position(x - 1, y));
//            specialfire.add(new Position(x + 1, y));
//            specialfire.add(new Position(x, y - 1));
//            specialfire.add(new Position(x, y + 1));

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
