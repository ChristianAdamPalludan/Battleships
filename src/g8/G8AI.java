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
import java.util.Stack;

/**
 *
 * @author Ben
 */
public class G8AI implements BattleshipsPlayer {

    Position lastShot = new Position(10, 10);
    private boolean hit;
    Stack<Position> specialfire = new Stack();
    ArrayList<Position> shotsFired = new ArrayList<>();
    private boolean offset = false;
    private boolean[][] shipMap;
    private boolean vertical;
    private final static Random rnd = new Random();
    private int sizeX;
    private int sizeY;
    private int place = 1;
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

        if (specialfire.isEmpty()) {

            Position shot = new Position(nextX, nextY);

            while (!validShot(shot)) {
                nextX += 2;
                if (nextX >= sizeX) {
                    if (!offset) {
                        nextX = 1;
                        offset = true;
                    } else {
                        nextX = 0;
                        offset = false;
                    }
                    ++nextY;
                    if (nextY >= sizeY) {
                        nextY = 0;
                        break;
                    }
                }
                shot = new Position(nextX, nextY);
            }
                lastShot = shot;
                shotsFired.add(shot);
                return shot;
           
        } else {
            shotsFired.add(specialfire.peek());
            lastShot = specialfire.peek();
            return specialfire.pop();

        }
    }

    @Override
    public void hitFeedBack(boolean hit, Fleet enemyShips
    ) {
        this.hit = hit;
        if (hit) {
            Position top = new Position(lastShot.x, lastShot.y - 1);
            Position bottom = new Position(lastShot.x, lastShot.y + 1);
            Position left = new Position(lastShot.x - 1, lastShot.y);
            Position right = new Position(lastShot.x + 1, lastShot.y);

            if (validShot(left)) {
                specialfire.push(left);

            }
            if (validShot(right)) {
                specialfire.push(right);

            }
            if (validShot(top)) {
                specialfire.push(top);

            }
            if (validShot(bottom)) {
                specialfire.push(bottom);

            }

        }

    }

    private boolean validShot(Position position) {
        if (!(position.x < 0) && !(position.x > sizeX - 1) && !(position.y < 0) && !(position.y > sizeY - 1)) {
            return unUsed(position);

        }
        return false;
    }

    private boolean unUsed(Position position) {
        for (Position shot : shotsFired) {
            if (shot.x == position.x && shot.y == position.y) {
                return false;
            }

        }
        return true;

    }

    @Override
    public void startMatch(int rounds) {
        //Do nothing
    }

    @Override
    public void startRound(int round) {
        this.shotsFired.clear();
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
