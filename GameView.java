
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;


import Player.Player;
import Mines.*;
import Facilities.*;

public class GameView {
    JFrame game_frame = new JFrame("IdleMiner");
    JPanel mine_panel = new JPanel();
    JLabel mine_panel_background = new JLabel();

    JLabel copper_mine_image = new JLabel();
    JButton copper_invest = new JButton();
    JButton copper_unlock = new JButton();
    JLabel copper_insufficient = new JLabel();
    JLabel copper_level = new JLabel();

    JPanel raw_resource_panel = new JPanel();
    JLabel raw_resource_panel_background = new JLabel();
    JLabel copper_amount = new JLabel();    
    JLabel tin_amount = new JLabel();
    JLabel coal_amount = new JLabel();
    JLabel iron_amount = new JLabel();
    JLabel lead_amount = new JLabel();
    JLabel aluminium_amount = new JLabel();
    JLabel silver_amount = new JLabel();
    JLabel sulphur_amount = new JLabel();    
    JLabel gold_amount = new JLabel();
    JLabel platinum_amount = new JLabel();
    JLabel cobalt_amount = new JLabel();
    JLabel diamond_amount = new JLabel();

    JPanel furnace_panel = new JPanel();
    JLabel furnace_panel_background = new JLabel();
    JLabel furnace1 = new JLabel();       
    JLabel furnace2 = new JLabel();
    JLabel furnace3 = new JLabel();
    JLabel furnace4 = new JLabel();
    JLabel furnace5 = new JLabel();
    JLabel furnace6 = new JLabel();
    JButton select_recipe_furnace1 = new JButton();
    JButton select_recipe_furnace2 = new JButton();
    JButton select_recipe_furnace3 = new JButton();
    JButton select_recipe_furnace4 = new JButton();
    JButton select_recipe_furnace5 = new JButton();
    JButton select_recipe_furnace6 = new JButton();
    
    
    Player player;
    CopperMine copper_mine;

    public GameView(Player player) {
        this.player = player;

        game_frame.setSize(1920, 1080);
        game_frame.setLayout(null);

        makeMinePanel();
        makeRawResourcePanel();
        makeFurnacePanel();

        //temporary for testing.
        this.player.updateMoney(1000000);
    



        game_frame.setVisible(true);
        game_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void makeMinePanel() {
        mine_panel.setLayout(null);
        mine_panel.setBounds(0, 10, 400, 1027);
        mine_panel_background.setIcon(new ImageIcon("Images/Mines/mine_panel_background.png"));
        mine_panel_background.setBounds(0, 0, 400, 1027);
        mine_panel.add(mine_panel_background);

        makeCopperMinePanel();
        //tin
        //coal
        //iron
        //lead
        //aluminium
        //silver
        //sulphur
        //gold
        //platinum
        //cobalt
        //diamond

        game_frame.add(mine_panel);
        game_frame.revalidate();
        game_frame.repaint();
    }

    private void makeCopperMinePanel() {
        copper_mine_image.setIcon(new ImageIcon("Images/Mines/copper_mine_locked.png"));
        copper_mine_image.setBounds(5, 5, 391, 82);
        mine_panel.add(copper_mine_image);

        copper_level.setBounds(120, 10, 50, 25);
        try {
            copper_level.setText("Lv. " + player.getCopperMine().getLevel());

        } catch (Exception e) {
            copper_level.setText("Lv. 1");
        }
        


        copper_insufficient.setIcon(new ImageIcon("Images/Mines/insufficient.png"));
        copper_insufficient.setBounds(272, 35, 113, 35);
        mine_panel.add(copper_insufficient);
        mine_panel.setComponentZOrder(copper_insufficient, 0);
        copper_insufficient.setVisible(false);
        
        copper_invest.setIcon(new ImageIcon("Images/Mines/invest.png"));
        copper_invest.setBounds(318, 9, 68, 26);
        copper_invest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invest(0);
            }
        });

        copper_unlock.setIcon(new ImageIcon("Images/Mines/unlock.png"));
        copper_unlock.setBounds(139, 52, 123, 27);
        copper_unlock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(unlock(0)) {
                    mine_panel.add(copper_invest);
                    mine_panel.add(copper_level);
                    copper_mine_image.setIcon(new ImageIcon("Images/Mines/copper_mine_lvl1.png"));
                    mine_panel.remove(copper_unlock);
                    player.purchaseMine(0);
                } 
                else {
                    copper_insufficient.setVisible(true);
                    mine_panel.revalidate();
                    mine_panel.repaint();

                    Timer timer = new Timer(2000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            copper_insufficient.setVisible(false);
                            mine_panel.revalidate();
                            mine_panel.repaint();
                            ((Timer) e.getSource()).stop();
                        }
                    });
                    timer.setRepeats(false); 
                    timer.start(); 
                }
            }
        });
        mine_panel.add(copper_unlock);
    }

    private void makeRawResourcePanel() {
        raw_resource_panel.setLayout(null);
        raw_resource_panel.setBounds(400, 10, 200, 1027);
        raw_resource_panel_background.setIcon(new ImageIcon("Images/RawResources/raw_resource_panel_background.png"));
        raw_resource_panel_background.setBounds(0, 0, 200, 1027);
        raw_resource_panel.add(raw_resource_panel_background);

        
        copper_amount.setBounds(54, 25, 131, 39);
        copper_amount.setFont(new Font(copper_amount.getFont().getName(), Font.PLAIN, 20));
        copper_amount.setText("0");
        copper_amount.setHorizontalAlignment(SwingConstants.CENTER);
        raw_resource_panel.add(copper_amount);
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 1; i++) { //don't forget to change 1 to 12 when everything is added!
                    updateAmount(i, player.getRawResource(i));
                }
            }
        });
        timer.start();



        game_frame.add(raw_resource_panel);
    }

    private void updateAmount(int mine_enum, int amount) {
        switch (mine_enum) {
            case 0:
            String amount_string = String.valueOf(amount);
                if(amount_string.length() > 6) {
                    double millions = amount / 1000000.0;
                    
                    String set_string = new DecimalFormat("#.##").format(millions) + " M";
                    copper_amount.setText(set_string);
                }
                else {
                    copper_amount.setText(amount_string);
                }
                break;
        
            default:
                break;
        }
    }

    private void invest(int mine_enum) {
        JFrame invest_frame = new JFrame();
        invest_frame.setSize(500, 250);
        invest_frame.setLayout(null);
        invest_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel invest_panel = new JPanel();
        invest_panel.setLayout(null);
        invest_panel.setBounds(0, 0, 500, 250);
        invest_panel.setFocusable(true);

        JLabel invest_mine = new JLabel();
        invest_mine.setBounds(189, 12, 123, 26);
        switch (mine_enum) {
            case 0:
                invest_mine.setIcon(new ImageIcon("Images/Mines/invest_copper_mine.png"));
                break;
            default:
                break;
        }
        invest_panel.add(invest_mine);

        JLabel invest_panel_background = new JLabel();
        invest_panel_background.setBounds(0, 0, 500, 250);
        invest_panel_background.setIcon(new ImageIcon("Images/Mines/invest_panel_background.png"));
        invest_panel.add(invest_panel_background);

        JLabel next_level = new JLabel();
        next_level.setBounds(188, 90, 125, 30);
        next_level.setHorizontalAlignment(SwingConstants.CENTER);
        String next_level_string = ("Invested: " + this.player.getCopperMine().getInvestedMoney() + " / " + this.player.getCopperMine().getNextLevel());
        next_level.setText(next_level_string);
        invest_panel.add(next_level);


        JTextField invest_amount = new JTextField();
        invest_amount.setBounds(125, 125, 251, 45);
        invest_amount.setHorizontalAlignment(SwingConstants.CENTER);
        invest_panel.add(invest_amount);

        JButton confirm = new JButton();
        confirm.setBounds(188, 176, 125, 30);
        confirm.setIcon(new ImageIcon("Images/Mines/confirm.png"));
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amount;
                try {
                    amount = Integer.parseInt(invest_amount.getText());
                    if (amount <= player.getMoney()) {
                        player.updateMoney(-amount);
                        player.getCopperMine().investMoney(amount);
                        copper_level.setText("Lv. " + player.getCopperMine().getLevel());
                        invest_frame.dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(invest_frame, "Not enough money", "Insufficient Money", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(invest_frame, "You must enter an integer", "Integer Error", JOptionPane.ERROR_MESSAGE);
                }   
            }
        });
        invest_panel.add(confirm);
        invest_frame.add(invest_panel);
        invest_frame.setVisible(true);

    }

    private boolean unlock(int mine_enum) {
        switch (mine_enum) {
            case 0:
                return (player.getMoney() >= 1000);
            default:
                return false;
        }
    }

    private void makeFurnacePanel() {
        furnace_panel.setLayout(null);
        furnace_panel.setBounds(600, 10, 400, 1027);
        furnace_panel_background.setIcon(new ImageIcon("Images/Furnaces/furnace_panel_background.png"));
        furnace_panel_background.setBounds(0, 0, 400, 1027);
        furnace_panel.add(furnace_panel_background);

        furnace1.setIcon(new ImageIcon("Images/Furnaces/furnace.png"));
        furnace2.setIcon(new ImageIcon("Images/Furnaces/furnace.png"));
        furnace3.setIcon(new ImageIcon("Images/Furnaces/furnace.png"));
        furnace4.setIcon(new ImageIcon("Images/Furnaces/furnace.png"));
        furnace5.setIcon(new ImageIcon("Images/Furnaces/furnace.png"));
        furnace6.setIcon(new ImageIcon("Images/Furnaces/furnace.png"));

        furnace1.setBounds(10, 6, 390, 167);
        furnace2.setBounds(10, 176, 390, 167);
        furnace3.setBounds(10, 346, 390, 167);
        furnace4.setBounds(10, 516, 390, 167);
        furnace5.setBounds(10, 686, 390, 167);
        furnace6.setBounds(10, 856, 390, 167);

        furnace_panel.add(furnace1);
        furnace_panel.add(furnace2);
        furnace_panel.add(furnace3);
        furnace_panel.add(furnace4);
        furnace_panel.add(furnace5);
        furnace_panel.add(furnace6);

        select_recipe_furnace1.setIcon(new ImageIcon("Images/Furnaces/select_recipe.png"));
        select_recipe_furnace2.setIcon(new ImageIcon("Images/Furnaces/select_recipe.png"));
        select_recipe_furnace3.setIcon(new ImageIcon("Images/Furnaces/select_recipe.png"));
        select_recipe_furnace4.setIcon(new ImageIcon("Images/Furnaces/select_recipe.png"));
        select_recipe_furnace5.setIcon(new ImageIcon("Images/Furnaces/select_recipe.png"));
        select_recipe_furnace6.setIcon(new ImageIcon("Images/Furnaces/select_recipe.png"));

        select_recipe_furnace1.setBounds(15, 13, 135, 26);
        select_recipe_furnace2.setBounds(15, 182, 135, 26);
        select_recipe_furnace3.setBounds(15, 353, 135, 26);
        select_recipe_furnace4.setBounds(15, 522, 135, 26);
        select_recipe_furnace5.setBounds(15, 693, 135, 26);
        select_recipe_furnace6.setBounds(15, 862, 135, 26);

        select_recipe_furnace1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Furnace furnace = player.getFurnace1();
            }
        });

        furnace_panel.add(select_recipe_furnace1);
        furnace_panel.add(select_recipe_furnace2);
        furnace_panel.add(select_recipe_furnace3);
        furnace_panel.add(select_recipe_furnace4);
        furnace_panel.add(select_recipe_furnace5);
        furnace_panel.add(select_recipe_furnace6);



        game_frame.add(furnace_panel);
    }
}
