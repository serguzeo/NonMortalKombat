/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MKRefactor.Fabric;

import MKRefactor.Game.Player;

/**
 *
 * @author Мария
 */
public class EnemyFabric {
    public Player create(int i) {
        EnemyFabricInterface fabric = null;
        switch (i) {
            case 0:
                fabric = new BarakaFabric();
                break;
            case 1:
                fabric = new SubZeroFabric();
                break;
            case 2:
                fabric = new LiuKangFabric();
                break;
            case 3:
                fabric = new SonyaBladeFabric();
                break;
            case 4:
                fabric = new ShaoKahnFabric();
                break;
        }
        Player enemy = fabric.create();
        return enemy;
    }
}