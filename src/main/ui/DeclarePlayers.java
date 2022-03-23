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
    JButton done = new JButton("Continue");
    JTextArea textArea;
    JButton weakTeam;
    JButton strongTeam;

    Players setter = new Setters(-1, 1);
    Players middle1 = new MiddleBlockers(-1, 1);
    Players opposite = new OppositeHitter(-1, 1);
    Players outside1 = new OutsideHitter(-1, 1);
    Players middle2 = new MiddleBlockers(-1, 1);
    Players outside2 = new OutsideHitter(-1, 1);



    String enemyTeam;
    JTextField set = new JTextField("Choose the Setter's Number: ");
    JTextField mid1 = new JTextField("Choose the first Middle Blocker's Number: ");
    JTextField oh1 = new JTextField("Choose the first Outside Hitter's Number: ");
    JTextField op = new JTextField("Choose the Opposite Hitter's Number: ");
    JTextField mid2 = new JTextField("Choose the second Middle Blocker's Number: ");
    JTextField oh2 = new JTextField("Choose the second Outside Hitter's Number: ");


    List<Players> players = new ArrayList<>();

    // EFFECTS: constructs a declare players object
    public DeclarePlayers(Game game) {
        this.game = game;
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createPlayers();
        designLayout();

    }

    // EFFECTS: adds players to players list
    public void createPlayers() {
        players.add(setter);
        players.add(middle1);
        players.add(outside1);
        players.add(opposite);
        players.add(middle2);
        players.add(outside2);
    }

    // EFFECTS: constructs a declareplayer object
    public DeclarePlayers() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createPlayers();
        designLayout();

    }


    // MODIFIES: this
    // EFFECTS: puts panels and panels in place in frame
    private void designLayout() {

        frame.setSize(400, 550);
        createTextArea();

        done.setBounds(10, 480, 360, 40);
        done.setSize(380, 40);
        done.setBackground(new Color(30, 144, 255));
        done.setForeground(Color.WHITE);
        done.setOpaque(true);
        done.addActionListener(this);
        frame.add(done);


        set.setBounds(20, 20, 360, 40);
        set.setBackground(Color.lightGray);
        set.addActionListener(this);
        frame.add(set);


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

    // MODIFIES this
    // EFFECTS: creates a text area to write down instructions
    public void createTextArea() {

        textArea = new JTextArea();
        textArea.setForeground(Color.BLACK);
        textArea.append("Give each player a non-negative number and choose an enemy");

        textArea.setBounds(10, 370, 380, 100);
        textArea.setBackground(Color.lightGray);
        frame.add(textArea);


    }


    // EFFEECTS: returns if enough information is given to move to the next ui
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

    // MODIFIES: this
    // EFFECTS: reacts to user input. records player numbers inputted, enemy team requested, and if done is selected
            // moves on to addplayers
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == done) {
            if (allInput()) {
                frame.setVisible(false);
                new AddPlayers(players, enemyTeam);
            }
        }

        if (e.getSource() == set) {
            int num = parseInt(set.getText().substring("Choose the Setter's Number: ".length(), set.getText().length()));
            setter.declareNum(num);
            textArea.append("\n Your setter's number is: " + setter.getNum());
        } else if (e.getSource() == mid1) {
            int num = parseInt(mid1.getText().substring("Choose the first Middle Blocker's Number: ".length(), mid1.getText().length()));
            middle1.declareNum(num);
            textArea.append("\n Your first middle's number is: " + middle1.getNum());
        } else if (e.getSource() == mid2) {
            int num = parseInt(mid2.getText().substring("Choose the second Middle Blocker's Number: ".length(), mid2.getText().length()));
            middle2.declareNum(num);
            textArea.append("\n Your second middle's number is: " + middle2.getNum());
        } else if (e.getSource() == oh1) {
            int num = parseInt(oh1.getText().substring("Choose the first Outside Hitter's Number: ".length(), oh1.getText().length()));
            outside1.declareNum(num);
            textArea.append("\n Your first outside's number is: " + outside1.getNum());
        } else if (e.getSource() == oh2) {
            int num = parseInt(oh2.getText().substring("Choose the second Outside Hitter's Number: ".length(), oh2.getText().length()));
            outside2.declareNum(num);
            textArea.append("\n Your second outside's number is: " + outside2.getNum());
        } else if (e.getSource() == op) {
            int num = parseInt(op.getText().substring("Choose the Opposite Hitter's Number: ".length(), op.getText().length()));
            opposite.declareNum(num);
            textArea.append("\n Your opposite's number is: " + opposite.getNum());
        }

        if (e.getSource() == strongTeam) {
            this.enemyTeam = "Strong Team";
            textArea.append("\n You have chosen to play the strong team");
        } else if (e.getSource() == weakTeam) {
            this.enemyTeam = "Weak Team";
            textArea.append("\n You have chosen to play the weak team");
        }
    }


}
