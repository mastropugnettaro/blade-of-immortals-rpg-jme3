/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgTest.gameplay.rpg.entities;

import sg.atom.gameplay.GameItem;

/**
 *
 * @author hungcuong
 */
public class Weapon extends GameItem {

    public Weapon(long id, String name, String type) {
        super(id, name, type);
    }

    public Weapon(long id, String name) {
        super(id, name, "Weapon");
    }

    public boolean isHoldable(Class who) {
        return true;
    }
}
