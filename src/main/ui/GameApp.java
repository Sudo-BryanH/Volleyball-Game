package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.*;


public class GameApp {

    Team myTeam;
    EnemyTeam weakTeam;
    EnemyTeam strongTeam;
    Scanner scan = new Scanner(System.in);
    Players weakMB1;
    Players weakMB2;
    Players weakSet;
    Players weakOH1;
    Players weakOH2;
    Players weakOP;
    Players strongMB1;
    Players strongMB2;
    Players strongSet;
    Players strongOH1;
    Players strongOH2;
    Players strongOP;
    Players myMB1;
    Players myMB2;
    Players mySet;
    Players myOP;
    Players myOH1;
    Players myOH2;
    Game game;
    EnemyTeam enemyTeam;
    Ball ball = new Ball();


    public GameApp() {
        setUp();
        beginGame();
    }


    // EFFECTS: Creates enemies teams and sets up your team. Then, decide which team you will be playing
    public void setUp() {

        // Make enemy teams
        weakTeam = (EnemyTeam) enemyTeamConstructor("weakTeam", weakMB1, weakMB2, weakSet, weakOP, weakOH1, weakOH2, 10);
        strongTeam = (EnemyTeam) enemyTeamConstructor("strongTeam", strongMB1, strongMB2, strongSet,
                strongOP, strongOH1, strongOH2, 3);

        // Make our team
        System.out.println("Let's create your team. What would you like to call your team?");
        String name = scan.nextLine();
        myTeam = myTeamConstructor(name, mySet, myMB1, myMB2, myOH1, myOH2, myOP);

        // TODO: Allow user to make a new player and add it to the team
        System.out.println("Which team would you like to choose to play against? "
                + "Your options are \n the weak team or the strong team");
        String input;
        input = scan.nextLine();
        while (!input.equals("weak team") && !input.equals("strong team")) {
            System.out.println("Please type in either 'weak team' or 'strong team'");
            input = scan.nextLine();
        }

        if (input.equals("strong team")) {
            game = new Game(myTeam, strongTeam);
            enemyTeam = strongTeam;
            System.out.println("You will now be playing against the strong team");
        } else {
            game = new Game(myTeam, weakTeam);
            enemyTeam = weakTeam;
            System.out.println("You will now be playing against the weak team");
        }

    }

    public void beginGame() {
        boolean gameOver = false;
        int turn = 0;

        System.out.println("Let us begin this game. Whenever prompted, follow the onscreen instructions");

        beginRally(turn);
        while (gameOver == false) {
            beginRally(turn);
            if (game.isGameOver()) {
                gameOver = true;
            }
            turn = game.getTurnNum();
        }

        // TODO check if user wants to change starting players
        System.out.println("Game has ended");
        System.out.println("The final score for this set is " + game.getScore() + "your team to the enemy team.");
        System.out.println("Thank you for playing");

    }

    public void beginRally(int turn) {
        boolean isOver = false;
        int setNum;
        int attackNum;

        serve(turn);
        while (!isOver) {
            game.flipTurnNum();
            // receive
            receive(turn);
            // choose set
            setNum = chooseSet(turn);
            attackNum = chooseAttack(setNum, turn);
            // other team defend
            chooseDefend(turn, setNum, attackNum);
            // Set and attack
            attack(turn, setNum, attackNum);
            game.flipTurnNum();
            isOver = checkReceive(turn);

        }

    }

    private boolean checkReceive(int turn) {
        List<Players> backrow = new ArrayList<>();
        boolean check = false;
        if (turn == 0) {
            for (Players p : enemyTeam.getStarters()) {
                if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                    backrow.add(p);
                }
            }
            check = game.checkReceive(ball.getXPos(), ball.getYPos(), backrow);
        } else if (turn == 1) {
            for (Players p : myTeam.getStarters()) {
                if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                    backrow.add(p);
                }
            }
            check = game.checkReceive(ball.getXPos(), ball.getYPos(), backrow);
        }
        if (check) {
            System.out.println("Ball has been received");
        }
        return check;
    }

    private void attack(int turn, int setNum, int attackNum) {
        if (turn == 0) {
            enemyTeam.set(setNum, ball);
            if (setNum == 0) {
                System.out.println("Ball has been set to the left");
            } else if (setNum == 1) {
                System.out.println("Ball has been set to the middle");
            } else {
                System.out.println("Ball has been set to the right");
            }

            enemyTeam.attack(setNum, attackNum, ball);

        } else if (turn == 1) {
            myTeam.set(setNum, ball);
            if (setNum == 0) {
                System.out.println("Ball has been set to the left");
            } else if (setNum == 1) {
                System.out.println("Ball has been set to the middle");
            } else {
                System.out.println("Ball has been set to the right");
            }
        }
        myTeam.attack(setNum, attackNum, ball);
    }

    private void serve(int turn) {
        int chance = (int) Math.random() * 2;
        if (turn == 0) {
            enemyTeam.startPosServe();
            myTeam.startPosNoServe();
            for (Players p : enemyTeam.getStarters()) {
                if (p.getRotation() == 1) {
                    p.serve(chance, ball);
                    System.out.println(p.getNum() + "Has served");
                }
            }
        } else if (turn == 1) {
            enemyTeam.startPosNoServe();
            myTeam.startPosServe();
            for (Players p : enemyTeam.getStarters()) {
                if (p.getRotation() == 1) {
                    p.serve(chance, ball);
                    System.out.println(p.getNum() + "Has served");
                }
            }
        }
    }

    private void receive(int turn) {
        if (turn == 0) {
            for (Players p : enemyTeam.getStarters()) {
                if ((p.getPosX() - ball.getXPos()) <= 1 && (p.getPosY() - ball.getYPos()) <= 1) {
                    p.receive(ball);
                }
            }
        } else if (turn == 1) {
            for (Players p : myTeam.getStarters()) {
                if ((p.getPosX() - ball.getXPos()) <= 1 && (p.getPosY() - ball.getYPos()) <= 1) {
                    p.receive(ball);
                }
            }
        }
    }

    private int chooseSet(int turn) {
        int chance2 = (int) Math.random() * 2;
        int chance3 = (int) Math.random() * 3;
        int choice;
        if (turn == 0) {
            if (enemyTeam.isSetterBack()) {
                return chance3;
            } else if (enemyTeam.isSetterBack()) {
                return chance2;
            }
        } else if (turn == 1) {
            if (myTeam.isSetterBack()) {
                System.out.println("Do you want to set the left side [0], middle [1], or right side [2] to attack?");
            } else {
                System.out.println("Do you want to set the left side [0], middle [1]");
            }
        }
        choice = scan.nextInt();
        System.out.println("you have picked" + choice);
        return choice;
    }

    private void chooseDefend(int turn, int setNum, int attackNum) {
        if (turn == 0) {
            enemyDefend(setNum, attackNum);
        } else {
            myDefence();
        }

    }

    private void enemyDefend(int setNum, int attackNum) {
        int multiplier = enemyTeam.getChance();
        int chance = (int) Math.random() * multiplier;
        if (enemyTeam.isSetterBack()) {
            enemyTeam.defendBSetter();
        } else {
            enemyTeam.defendFSetter();
        }

        if (chance == 1) {
            System.out.println("Enemy chance is in position to receive");
            if (setNum == 0) {
                enemyMoveDefence0();
            } else if (setNum == 1) {
                enemyMoveDefence1();
            } else if (setNum == 2) {
                enemyMoveDefence2();
            }

        }

    }

    private void myDefence() {
        if (myTeam.isSetterBack()) {
            myTeam.defendBSetter();
        } else {
            myTeam.defendFSetter();
        }

        for (Players p : myTeam.getStarters()) {

            if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                System.out.println("Choose this " + p.getNum() + p.getPlayingPosition() + "'s x positioning");
                int x = scan.nextInt();
                p.moveToX(x);
                System.out.println(p.getNum() + p.getPlayingPosition()
                        + "has been moved to (" + p.getPosX() + "," + p.getPosX() + ")");

            } else if (p.getPlayingPosition() != "Setter") {
                System.out.println("Choose this " + p.getNum() + p.getPlayingPosition() + "'s x positioning");
                int x = scan.nextInt();
                System.out.println("Choose this " + p.getNum() + p.getPlayingPosition() + "'s y positioning");
                int y = scan.nextInt();
                p.moveToX(x);
                p.moveToY(y);
                System.out.println(p.getNum() + p.getPlayingPosition()
                        + "has been moved to (" + p.getPosX() + "," + p.getPosX() + ")");

            }
        }
    }

    private void enemyMoveDefence1() {

        for (Players p : enemyTeam.getStarters()) {
            if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                p.moveToX(2);
            } else if (p.getPlayingPosition() == "Middle Blocker") {
                p.moveToX(2);
                p.moveToY(5);
            } else if (p.getPlayingPosition() == "Outside Hitter") {
                p.moveToX(10);
                p.moveToY(5);
            }
        }

    }

    private void enemyMoveDefence0() {

        for (Players p : enemyTeam.getStarters()) {
            if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                p.moveToX(6);
            } else if (p.getPlayingPosition() == "Middle Blocker") {
                p.moveToX(0);
                p.moveToY(3);
            } else if (p.getPlayingPosition() == "Outside Hitter") {
                p.moveToX(6);
                p.moveToY(2);
            }
        }

    }

    private void enemyMoveDefence2() {

        for (Players p : enemyTeam.getStarters()) {
            if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                p.moveToX(10);
            } else if (p.getPlayingPosition() == "Middle Blocker") {
                p.moveToX(6);
                p.moveToY(3);
            } else if (p.getPlayingPosition() == "Outside Hitter") {
                p.moveToX(12);
                p.moveToY(4);
            }
        }

    }


    private int chooseAttack(int setNum, int turn) {
        int choice = 1;
        int chance2 = (int) Math.random() * 2;
        int chance3 = (int) Math.random() * 3;
        if (turn == 1) {
            if (setNum == 0 || setNum == 2) {
                System.out.println("Choose whether you want to spike straight[0] or to the middle of the court[1]");
            } else if (setNum == 1) {
                System.out.println("Choose whether you want to spike to the "
                        + "to the left[2] or to the right [1]");
            }
            choice = scan.nextInt();
            return choice;
        } else if (turn == 0) {
            if (setNum == 0 || setNum == 2) {
                return chance2;
            } else if (setNum == 1) {
                return chance3;
            } else {
                return 1;
            }
        }

        return -1; // Should only happen if all else fails
    }


    public Team enemyTeamConstructor(String name, Players mb1, Players mb2,
                                     Players set, Players op, Players oh1,
                                     Players oh2, int chance) {
        set = new Setters(1, 0);
        mb1 = new MiddleBlockers(2, 0);
        oh1 = new OutsideHitter(3, 0);
        op = new OppositeHitter(4, 0);
        mb2 = new MiddleBlockers(5, 0);
        oh2 = new OutsideHitter(6, 0);

        EnemyTeam e;
        e = new EnemyTeam(name, mb1, mb2, set, op, oh1, oh2);
        e.setChance(chance);

        return e; // May cause errors with subtypes

    }

    public Team myTeamConstructor(String name, Players mb1, Players mb2,
                                  Players set, Players op, Players oh1,
                                  Players oh2) {

        int n;

        System.out.println("Alright, let's give your players numbers. Let's start with the setter, type in a number:");
        n = scan.nextInt();
        set = new Setters(n, 1);
        System.out.println("Next up, your first middle blocker");
        n = scan.nextInt();
        mb1 = new MiddleBlockers(n, 1);
        System.out.println("Next up, your first outside hitter");
        n = scan.nextInt();
        oh1 = new OutsideHitter(n, 1);
        System.out.println("Next up, your opposite hitter");
        n = scan.nextInt();
        op = new OppositeHitter(n, 1);
        System.out.println("Next up, your second middle blocker");
        n = scan.nextInt();
        mb2 = new MiddleBlockers(n, 1);
        System.out.println("Next up, your second outside hitter");
        n = scan.nextInt();
        oh2 = new OutsideHitter(n, 1);

        MyTeam m;
        m = new MyTeam(name, mb1, mb2, set, op, oh1, oh2);

        return m; // May cause errors with subtypes
    }

    public void playerConstructor(String position) {

    }


}
