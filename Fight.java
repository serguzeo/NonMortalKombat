/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

//ADD IMAGE!!!
/*    ImageIcon icon1 = createIcon("images/settings.jpg");
    JLabel label = new JLabel(icon1);
    panel.add(label).setBounds(10,10,27,30);
*/

/**
 *
 * @author Мария
 */
public class Fight {
    
    private final int kind_fight[][] = {{1,0},{1,1,0},{0,1,0},{1,1,1,1}};
    
    public void Move(Player p1, Player p2){
        
        switch (Integer.toString(p1.getAttack())+Integer.toString(p2.getAttack())){
            case "10":
                p2.setHealth(-(int)(p1.getDamage()*0.5));
                System.out.println(p1.getDamage()*0.5);
                break;
            case "11":
                p2.setHealth(-p1.getDamage());
                System.out.println(p1.getDamage());
                break;
            case "00": {
                double i = Math.random();
                if (i>=0.5){
                    //оглушение
                }
                System.out.println(0);
                break;}
            case "01":
                System.out.println(0);
                break;
        }         
    }
    
    public int[] ChooseBehavior(Player enemy){
       int arr[]=null;
       double i = Math.random();     
       if (enemy instanceof Tank){
           if (i<0.15){
               arr=kind_fight[0];
           }
           if (i>=0.15 & i<0.3){
               arr=kind_fight[1];
           }
           if (i>=0.3 & i<0.9){
               arr=kind_fight[2];
           }
           if (i>=0.9 & i<1){
               arr=kind_fight[3];
           }
       }
       if (enemy instanceof Mage){
           if (i<0.25){
               arr=kind_fight[0];
           }
           if (i>=0.25 & i<0.5){
               arr=kind_fight[1];
           }
           if (i>=0.5 & i<1){
               arr=kind_fight[3];
           }
       }
       if (enemy instanceof Fighter){
           if (i<0.125){
               arr=kind_fight[0];
           }
           if (i>=0.125 & i<0.25){
               arr=kind_fight[1];
           }
           if (i>=0.25 & i<0.35){
               arr=kind_fight[2];
           }
           if (i>=0.35 & i<1){
               arr=kind_fight[3];
           }
       }
       if (enemy instanceof Soldier){
           if (i<0.25){
               arr=kind_fight[0];
           }
           if (i>=0.25 & i<0.5){
               arr=kind_fight[1];
           }
           if (i>=0.5 & i<1){
               arr=kind_fight[2];
           }
       }
       return arr;
    }
    
    public void Round(Player human, Player enemy){
        int i=1;
        int k=0;
        int kind_attack[]=ChooseBehavior(enemy);
        System.out.println("human "+human.getHealth()+"    enemy "+enemy.getHealth());
        while (human.getHealth()>0 & enemy.getHealth()>0){
            enemy.setAttack(kind_attack[k]);
            if (i%2==1){
                Move(human,enemy);
            }
            else{
                Move(enemy,human);
            }
            i++;
            if (k<kind_attack.length-1){
                k++;
            }
            else {
                kind_attack=ChooseBehavior(enemy);
                k=0;
            }
            System.out.println("human "+human.getHealth()+"    enemy "+enemy.getHealth());
        }
    }
    
}
