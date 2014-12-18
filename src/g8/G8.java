/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g8;

import battleship.interfaces.BattleshipsPlayer;
import tournament.player.PlayerFactory;

/**
 *
 * @author Ben
 */
public class G8 implements PlayerFactory<BattleshipsPlayer> {

    public G8() {
    }
    
    public static PlayerFactory<BattleshipsPlayer> getPlayerFactory()
    {
        return new G8();
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
