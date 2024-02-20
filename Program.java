import Mines.CopperMine;
import Mines.TinMine;
import Player.Player;

public class Program {
    public static void main(String[] args) {
        //CopperMine copperMine = new CopperMine(10, 10, 100, 1200);
        //TinMine tinMine = new TinMine(5, 5, 50, 500);
        //copperMine.mine();
        //tinMine.mine();

        new GameView(new Player("Syrca"));
        
    } 
}