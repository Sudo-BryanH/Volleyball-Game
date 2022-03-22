package ui;

import jdk.nashorn.internal.objects.annotations.Setter;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class DeclarePlayers implements ActionListener {

    JFrame frame;
    Game game;
    JButton add = new JButton("Add");
    JButton done = new JButton("Continue");
    JTextArea textArea;
    JButton weakTeam;
    JButton strongTeam;



    String enemyTeam;
    JTextField set = new JTextField("Choose the Setter's Number: ");
    JTextField mid1 = new JTextField("Choose the first Middle Blocker's Number: ");
    JTextField oh1 = new JTextField("Choose the first Outside Hitter's Number: ");
    JTextField op = new JTextField("Choose the Opposite Hitter's Number: ");
    JTextField mid2 = new JTextField("Choose the second Middle Blocker's Number: ");
    JTextField oh2 = new JTextField("Choose the second Outside Hitter's Number: ");


    List<Players> players = new ArrayList<>();


    public DeclarePlayers(Game game) {
        this.game = game;
        frame = new JFrame();
        createPlayers();
        designLayout();

    }

    public void createPlayers() {
        players.add(new Setters(-1, 1));
        players.add(new MiddleBlockers(-1, 1));
        players.add(new OutsideHitter(-1, 1));
        players.add(new OppositeHitter(-1, 1));
        players.add(new MiddleBlockers(-1, 1));
        players.add(new OppositeHitter(-1, 1));
    }

    public DeclarePlayers() {
        frame = new JFrame();
        designLayout();
    }

    private void designLayout() {


        frame.setSize(400, 450);

        done.setBounds(10, 380, 360, 40);
        done.setSize(380, 40);
        done.setForeground(Color.red);
        done.addActionListener(this);
        frame.add(done);


        set.setBounds(20, 20, 360, 40);
        set.setBackground(Color.lightGray);
        set.addActionListener(this);
        frame.add(set);

        textArea = new JTextArea("Choose the Setter's Number");
        mid1.setBounds(20, 70, 360, 40);
        mid1.setBackground(Color.lightGray);
        mid1.addActionListener(this);
        frame.add(mid1);

        //mid2.setBackground(Color.gray);
        mid2.setBounds(20, 120, 360, 40);
        mid2.setBackground(Color.lightGray);
        mid2.addActionListener(this);
        frame.add(mid2);

        //op.setBackground(Color.gray);
        op.setBounds(20, 170, 360, 40);
        op.setBackground(Color.lightGray);
        op.addActionListener(this);
        frame.add(op);

        //oh1.setBackground(Color.gray);
        oh1.setBounds(20, 220, 360, 40);
        oh1.setBackground(Color.lightGray);
        oh1.addActionListener(this);
        frame.add(oh1);

        oh2.setBounds(20, 270, 360, 40);
        oh2.setBackground(Color.lightGray);
        oh2.addActionListener(this);
        frame.add(oh2);




        weakTeam = new JButton("Weak Team");
        weakTeam.setBounds(10, 320, 190, 40);
        weakTeam.addActionListener(this);
        weakTeam.setForeground(Color.BLACK);
        frame.add(weakTeam);

        strongTeam = new JButton("Strong Team");
        strongTeam.setBounds(200, 320, 190, 40);
        strongTeam.addActionListener(this);
        strongTeam.setForeground(Color.BLACK);
        frame.add(strongTeam);

        JButton blank = new JButton();
        blank.setBounds(10, 320, 160, 40);
        frame.add(blank);

        frame.setVisible(true);


    }



    public boolean allInput() {
        boolean playersDeclared = true;
        boolean enemyDeclared = false;
        for (Players p : players) {
            if (p.getNum() == -1) {
                playersDeclared = false;
                break;
            }
        }


        if (enemyTeam != null) {
            enemyDeclared = true;
        }
        return playersDeclared && enemyDeclared;

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == done) {
            if (allInput()) {
                new AddPlayers(players, enemyTeam);
            }
        }

        if (e.getSource() == set) {
            int num = parseInt(set.getText().substring("Choose the Setter's Number: ".length(), set.getText().length()));
            players.get(0).declareNum(num);
        } else if (e.getSource() == mid1) {
            int num = parseInt(mid1.getText().substring("Choose the first Middle Blocker's Number: ".length(), mid1.getText().length()));
            players.get(1).declareNum(num);
        } else if (e.getSource() == mid2) {
            int num = parseInt(mid2.getText().substring("Choose the second Middle Blocker's Number: ".length(), mid2.getText().length()));
            players.get(4).declareNum(num);
        } else if (e.getSource() == oh1) {
            int num = parseInt(oh1.getText().substring("Choose the first Outside Hitter's Number: ".length(), oh1.getText().length()));
            players.get(2).declareNum(num);
        } else if (e.getSource() == oh2) {
            int num = parseInt(oh2.getText().substring("Choose the second Outside Hitter's Number: ".length(), oh2.getText().length()));
            players.get(5).declareNum(num);
        } else if (e.getSource() == op) {
            int num = parseInt(op.getText().substring("Choose the Opposite Hitter's Number: ".length(), op.getText().length()));
            players.get(3).declareNum(num);
        }

        if (e.getSource() == strongTeam) {
            this.enemyTeam = "Strong Team";
        } else {
            this.enemyTeam = "Weak Team";
        }
    }


}
