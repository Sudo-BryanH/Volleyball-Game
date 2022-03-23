package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class AddPlayers implements ActionListener {

    JFrame frame;
    Game game;
    JButton add = new JButton("Add");
    JButton done = new JButton("Continue");
    JTextArea textArea;
    JTextField num;

    int playerType = -1;
    int playerNum = -1;
    boolean canAdd;
    String enemyTeam;

    String[] playerTypes = {"Setter", "Middle Blocker", "Outside Hitter", "Opposite Hitter"};
    JComboBox comboBox = new JComboBox(playerTypes);
    List<Players> startingPlayers;
    List<Players> rosterPlayers;

    // EFFECTS: constructs AddPlayers ui
    public AddPlayers(List<Players> players, String enemyTeam) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        startingPlayers = players;
        this.enemyTeam = enemyTeam;
        this.rosterPlayers = new ArrayList<>();
        for (Players p : players) {
            rosterPlayers.add(p);
        }
        designLayout();

    }

    // MDOIFIES: this
    // EFFECTS: creates the frame and adds all necessary panels to it
    private void designLayout() {
        frame.setSize(400, 600);
        createTextArea();

        done.setBounds(10, 530, 360, 40);
        done.setSize(380, 40);
        done.setBackground(new Color(30, 144, 255));
        done.setForeground(Color.WHITE);
        done.setOpaque(true);
        done.addActionListener(this);
        frame.add(done);
        comboBox.addActionListener(this);
        comboBox.setBackground(Color.WHITE);
        comboBox.setBounds(10, 30, 100, 40);
        frame.add(comboBox);
        num = new JTextField("Player Number: ");
        num.setBounds(110, 30, 190, 40);
        num.setBackground(Color.lightGray);
        num.addActionListener(this);
        frame.add(num);

        add = new JButton("Add Player");
        add.setBounds(310, 30, 80, 40);
        add.setBackground(Color.lightGray);
        add.addActionListener(this);
        frame.add(add);
        JButton blank = new JButton();
        blank.setBounds(10, 320, 160, 40);
        frame.add(blank);
        frame.setVisible(true);

    }

    // EFFECTS: creates the text area
    public void createTextArea() {

        textArea = new JTextArea();
        textArea.setForeground(Color.BLACK);
        textArea.setText("Create as many players as you like."
                + "\nBe sure to input the player type and number before adding\n player");

        textArea.setBounds(10, 80, 380, 440);
        textArea.setBackground(Color.lightGray);
        frame.add(textArea);


    }

    // MODIFIES: this
    // EFFECTS: adds player as specified by user
    private void addPlayer() {
        Players p = null;

        if (playerType == 0) {
            p = new Setters(playerNum, 1);
        } else if (playerType == 1) {
            p = new MiddleBlockers(playerNum, 1);
        } else if (playerType == 2) {
            p = new OutsideHitter(playerNum, 1);
        } else if (playerType == 3) {
            p = new OppositeHitter(playerNum, 1);
        }

        rosterPlayers.add(p);
        textArea.append("\nYou have added player " + p.getPlayingPosition() + " " + playerNum
                + "\nto your team. \nYou now have " + rosterPlayers.size() + "On your team.");

    }



    // MODIFIES this
    // EFFECTS: reacts to user input, moves to set up a new game if done is selected
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBox) {
            playerType = comboBox.getSelectedIndex();
        } else if (e.getSource() == add) {
            if (inputAll()) {
                addPlayer();
                playerType = -1;
                playerNum = -1;
            }
        } else if (e.getSource() == done) {
            //frame.setVisible(false);
            new GameAppGUI(startingPlayers, rosterPlayers, enemyTeam);
        } else if (e.getSource() == num) {
            playerNum = parseInt(num.getText().substring("Player Number: ".length(), num.getText().length()));
        }

    }

    // EFFEECTS: returns whether or not a player is specified enough to be added
    private boolean inputAll() {
        return playerType != -1 && playerNum != -1;
    }
}
