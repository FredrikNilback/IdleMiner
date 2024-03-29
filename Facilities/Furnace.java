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
            case 0:  //copper bar
                this.cycle = 60;
                smelt();
                break;
            case 1:  //tin bar
                this.cycle = 30;
                smelt();
                break;
            case 2:  //bronze bar
                this.cycle = 180;
                smelt();
                break;
            case 3:  //iron bar
                this.cycle = 270;
                smelt();
                break;
            case 4:  //lead bar
                this.cycle = 300;
                smelt();
                break;
            case 5:  //steel bar
                this.cycle = 360;
                smelt();
                break;
            case 6:  //aluminium bar
                this.cycle = 150;
                smelt();
                break;
            case 7:  //silver bar
                this.cycle = 360;
                smelt();
                break;
            case 8:  //gold bar
                this.cycle = 480;
                smelt();
                break;
            case 9:  //platinum bar
                this.cycle = 570;
                smelt();
                break;
            case 10:  //cobalt bar
                this.cycle = 720;
                smelt();
                break;
            case 11:  //lithium bar
                this.cycle = 360;
                smelt();
                break;
            default:
                break;
        }
    }

    public void smelt() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new FurnaceSmeltTask(), (this.cycle * 100000 / speed), (this.cycle * 100000 / speed));
    }

    private class FurnaceSmeltTask extends TimerTask {
        @Override
        public void run() {
            String resources = "Copper bars.";
            switch (output_enum) {
                case 0:
                    resources = "Copper bars.";
                    break;
                case 1: 
                    resources = "Tin bars.";
                    break;
                case 2:
                    resources = "Bronze bars.";
                    break;
                case 3: 
                    resources = "Iron bars.";
                    break;
                case 4:
                    resources = "Lead bars.";
                    break;
                case 5: 
                    resources = "Steel bars.";
                    break;
                case 6: 
                    resources = "Aluminium bars.";
                    break;
                case 7: 
                    resources = "Silver bars.";
                    break;
                case 8: 
                    resources = "Gold bars.";
                    break;
                case 9: 
                    resources = "Platinum bars.";
                    break;
                case 10: 
                    resources = "Cobalt bars.";
                    break;
                case 11: 
                    resources = "Lithium bars.";
                    break;
                default:
                    break;
            }
            int produced = produceResources();

            System.out.println("Furnace produced " + produced + " " + resources);
            player.updateSmeltedResource(output_enum, produced);
            System.out.println("Copper ore left: " + player.getRawResource(0));
        }
    }

    private int produceResources() {
        switch (this.output_enum) {
            case 0:  //copper bar
                if(this.player.getRawResource(0) >= 100) {
                    this.player.updateRawResource(0, -100);
                    return 1;  //add bonus resource logic here. 
                }
                else {
                    return 0;
                }
                
            case 1:  //tin bar
                if(this.player.getRawResource(1) >= 100) {
                    this.player.updateRawResource(1, -100);
                    return 1;  //add bonus resource logic here. 
                }
                else {
                    return 0;
                } 
            case 2:  //bronze bar
                if(this.player.getSmeltedResource(0) >= 5 &&
                   this.player.getSmeltedResource(1) >= 5) {
                    this.player.updateSmeltedResource(0, -5);
                    this.player.updateSmeltedResource(1, -5);
                    return 1;  //add bonus resource logic here. 
                }
                else {
                    return 0;
                } 
            case 3:  //iron bar
                if(this.player.getRawResource(3) >= 250) {
                    this.player.updateRawResource(3, -250);
                    return 1;  //add bonus resource logic here. 
                }
                else {
                    return 0;
                } 
            case 4:  //lead bar
                if(this.player.getRawResource(4) >= 250) {
                    this.player.updateRawResource(4, -250);
                    return 1;  //add bonus resource logic here. 
                }
                else {
                    return 0;
                } 
            case 5:  //steel bar
                if(this.player.getRawResource(3) >= 500 &&
                   this.player.getRawResource(2) >= 250) {
                    this.player.updateRawResource(3, -500);
                    this.player.updateRawResource(2, -250);
                    return 1;  //add bonus resource logic here. 
                }
                else {
                    return 0;
                } 
            case 6:  //aluminium bar
                if(this.player.getRawResource(5) >= 500) {
                    this.player.updateRawResource(5, -500);
                    return 1;  //add bonus resource logic here. 
                }
                else {
                    return 0;
                } 
            case 7:  //silver bar
                if(this.player.getRawResource(6) >= 500) {
                    this.player.updateRawResource(6, -500);
                    return 1;  //add bonus resource logic here. 
                }
                else {
                    return 0;
                } 
            case 8:  //gold bar
                if(this.player.getRawResource(8) >= 1000) {
                    this.player.updateRawResource(8, -1000);
                    return 1;  //add bonus resource logic here. 
                }
                else {
                    return 0;
                } 
            case 9:  //platinum bar
                if(this.player.getRawResource(9) >= 1500) {
                    this.player.updateRawResource(9, -1500);
                    return 1;  //add bonus resource logic here. 
                }
                else {
                    return 0;
                } 
            case 10:  //cobalt bar
                if(this.player.getRawResource(10) >= 2500) {
                    this.player.updateRawResource(10, -2500);
                    return 1;  //add bonus resource logic here. 
                }
                else {
                    return 0;
                } 
            case 11:  //lithium bar
                if(this.player.getRawResource(11) >= 1000) {
                    this.player.updateRawResource(11, -1000);
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
