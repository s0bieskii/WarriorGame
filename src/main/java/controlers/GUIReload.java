package controlers;

import logic.Hero;
import logic.Monster;
import logic.gameLogic;

import javax.swing.*;
import java.util.ArrayList;

class GUIReload implements Runnable{
    gameControler controler;
    Hero hero;
    ArrayList<Monster> monsters;
    gameLogic logic;

    public GUIReload(gameControler controler){
        this.controler=controler;
        logic=controler.getLogic();
    }

    @Override
    public void run() {
        long lastTime=0;
        hero=logic.getHero();
        int n=0;
        while (true){
            monsters=logic.getMonsters();
            if(System.currentTimeMillis()-lastTime>1200 && !monsters.isEmpty() && !(logic.getHero().getCurrentHealt()<=0)){
                monsters.stream().forEach(e->hero.takeDamage(e.getDamage()));
                controler.setHealt();
                lastTime=System.currentTimeMillis();
            }
            if(hero.getCurrentHealt()==0){
                JOptionPane.showMessageDialog(null, "Lose!");
                System.exit(0);
            }
        }
    }
}