package Mines;

import java.util.Timer;
import java.util.TimerTask;
import Player.Player;

public class CoalMine {
    private Timer timer;

    private Player player;
    private int cycle;
    private int resource_amount;
    private int level_up_resources;
    private int level;
    private int invested_money;
    private int start_off_investment;
    private int level_scaling;
    private int speed;
    

    public CoalMine(Player player, int cycle, int resource_amount, int level_scaling, int start_off_investment) {
        this.player = player;
        this.cycle = cycle;
        this.resource_amount = resource_amount;
        this.level_scaling = level_scaling;
        this.start_off_investment = start_off_investment;
        this.level_up_resources = resource_amount;
        
        this.level = 1;
        this.invested_money = 0;
        this.speed = 100;
    }

    public CoalMine(Player player, int cycle, int resource_amount, int level_scaling, int start_off_investment, int level, int invested_money, int speed) {
        this.player = player;
        this.cycle = cycle;
        this.resource_amount = resource_amount;
        this.level_scaling = level_scaling;
        this.start_off_investment = start_off_investment;
        this.level_up_resources = resource_amount;

        this.level = level;
        this.invested_money = invested_money;
        this.speed = speed;
    }

    public void mine() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new CoalMiningTask(), 0, (this.cycle * 100000 / speed));
    }

    private class CoalMiningTask extends TimerTask {
        @Override
        public void run() {
            int produced = produceResources();
            System.out.println("Coal mine produced " + produced + " resources.");
            player.updateRawResource(2, produced);
        }
    }

    private int produceResources() {
        int produced_resources = this.resource_amount; // add bonus resource logic here

        return produced_resources;
    }

    public void investMoney(int amount) {
        this.invested_money += amount;
        System.out.println(amount + " invested." + "\n" + "Total: " + this.invested_money);
        checkLevelUp();

    }

    private void levelUp(int new_invested_money) {
        this.resource_amount += this.level_up_resources;
        this.level += 1;
        this.invested_money = new_invested_money;
    }

    private void checkLevelUp() {
        int level_up_condition = (int)(this.start_off_investment * Math.pow(2, this.level - 1) + level_scaling * (this.level));
        if(level_up_condition <= this.invested_money) {
            levelUp(this.invested_money - level_up_condition);
            System.out.println("Coal mine level up! New level: " + this.level + "\nCurrent gold invested: " + this.invested_money);
            checkLevelUp();
        }  
    }

    public void augmentCycle(int percentage) {
        this.speed += percentage;
    }

    // getters.
    public int getNextLevel() {
        return (int)(this.start_off_investment * Math.pow(2, this.level - 1) + level_scaling * (this.level));
    }

    public int getInvestedMoney() {
        return this.invested_money;
    }

    public int getLevel() {
        return this.level;
    }
}
