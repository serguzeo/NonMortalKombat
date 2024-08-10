/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MortalCombat.OldGame;

/**
 *
 * @author Мария
 */
public class Items {

    private String name;
    private int amount;

    public Items(String n, int c){
        this.name = n;
        this.amount = c;
    }
    public void setName(String s){
        this.name = s;
    }
    public void setAmount(int c){
        this.amount += c;
    }
    public String getName(){
        return this.name;
    }
    public int getAmount(){
        return this.amount;
    }
}
