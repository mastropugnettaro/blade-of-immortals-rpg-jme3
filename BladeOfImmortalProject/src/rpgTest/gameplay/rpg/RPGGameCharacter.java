/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgTest.gameplay.rpg;

import rpgTest.gameplay.rpg.entities.Weapon;
import sg.atom.gameplay.GameCharacter;

/**
 *
 * @author cuong.nguyenmanh2
 */
public class RPGGameCharacter extends GameCharacter {

    public RPGGameCharacter(String name) {
        super(name);
    }

    private int health = 100;
    // Game charater props
    private Weapon weapon;

    public Weapon getWeapon() {
        return weapon;
    }

    public int getHealth() {
        return health;
    }

    public void hurt(int dam) {
        health -= dam;

        if (health < 0) {
            //moveAction.speed = 0;
        }
    }

    public boolean isDead() {
        return false;
    }
}
