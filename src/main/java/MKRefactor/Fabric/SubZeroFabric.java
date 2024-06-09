/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MKRefactor.Fabric;


import MKRefactor.Game.Player;
import MKRefactor.Character.SubZero;

/**
 *
 * @author Мария
 */
public class SubZeroFabric implements EnemyFabricInterface {

    @Override
    public Player create() {
        Player enemy;
        enemy = new SubZero(1, 60, 16, 1);
        return enemy;
    }

}
