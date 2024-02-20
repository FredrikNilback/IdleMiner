package Mines;
import java.util.Timer;
import java.util.TimerTask;

public class TinMine {
    private Timer timer;
    private int cycle;
    private int resource_amount;
    private int level_up_resources;
    private int level;
    private int invested_money;
    private int start_off_investment;
    private int level_scaling;

    public TinMine(int cycle, int resource_amount, int level_scaling, int start_off_investment) {
        this.cycle = cycle;
        this.resource_amount = resource_amount;
        this.level_scaling = level_scaling;
        this.start_off_investment = start_off_investment;
        this.level_up_resources = resource_amount;

        this.level = 1;
        this.invested_money = 0;
    }

    public TinMine(int cycle, int resource_amount, int level_scaling, int start_off_investment, int level, int invested_money) {
        this.cycle = cycle;
        this.resource_amount = resource_amount;
        this.level_scaling = level_scaling;
        this.start_off_investment = start_off_investment;
        this.level_up_resources = resource_amount;

        this.level = level;
        this.invested_money = invested_money;
    }

    public void mine() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TinMiningTask(), 0, (this.cycle * 1000));
    }

    private class TinMiningTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("Tin mine produced " + resource_amount + " resources.");
        }
    }

    public void investMoney(int amount) {
        this.invested_money += amount;
        checkLevelUp();

    }

    private void levelUp() {
        this.resource_amount += this.level_up_resources;
        this.level += 1;
    }

    private void checkLevelUp() {
        boolean check;
        if(this.level == 1) {
            check = (this.start_off_investment * this.level) >= this.invested_money;
        }
        else {
            check = (this.start_off_investment * this.level) + (this.level * this.level_scaling) >= this.invested_money;
        }
        if(check) {
            levelUp();
        }
    }
}