/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MortalCombat.OldGame.Fabric;


import MortalCombat.OldGame.Player;
import MortalCombat.OldGame.Character.LiuKang;

/**
 *
 * @author Мария
 */
public class LiuKangFabric implements EnemyFabricInterface {

    @Override
    public Player create() {
        Player enemy;
        enemy = new LiuKang(1, 70, 20, 1);
        return enemy;
    }
}
