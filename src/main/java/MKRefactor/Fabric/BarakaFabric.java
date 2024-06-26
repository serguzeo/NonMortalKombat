/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MKRefactor.Fabric;


import MKRefactor.Game.Player;
import MKRefactor.Character.Baraka;

/**
 *
 * @author Мария
 */
public class BarakaFabric implements EnemyFabricInterface {
    /**
     * Создание вражеского персонажа
     */
    @Override
    public Player create() {
        Player enemy;
        enemy = new Baraka(1, 100, 12, 1);
        return enemy;
    }
}