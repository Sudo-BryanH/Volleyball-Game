package ui;

import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPlayerRenderer extends JPanel {

    JFrame frame;
    Game game;


    public AddPlayerRenderer(Game game) {
        this.game = game;



        frame = new JFrame();
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.WHITE);




    }

    private void designLayout(Graphics g) {

        JButton adder;
        JButton next;
        JTextField num;


        g.drawRoundRect(30, 20, 340, 30, 15, 15);
        num = new JTextField(20);

        g.setColor(Color.LIGHT_GRAY);
        num.setBounds(30, 20, 340, 30);
        num.setBackground(Color.LIGHT_GRAY);
        num.setForeground(Color.black);
        num.setOpaque(true);
        num.setVisible(true);
        frame.add(num);

        g.setColor(Color.GREEN);
        g.fillRoundRect(30, 350, 340, 20, 15, 15);
        g.setColor(Color.black);
        g.drawString("Add Player to Roster", 60, 380);
        adder = new JButton("Add");
        adder.setBounds(30, 350, 340, 20);
        adder.setBackground(Color.GREEN);
        adder.setForeground(Color.black);
        adder.setOpaque(true);
        adder.setVisible(true);
        frame.add(adder);

        g.setColor(Color.RED);
        g.fillRoundRect(355, 375, 40, 20, 15, 15);
        g.setColor(Color.black);
        g.drawString("Continue", 355, 400);
        next = new JButton("Continue");
        next.setBounds(355, 375, 40, 20);
        next.setBackground(Color.RED);
        next.setForeground(Color.black);
        next.setOpaque(true);
        next.setVisible(true);
        frame.add(next);


    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        designLayout(g);
    }



}
