package ui;

import model.Game;
import model.Players;
import model.Setters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddPlayers implements ActionListener {

    JFrame frame;
    Game game;
    JButton add = new JButton("Add");
    JButton done = new JButton("Continue");
    JTextArea textArea;
    JTextField num;

    int playerType;
    int playerNum;
    boolean canAdd;

    String[] playerTypes = {"Setter", "Middle Blocker", "Outside Hitter", "Opposite Hitter"};
    JComboBox comboBox = new JComboBox(playerTypes);
    List<Players> startingPlayers;


    public AddPlayers(Game game) {
        this.game = game;
        frame = new JFrame();
        designLayout();


    }

    public AddPlayers(List<Players> players, String enemyTeam) {
        frame = new JFrame();
        designLayout();
        startingPlayers = players;
    }

    private void designLayout() {
        frame.setSize(400, 400);
        frame.setBackground(Color.gray);
        comboBox.addActionListener(this);
        comboBox.setBackground(Color.WHITE);
        comboBox.setBounds(100, 100, 100, 40);
        frame.add(comboBox);
        frame.add(add);
        add.addActionListener(this);
        frame.add(done);
        done.addActionListener(this);
        frame.setVisible(true);

    }

    private void addPlayer() {
        Players p = null;
        if (playerType == 0) {
            p = new Setters(playerNum, 1);
        }

        game.getMyTeam().addPlayer(p);
        textArea.append("You have added player " + playerNum + "to your team. You now have "
                + game.getMyTeam().getRoster().size() + "On your team.");
        canAdd = false;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBox) {
            playerType = comboBox.getSelectedIndex();
        } else if (e.getSource() == add) {
            addPlayer();
        } else if (e.getSource() == done) {
            new GameAppGUI(startingPlayers);
        }

    }
}
