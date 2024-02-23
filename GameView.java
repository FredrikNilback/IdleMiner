
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
    JLabel lithium_amount = new JLabel();

    JPanel furnace_panel = new JPanel();
    JLabel furnace_panel_background = new JLabel();
    JLabel furnace1 = new JLabel();       
    JLabel furnace2 = new JLabel();
    JLabel furnace3 = new JLabel();
    JLabel furnace4 = new JLabel();
    JLabel furnace5 = new JLabel();
    JLabel furnace6 = new JLabel();
    JLabel furnace1_output = new JLabel();
    JLabel furnace2_output = new JLabel();
    JLabel furnace3_output = new JLabel();
    JLabel furnace4_output = new JLabel();
    JLabel furnace5_output = new JLabel();
    JLabel furnace6_output = new JLabel();
    JLabel furnace1_insufficient = new JLabel();
    JLabel furnace2_insufficient = new JLabel();
    JLabel furnace3_insufficient = new JLabel();
    JLabel furnace4_insufficient = new JLabel();
    JLabel furnace5_insufficient = new JLabel();
    JLabel furnace6_insufficient = new JLabel();
    JButton unlock_furnace1 = new JButton();
    JButton unlock_furnace2 = new JButton();
    JButton unlock_furnace3 = new JButton();
    JButton unlock_furnace4 = new JButton();
    JButton unlock_furnace5 = new JButton();
    JButton unlock_furnace6 = new JButton();
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
        this.player.updateRawResource(0, 300);
        System.out.println("Starting copper ore: " + this.player.getRawResource(0));
        
    



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
        //lithium

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
                invest_mine(0);
            }
        });

        copper_unlock.setIcon(new ImageIcon("Images/Mines/unlock.png"));
        copper_unlock.setBounds(139, 52, 123, 27);
        copper_unlock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(unlockMine(0)) {
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

    private void invest_mine(int mine_enum) {
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

    private boolean unlockMine(int mine_enum) {
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

        furnace1_insufficient.setIcon(new ImageIcon("Images/Mines/insufficient.png"));
        furnace1_insufficient.setBounds(272, 35, 113, 35);
        furnace_panel.add(furnace1_insufficient);
        furnace_panel.setComponentZOrder(furnace1_insufficient, 0);
        furnace1_insufficient.setVisible(false);
        
        furnace2_insufficient.setIcon(new ImageIcon("Images/Mines/insufficient.png"));
        furnace2_insufficient.setBounds(272, 205, 113, 35);
        furnace_panel.add(furnace2_insufficient);
        furnace_panel.setComponentZOrder(furnace2_insufficient, 0);
        furnace2_insufficient.setVisible(false);

        furnace3_insufficient.setIcon(new ImageIcon("Images/Mines/insufficient.png"));
        furnace3_insufficient.setBounds(272, 375, 113, 35);
        furnace_panel.add(furnace3_insufficient);
        furnace_panel.setComponentZOrder(furnace3_insufficient, 0);
        furnace3_insufficient.setVisible(false);

        furnace4_insufficient.setIcon(new ImageIcon("Images/Mines/insufficient.png"));
        furnace4_insufficient.setBounds(272, 545, 113, 35);
        furnace_panel.add(furnace4_insufficient);
        furnace_panel.setComponentZOrder(furnace4_insufficient, 0);
        furnace4_insufficient.setVisible(false);

        furnace5_insufficient.setIcon(new ImageIcon("Images/Mines/insufficient.png"));
        furnace5_insufficient.setBounds(272, 715, 113, 35);
        furnace_panel.add(furnace5_insufficient);
        furnace_panel.setComponentZOrder(furnace5_insufficient, 0);
        furnace5_insufficient.setVisible(false);

        furnace6_insufficient.setIcon(new ImageIcon("Images/Mines/insufficient.png"));
        furnace6_insufficient.setBounds(272, 885, 113, 35);
        furnace_panel.add(furnace6_insufficient);
        furnace_panel.setComponentZOrder(furnace6_insufficient, 0);
        furnace6_insufficient.setVisible(false);

        furnace1_output.setBounds(318, 108, 79, 44);
        furnace2_output.setBounds(318, 278, 79, 44);
        furnace3_output.setBounds(318, 448, 79, 44);
        furnace4_output.setBounds(318, 618, 79, 44);
        furnace5_output.setBounds(318, 788, 79, 44);
        furnace6_output.setBounds(318, 958, 79, 44);

        furnace1.setIcon(new ImageIcon("Images/Furnaces/furnace_locked.png"));
        furnace2.setIcon(new ImageIcon("Images/Furnaces/furnace_locked.png"));
        furnace3.setIcon(new ImageIcon("Images/Furnaces/furnace_locked.png"));
        furnace4.setIcon(new ImageIcon("Images/Furnaces/furnace_locked.png"));
        furnace5.setIcon(new ImageIcon("Images/Furnaces/furnace_locked.png"));
        furnace6.setIcon(new ImageIcon("Images/Furnaces/furnace_locked.png"));

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
                selectRecipe(furnace, 0);
                
            }
        });
        select_recipe_furnace2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Furnace furnace = player.getFurnace2();
                selectRecipe(furnace, 1);
                
            }
        });
        select_recipe_furnace3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Furnace furnace = player.getFurnace3();
                selectRecipe(furnace, 2);
                
            }
        });
        select_recipe_furnace4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Furnace furnace = player.getFurnace4();
                selectRecipe(furnace, 3);
                
            }
        });
        select_recipe_furnace5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Furnace furnace = player.getFurnace5();
                selectRecipe(furnace, 4);
                
            }
        });
        select_recipe_furnace6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Furnace furnace = player.getFurnace6();
                selectRecipe(furnace, 5);
                
            }
        });

        unlock_furnace1.setIcon(new ImageIcon("Images/Mines/unlock.png"));
        unlock_furnace2.setIcon(new ImageIcon("Images/Mines/unlock.png"));
        unlock_furnace3.setIcon(new ImageIcon("Images/Mines/unlock.png"));
        unlock_furnace4.setIcon(new ImageIcon("Images/Mines/unlock.png"));
        unlock_furnace5.setIcon(new ImageIcon("Images/Mines/unlock.png"));
        unlock_furnace6.setIcon(new ImageIcon("Images/Mines/unlock.png"));

        unlock_furnace1.setBounds(15, 13, 123, 26);
        unlock_furnace2.setBounds(15, 182, 123, 26);
        unlock_furnace3.setBounds(15, 353, 123, 26);
        unlock_furnace4.setBounds(15, 522, 123, 26);
        unlock_furnace5.setBounds(15, 693, 123, 26);
        unlock_furnace6.setBounds(15, 862, 123, 26);

        unlock_furnace1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(unlockFurnace(0)) {
                    //furnace_panel.add(furnace_invest);
                    //furnace_panel.add(furnace_level);
                    furnace_panel.add(furnace1_output);
                    furnace_panel.add(select_recipe_furnace1);
                    furnace1.setIcon(new ImageIcon("Images/Furnaces/furnace.png"));
                    furnace_panel.remove(unlock_furnace1);
                    player.purchaseFurnace(0);
                } 
                else {
                    furnace1_insufficient.setVisible(true);
                    furnace_panel.revalidate();
                    furnace_panel.repaint();

                    Timer timer = new Timer(2000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            furnace1_insufficient.setVisible(false);
                            furnace_panel.revalidate();
                            furnace_panel.repaint();
                            ((Timer) e.getSource()).stop();
                        }
                    });
                    timer.setRepeats(false); 
                    timer.start(); 
                }
            }
        });
        unlock_furnace2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(unlockFurnace(1)) {
                    //furnace_panel.add(furnace_invest);
                    //furnace_panel.add(furnace_level);
                    furnace_panel.add(furnace2_output);
                    furnace_panel.add(select_recipe_furnace2);
                    furnace2.setIcon(new ImageIcon("Images/Furnaces/furnace.png"));
                    furnace_panel.remove(unlock_furnace2);
                    player.purchaseFurnace(1);
                } 
                else {
                    furnace2_insufficient.setVisible(true);
                    furnace_panel.revalidate();
                    furnace_panel.repaint();

                    Timer timer = new Timer(2000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            furnace2_insufficient.setVisible(false);
                            furnace_panel.revalidate();
                            furnace_panel.repaint();
                            ((Timer) e.getSource()).stop();
                        }
                    });
                    timer.setRepeats(false); 
                    timer.start(); 
                }
            }
        });
        unlock_furnace3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(unlockFurnace(2)) {
                    //furnace_panel.add(furnace_invest);
                    //furnace_panel.add(furnace_level);
                    furnace_panel.add(furnace3_output);
                    furnace_panel.add(select_recipe_furnace3);
                    furnace3.setIcon(new ImageIcon("Images/Furnaces/furnace.png"));
                    furnace_panel.remove(unlock_furnace3);
                    player.purchaseFurnace(2);
                } 
                else {
                    furnace3_insufficient.setVisible(true);
                    furnace_panel.revalidate();
                    furnace_panel.repaint();

                    Timer timer = new Timer(2000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            furnace3_insufficient.setVisible(false);
                            furnace_panel.revalidate();
                            furnace_panel.repaint();
                            ((Timer) e.getSource()).stop();
                        }
                    });
                    timer.setRepeats(false); 
                    timer.start(); 
                }
            }
        });
        unlock_furnace4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(unlockFurnace(3)) {
                    //furnace_panel.add(furnace_invest);
                    //furnace_panel.add(furnace_level);
                    furnace_panel.add(furnace4_output);
                    furnace_panel.add(select_recipe_furnace4);
                    furnace4.setIcon(new ImageIcon("Images/Furnaces/furnace.png"));
                    furnace_panel.remove(unlock_furnace4);
                    player.purchaseFurnace(3);
                } 
                else {
                    furnace4_insufficient.setVisible(true);
                    furnace_panel.revalidate();
                    furnace_panel.repaint();

                    Timer timer = new Timer(2000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            furnace4_insufficient.setVisible(false);
                            furnace_panel.revalidate();
                            furnace_panel.repaint();
                            ((Timer) e.getSource()).stop();
                        }
                    });
                    timer.setRepeats(false); 
                    timer.start(); 
                }
            }
        });
        unlock_furnace5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(unlockFurnace(4)) {
                    //furnace_panel.add(furnace_invest);
                    //furnace_panel.add(furnace_level);
                    furnace_panel.add(furnace5_output);
                    furnace_panel.add(select_recipe_furnace5);
                    furnace5.setIcon(new ImageIcon("Images/Furnaces/furnace.png"));
                    furnace_panel.remove(unlock_furnace5);
                    player.purchaseFurnace(4);
                } 
                else {
                    furnace5_insufficient.setVisible(true);
                    furnace_panel.revalidate();
                    furnace_panel.repaint();

                    Timer timer = new Timer(2000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            furnace5_insufficient.setVisible(false);
                            furnace_panel.revalidate();
                            furnace_panel.repaint();
                            ((Timer) e.getSource()).stop();
                        }
                    });
                    timer.setRepeats(false); 
                    timer.start(); 
                }
            }
        });
        unlock_furnace6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(unlockFurnace(5)) {
                    //furnace_panel.add(furnace_invest);
                    //furnace_panel.add(furnace_level);
                    furnace_panel.add(furnace6_output);
                    furnace_panel.add(select_recipe_furnace6);
                    furnace6.setIcon(new ImageIcon("Images/Furnaces/furnace.png"));
                    furnace_panel.remove(unlock_furnace6);

                    player.purchaseFurnace(5);
                } 
                else {
                    furnace6_insufficient.setVisible(true);
                    furnace_panel.revalidate();
                    furnace_panel.repaint();

                    Timer timer = new Timer(2000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            furnace6_insufficient.setVisible(false);
                            furnace_panel.revalidate();
                            furnace_panel.repaint();
                            ((Timer) e.getSource()).stop();
                        }
                    });
                    timer.setRepeats(false); 
                    timer.start(); 
                }
            }
        });

        furnace_panel.add(unlock_furnace1);
        furnace_panel.add(unlock_furnace2);
        furnace_panel.add(unlock_furnace3);
        furnace_panel.add(unlock_furnace4);
        furnace_panel.add(unlock_furnace5);
        furnace_panel.add(unlock_furnace6);



        game_frame.add(furnace_panel);
    }

    private void selectRecipe(Furnace furnace, int furnace_enum) {
        JFrame jframe = new JFrame();
        JPanel jpanel = new JPanel();
        JLabel background = new JLabel();

        JButton copper_bar_select = new JButton();
        JButton tin_bar_select = new JButton();
        JButton bronze_bar_select = new JButton();
        JButton iron_bar_select = new JButton();
        JButton lead_bar_select = new JButton();
        JButton steel_bar_select = new JButton();
        
        JLabel copper_bar_amount = new JLabel();
        JLabel tin_bar_amount = new JLabel();
        JLabel bronze_bar_amount = new JLabel();
        JLabel iron_bar_amount = new JLabel();
        JLabel lead_bar_amount = new JLabel();
        JLabel steel_bar_amount = new JLabel();

        jframe.setSize(500, 1052);
        jframe.setLayout(null);
        jpanel.setBounds(0, 0, 500, 1015);
        jpanel.setLayout(null);
        background.setBounds(0, 0, 500, 1015);
        background.setIcon(new ImageIcon("Images/Furnaces/select_recipe_background.png"));
        jpanel.add(background);


        copper_bar_select.setBounds(57, 25, 68, 26);
        copper_bar_select.setIcon(new ImageIcon("Images/Furnaces/select.png"));
        copper_bar_select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Copper bar recipe selected!");
                furnace.decideTask(0);
                switch (furnace_enum) {
                    case 0:
                        furnace1_output.setIcon(new ImageIcon("Images/Furnaces/copper_bar_out.png"));
                        furnace_panel.repaint();
                        furnace_panel.revalidate();  
                        break;
                    case 1:
                        furnace2_output.setIcon(new ImageIcon("Images/Furnaces/copper_bar_out.png"));  
                        break;
                    case 2:
                        furnace3_output.setIcon(new ImageIcon("Images/Furnaces/copper_bar_out.png"));  
                        break;
                    case 3:
                        furnace4_output.setIcon(new ImageIcon("Images/Furnaces/copper_bar_out.png"));  
                        break;
                    case 4:
                        furnace5_output.setIcon(new ImageIcon("Images/Furnaces/copper_bar_out.png"));  
                        break;
                    case 5:
                        furnace6_output.setIcon(new ImageIcon("Images/Furnaces/copper_bar_out.png"));  
                        break;
                    default:
                        break;
                }
                jframe.dispose();
            }
        });
        jpanel.add(copper_bar_select);

        tin_bar_select.setBounds(57, 110, 68, 26);
        tin_bar_select.setIcon(new ImageIcon("Images/Furnaces/select.png"));
        tin_bar_select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Tin bar recipe selected!");
                furnace.decideTask(1);
                switch (furnace_enum) {
                    case 0:
                        furnace1_output.setIcon(new ImageIcon("Images/Furnaces/tin_bar_out.png"));
                        furnace_panel.repaint();
                        furnace_panel.revalidate();  
                        break;
                    case 1:
                        furnace2_output.setIcon(new ImageIcon("Images/Furnaces/tin_bar_out.png"));  
                        break;
                    case 2:
                        furnace3_output.setIcon(new ImageIcon("Images/Furnaces/tin_bar_out.png"));  
                        break;
                    case 3:
                        furnace4_output.setIcon(new ImageIcon("Images/Furnaces/tin_bar_out.png"));  
                        break;
                    case 4:
                        furnace5_output.setIcon(new ImageIcon("Images/Furnaces/tin_bar_out.png"));  
                        break;
                    case 5:
                        furnace6_output.setIcon(new ImageIcon("Images/Furnaces/tin_bar_out.png"));  
                        break;
                    default:
                        break;
                }
                jframe.dispose();
            }
        });
        jpanel.add(tin_bar_select);

        bronze_bar_select.setBounds(57, 195, 68, 26);
        bronze_bar_select.setIcon(new ImageIcon("Images/Furnaces/select.png"));
        bronze_bar_select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bronze bar recipe selected!");
                furnace.decideTask(2);
                switch (furnace_enum) {
                    case 0:
                        furnace1_output.setIcon(new ImageIcon("Images/Furnaces/bronze_bar_out.png"));
                        furnace_panel.repaint();
                        furnace_panel.revalidate();  
                        break;
                    case 1:
                        furnace2_output.setIcon(new ImageIcon("Images/Furnaces/bronze_bar_out.png"));  
                        break;
                    case 2:
                        furnace3_output.setIcon(new ImageIcon("Images/Furnaces/bronze_bar_out.png"));  
                        break;
                    case 3:
                        furnace4_output.setIcon(new ImageIcon("Images/Furnaces/bronze_bar_out.png"));  
                        break;
                    case 4:
                        furnace5_output.setIcon(new ImageIcon("Images/Furnaces/bronze_bar_out.png"));  
                        break;
                    case 5:
                        furnace6_output.setIcon(new ImageIcon("Images/Furnaces/bronze_bar_out.png"));  
                        break;
                    default:
                        break;
                }
                jframe.dispose();
            }
        });
        jpanel.add(bronze_bar_select);

        iron_bar_select.setBounds(57, 280, 68, 26);
        iron_bar_select.setIcon(new ImageIcon("Images/Furnaces/select.png"));
        iron_bar_select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Iron bar recipe selected!");
                furnace.decideTask(3);
                switch (furnace_enum) {
                    case 0:
                        furnace1_output.setIcon(new ImageIcon("Images/Furnaces/iron_bar_out.png"));
                        furnace_panel.repaint();
                        furnace_panel.revalidate();  
                        break;
                    case 1:
                        furnace2_output.setIcon(new ImageIcon("Images/Furnaces/iron_bar_out.png"));  
                        break;
                    case 2:
                        furnace3_output.setIcon(new ImageIcon("Images/Furnaces/iron_bar_out.png"));  
                        break;
                    case 3:
                        furnace4_output.setIcon(new ImageIcon("Images/Furnaces/iron_bar_out.png"));  
                        break;
                    case 4:
                        furnace5_output.setIcon(new ImageIcon("Images/Furnaces/iron_bar_out.png"));  
                        break;
                    case 5:
                        furnace6_output.setIcon(new ImageIcon("Images/Furnaces/iron_bar_out.png"));  
                        break;
                    default:
                        break;
                }
                jframe.dispose();
            }
        });
        jpanel.add(iron_bar_select);

        lead_bar_select.setBounds(57, 365, 68, 26);
        lead_bar_select.setIcon(new ImageIcon("Images/Furnaces/select.png"));
        lead_bar_select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("lead bar recipe selected!");
                furnace.decideTask(4);
                switch (furnace_enum) {
                    case 0:
                        furnace1_output.setIcon(new ImageIcon("Images/Furnaces/lead_bar_out.png"));
                        furnace_panel.repaint();
                        furnace_panel.revalidate();  
                        break;
                    case 1:
                        furnace2_output.setIcon(new ImageIcon("Images/Furnaces/lead_bar_out.png"));  
                        break;
                    case 2:
                        furnace3_output.setIcon(new ImageIcon("Images/Furnaces/lead_bar_out.png"));  
                        break;
                    case 3:
                        furnace4_output.setIcon(new ImageIcon("Images/Furnaces/lead_bar_out.png"));  
                        break;
                    case 4:
                        furnace5_output.setIcon(new ImageIcon("Images/Furnaces/lead_bar_out.png"));  
                        break;
                    case 5:
                        furnace6_output.setIcon(new ImageIcon("Images/Furnaces/lead_bar_out.png"));  
                        break;
                    default:
                        break;
                }
                jframe.dispose();
            }
        });
        jpanel.add(lead_bar_select);

        steel_bar_select.setBounds(57, 365, 68, 26);
        steel_bar_select.setIcon(new ImageIcon("Images/Furnaces/select.png"));
        steel_bar_select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("steel bar recipe selected!");
                furnace.decideTask(5);
                switch (furnace_enum) {
                    case 0:
                        furnace1_output.setIcon(new ImageIcon("Images/Furnaces/steel_bar_out.png"));
                        furnace_panel.repaint();
                        furnace_panel.revalidate();  
                        break;
                    case 1:
                        furnace2_output.setIcon(new ImageIcon("Images/Furnaces/steel_bar_out.png"));  
                        break;
                    case 2:
                        furnace3_output.setIcon(new ImageIcon("Images/Furnaces/steel_bar_out.png"));  
                        break;
                    case 3:
                        furnace4_output.setIcon(new ImageIcon("Images/Furnaces/steel_bar_out.png"));  
                        break;
                    case 4:
                        furnace5_output.setIcon(new ImageIcon("Images/Furnaces/steel_bar_out.png"));  
                        break;
                    case 5:
                        furnace6_output.setIcon(new ImageIcon("Images/Furnaces/steel_bar_out.png"));  
                        break;
                    default:
                        break;
                }
                jframe.dispose();
            }
        });
        jpanel.add(steel_bar_select);



        jframe.add(jpanel);
        jframe.setVisible(true);  
        jframe.setResizable(false);      
        jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private boolean unlockFurnace(int furnace_enum) {
        switch (furnace_enum) {
            case 0:
                return (player.getMoney() >= 10000);
            case 1:
                return (player.getMoney() >= 50000);
            case 2:
                return (player.getMoney() >= 250000);
            case 3:
                return (player.getMoney() >= 1000000);
            case 4:
                return (player.getMoney() >= 25000000);
            case 5: 
                return (player.getMoney() >= 500000000);
            default:
                return false;
        }
    }
}
