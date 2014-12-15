/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g8;

import battleship.interfaces.BattleshipsPlayer;
import static jdk.nashorn.internal.runtime.Debug.id;
import tournament.player.PlayerFactory;

/**
 *
 * @author Ben
 */
public class G8Factory implements PlayerFactory<BattleshipsPlayer> {

    private static int nextID = 1;
    private final int id;

    public G8Factory() {
        id = nextID++;
    }

    @Override
    public BattleshipsPlayer getNewInstance() {
        return new G8AI();
    }

    @Override
    public String getID() {
        return "G8";
    }

    @Override
    public String getName() {
        return "Captain Cook";
    }
}
