/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MortalCombat.Fabric;


import MortalCombat.Game.Player;
import MortalCombat.Character.SonyaBlade;

/**
 *
 * @author Мария
 */
public class SonyaBladeFabric implements EnemyFabricInterface {

    @Override
    public Player create() {
        Player enemy;
        enemy = new SonyaBlade(1, 80, 16, 1);
        return enemy;
    }

}
