package Facilities;

import Player.Player;
import java.util.Timer;
import java.util.TimerTask;

public class Furnace {
    Player player;
    Timer timer;

    int output_enum;
    int input_enum1;
    int input_enum2;
    int input_enum3;

    int speed;
    int cycle;

    

    public Furnace(Player player) {
        this.player = player;
        this.speed = 100;

    }

    public void decideTask(int output_enum) {
        this.output_enum = output_enum;
        switch (this.output_enum) {
            case 1:  //copper bar
                this.cycle = 60;
                break;
            case 2:  //tin bar
                this.cycle = 30;
            case 3:  //bronze bar
                this.cycle = 180;
            default:
                break;
        }
    }



    public void smelt() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new FurnaceSmeltTask(), 0, (this.cycle * 100000 / speed));
    }

    private class FurnaceSmeltTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("Furnace produced " + produceResources() + " resources.");
            player.updateSmeltedResource(output_enum, produceResources());
        }
    }

    private int produceResources() {
        switch (this.output_enum) {
            case 1:
                if(this.player.getRawResource(1) >= 100) {
                    this.player.updateRawResource(1, -100);
                    return 1;  //add bonus resource logic here. 
                }
                else {
                    return 0;
                }
                
            case 2: 
                if(this.player.getRawResource(2) >= 100) {
                    this.player.updateRawResource(2, -100);
                    return 1;  //add bonus resource logic here. 
                }
                else {
                    return 0;
                } 
            case 3: 
                if(this.player.getSmeltedResource(1) >= 5 &&
                   this.player.getSmeltedResource(2) >= 5) {
                    this.player.updateSmeltedResource(1, -5);
                    this.player.updateSmeltedResource(2, -5);
                    return 1;  //add bonus resource logic here. 
                }
                else {
                    return 0;
                } 
            default:
                return 0;
        }

        
    }

    
}
