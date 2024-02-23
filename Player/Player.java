package Player;

import Mines.*;
import Facilities.*;

public class Player {
    private String name;
    private int money;
    
    private CopperMine copper_mine;
    private TinMine tin_mine;
    private CoalMine coal_mine;
    private IronMine iron_mine;
    private LeadMine lead_mine;
    private AluminiumMine aluminium_mine;
    private SilverMine silver_mine;
    private SulphurMine sulphur_mine;
    private GoldMine gold_mine;
    private PlatinumMine platinum_mine;
    private CobaltMine cobalt_mine;
    private LithiumMine lithium_mine;

    private int copper;
    private int tin;
    private int coal;
    private int iron;
    private int lead;
    private int aluminium;
    private int silver;
    private int sulphur;
    private int gold;
    private int platinum;
    private int cobalt; 
    private int lithium;

    private Furnace furnace1;
    private Furnace furnace2;
    private Furnace furnace3;
    private Furnace furnace4;
    private Furnace furnace5;
    private Furnace furnace6;

    private int copper_bar;
    private int tin_bar;
    private int bronze_bar;
    private int iron_bar;
    private int lead_bar;
    private int steel_bar;
    private int aluminium_bar;
    private int silver_bar;
    private int gold_bar;
    private int platinum_bar;
    private int cobalt_bar;
    private int lithium_bar;


    public Player(String name) {
        this.name = name;
        this.money = 1000 + 2500;

        this.copper = 0;
        this.tin = 0;
        this.coal = 0;
        this.iron = 0;
        this.lead = 0;
        this.aluminium = 0;
        this.silver = 0;
        this.sulphur = 0;
        this.gold = 0;
        this.platinum = 0;
        this.cobalt = 0;
        this.lithium = 0;    
    }

    public Player(String name, int money,
                  int copper, int tin, int coal, int iron, 
                  int lead, int aluminium, int silver, int sulphur, 
                  int gold, int platinum, int cobalt, int lithium,
                  int copper_bar, int tin_bar, int bronze_bar) {

        this.name = name;
        this.money = money;

        this.copper = copper;
        this.tin = tin;
        this.coal = coal;
        this.iron = iron;
        this.lead = lead;
        this.aluminium = aluminium;
        this.silver = silver;
        this.sulphur = sulphur;
        this.gold = gold;
        this.platinum = platinum;
        this.cobalt = cobalt;
        this.lithium = lithium;  
        
        this.copper_bar = copper_bar;
        this.tin_bar = tin_bar;
        this.bronze_bar = bronze_bar;
    }

    public void purchaseMine(int mine_enum) {
        switch (mine_enum) {
            case 0:
                System.out.println("Purchased Copper mine");
                this.copper_mine = new CopperMine(this, 10, 10, 100, 1000);
                updateMoney(-1000);
                this.copper_mine.mine();
                break;
        
            default:
                break;
        }
    }

    public void purchaseFurnace(int furnace_enum) {
        switch (furnace_enum) {
            case 0:
                System.out.println("Purchased Furnace");
                this.furnace1 = new Furnace(this);
                updateMoney(-10000);
                break;
            case 1:
                System.out.println("Purchased Furnace");
                this.furnace2 = new Furnace(this);
                updateMoney(-50000);
                break;
            case 2:
                System.out.println("Purchased Furnace");
                this.furnace3 = new Furnace(this);
                updateMoney(-250000);
                break;
            case 3:
                System.out.println("Purchased Furnace");
                this.furnace4 = new Furnace(this);
                updateMoney(-1000000);
                break;
            case 4:
                System.out.println("Purchased Furnace");
                this.furnace5 = new Furnace(this);
                updateMoney(-25000000);
                break;
            case 5:
                System.out.println("Purchased Furnace");
                this.furnace6 = new Furnace(this);
                updateMoney(-500000000);
                break;
            default:
                break;
        }
    }

    public int getMoney() {
        return money;
    }
    public void updateMoney(int change) {
        System.out.println("Money before: " + this.money);
        this.money += change;
        System.out.println("Money after: " + this.money);
    }
    
    public int getRawResource(int mine_enum) {
        switch (mine_enum) {
            case 0:
                return this.copper;
            case 1:
                return this.tin;
            case 2:
                return this.coal;
            case 3:
                return this.iron;
            case 4:
                return this.lead;
            case 5:
                return this.aluminium;
            case 6:
                return this.silver;
            case 7:
                return this.sulphur;
            case 8: 
                return this.gold;
            case 9:
                return this.platinum;
            case 10:
                return this.cobalt;
            case 11:
                return this.lithium;
            default:
                return 0;
        }
    }
    public void updateRawResource(int mine_enum, int change) {
        switch (mine_enum) {
            case 0:
                this.copper += change;
                break;
            case 1:
                this.tin += change;
                break;
            case 2:
                this.coal += change;
                break;
            case 3:
                this.iron += change;
                break;
            case 4:
                this.lead += change;
                break;
            case 5:
                this.aluminium += change;
                break;
            case 6:
                this.silver += change;
                break;
            case 7:
                this.sulphur += change;
                break;
            case 8: 
                this.gold += change;
                break;
            case 9:
                this.platinum += change;
                break;
            case 10:
                this.cobalt += change;
                break;
            case 11:
                this.lithium += change;
                break;
            default:
                break;
        }
    }
    
    public int getSmeltedResource(int smelt_enum) {
        switch (smelt_enum) {
            case 0:
                return this.copper_bar;
            case 1:
                return this.tin_bar;
            case 2:
                return this.bronze_bar;
            default:
                return 0;
                
        }
    }
    public void updateSmeltedResource(int smelt_enum, int change) {
        switch (smelt_enum) {
            case 0:
                this.copper_bar += change;
                break;
            case 1: 
                this.tin_bar += change;
                break;
            case 2:
                this.bronze_bar += change;
                break;
            default:
                break;
        }
    }


    //getters for mines

    public CopperMine getCopperMine() {
        return this.copper_mine;
    }
    public TinMine getTin_mine() {
        return tin_mine;
    }
    public CoalMine getCoal_mine() {
        return coal_mine;
    }
    public IronMine getIron_mine() {
        return iron_mine;
    }
    public LeadMine getLead_mine() {
        return lead_mine;
    }
    public AluminiumMine getAluminium_mine() {
        return aluminium_mine;
    }
    public SilverMine getSilver_mine() {
        return silver_mine;
    }
    public SulphurMine getSulphur_mine() {
        return sulphur_mine;
    }
    public GoldMine getGold_mine() {
        return gold_mine;
    }
    public PlatinumMine getPlatinum_mine() {
        return platinum_mine;
    }
    public CobaltMine getCobalt_mine() {
        return cobalt_mine;
    }
    public LithiumMine getlithium_mine() {
        return lithium_mine;
    }

    // getters for furnaces
    public Furnace getFurnace1() {
        return furnace1;
    }
    public Furnace getFurnace2() {
        return furnace2;
    }
    public Furnace getFurnace3() {
        return furnace3;
    }
    public Furnace getFurnace4() {
        return furnace4;
    }
    public Furnace getFurnace5() {
        return furnace5;
    }
    public Furnace getFurnace6() {
        return furnace6;
    }



}
