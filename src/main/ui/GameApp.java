package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.*;

// This is the GameApp class where most of the user interface happens. Players can see
// information about the score, their players' positions, ball's positions and turns.
// Players can also control their players from here.

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
    int turn;

    // EFFECTS: constructor for a game
    public GameApp() {
        setUp();
        beginGame();
    }

    // MODIFIES: this, MyTeam, EnemyTeam, game
    // EFFECTS: Creates enemies teams and sets up your team and players. Then, decide which team you will be playing
    public void setUp() {
        String input;
        boolean status = false;
        // Make enemy teams
        weakTeam = (EnemyTeam) enemyTeamConstructor("weakTeam", weakMB1, weakMB2, weakSet,
                weakOP, weakOH1, weakOH2, 10);
        strongTeam = (EnemyTeam) enemyTeamConstructor("strongTeam", strongMB1, strongMB2, strongSet,
                strongOP, strongOH1, strongOH2, 3);

        // Make our team
        System.out.println("Let's create your team. What would you like to call your team?");
        input = scan.nextLine();
        myTeam = myTeamConstructor(input, mySet, myMB1, myMB2, myOH1, myOH2, myOP);

        chooseEnemyTeam();
        while (!status) {
            System.out.println("Would you like to add another player to the roster? Type y or n");
            input = scan.next();
            if (input.equals("y")) {
                addPlayerToTeam();
            } else {
                System.out.println("no more added. There are " + myTeam.getRoster().size() + "Players in roster");
                status = true;
            }
        }

        beginGame();
    }

    // MODIFIES: game
    // EFFECTS: Runs the game
    public void beginGame() {
        boolean gameOver = false;
        turn = game.getTurnNum();
        enemyTeam.changeRotation();
        enemyTeam.changeRotation();
        enemyTeam.changeRotation();

        System.out.println("Let us begin this game. Whenever prompted, follow the onscreen instructions");


        while (gameOver == false) {
            System.out.println(game.getScore());
            beginRally(turn);

            turn = game.getTurnNum();
            if (game.isGameOver()) {
                gameOver = true;
            }
        }

        // TODO check if user wants to change starting players
        System.out.println("Game has ended");
        System.out.println("The final score for this set is " + game.getScore() + "your team to the enemy team.");
        System.out.println("Thank you for playing");


    }

    // MODIFIES: game, ball
    // EFFECTS: Runs each rally. Shows the turn and ball position after every major event. Shows scores when finished.
    public int beginRally(int turn) {
        boolean isOver = false;
        int setNum;
        int attackNum;
        System.out.println("Turn number is " + turn);
        serve(turn);
        while (!isOver) {
            ballPos();
            game.flipTurnNum();
            turn = game.getTurnNum();
            System.out.println("Turn number changed to " + turn);
            receive(turn);
            setNum = chooseSet(turn);
            attackNum = chooseAttack(setNum, turn);
            chooseDefend(turn, setNum, attackNum);
            attack(turn, setNum, attackNum);
            ballPos();
            isOver = !checkReceive(turn);
        }
        endRally(turn);
        return turn;
    }


    // EFFECTS: returns true if a ball has been received, false otherwise
    private boolean checkReceive(int turn) {
        List<Players> backrow = new ArrayList<>();
        boolean check = false;
        if (turn == 1) {
            for (Players p : enemyTeam.getStarters()) {
                if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                    backrow.add(p);
                }
            }
            check = game.checkReceive(ball.getXPos(), ball.getYPos(), backrow);
        } else if (turn == 0) {
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

    // REQUIRES: turn[0, 1], setNum[0, 2], attackNum[0,2]
    // MODIFIES: ball, MyTeam, EnemyTeam, Players, ball
    // EFFECT: Ball goes to player then goes to opponent court
    private void attack(int turn, int setNum, int attackNum) {
        attackPositions(turn);
        if (turn == 0) {
            enemyTeam.set(setNum, ball);
            if (setNum == 0) {
                System.out.println("Ball has been set to the left and is attack towards" + attackNum);
            } else if (setNum == 1) {
                System.out.println("Ball has been set to the middle and is attack towards" + attackNum);
            } else {
                System.out.println("Ball has been set to the right and is attack towards" + attackNum);
            }
            enemyTeam.attack(setNum, attackNum, ball);

        } else if (turn == 1) {
            myTeam.set(setNum, ball);
            if (setNum == 0) {
                System.out.println("Ball has been set to the left and is attack towards" + attackNum);
            } else if (setNum == 1) {
                System.out.println("Ball has been set to the middle and is attack towards" + attackNum);
            } else {
                System.out.println("Ball has been set to the right and is attack towards" + attackNum);
            }
            myTeam.attack(setNum, attackNum, ball);
        }
    }

    // REQUIRES: turn [0, 1]
    // MODIFIES: MyTeam EnemyTeam Players
    // EFFECTS: moves the players into position to attack
    private void attackPositions(int turn) {
        if (turn == 0) {
            if (enemyTeam.isSetterBack()) {
                enemyTeam.attackBSetter();
            } else {
                enemyTeam.attackFSetter();
            }
        } else {
            if (myTeam.isSetterBack()) {
                myTeam.attackBSetter();
            } else {
                myTeam.attackFSetter();
            }
        }
    }

    // REQUIRES: turn [0, 1]
    // MODIFIES: ball
    // EFFECT: Ball goes to opponent court
    private void serve(int turn) {
        int chance = (int) (Math.random() * 2);
        int input;
        if (turn == 0) {
            enemyTeam.startPosServe();
            myTeam.startPosNoServe();
            for (Players p : enemyTeam.getStarters()) {
                if (p.getRotation() == 1) {
                    p.serve(chance, ball);
                }
            }
            System.out.println("Serve has been made");
        } else if (turn == 1) {
            enemyTeam.startPosNoServe();
            myTeam.startPosServe();
            System.out.println("Do you want to serve left[0] or right [1]");
            input = scan.nextInt();
            for (Players p : enemyTeam.getStarters()) {
                if (p.getRotation() == 1) {
                    p.serve(input, ball);
                }
            }
            System.out.println("Serve has been made");
        }
    }

    // REQUIRES: turn[0, 1]
    // MODIFIES: ball
    // EFFECTS: moves the ball to setter's position
    private void receive(int turn) {
        if (turn == 0) {
            for (Players p : enemyTeam.getStarters()) {
                if ((p.getPosX() - ball.getXPos()) <= 1 && (p.getPosY() - ball.getYPos()) <= 1) {
                    p.receive(ball);
                }
            }
            System.out.println("Ball has been received by the enemy");
        } else if (turn == 1) {
            for (Players p : myTeam.getStarters()) {
                if ((p.getPosX() - ball.getXPos()) <= 1 && (p.getPosY() - ball.getYPos()) <= 1) {
                    p.receive(ball);
                }
            }
            System.out.println("Ball has been received by us");
        }
        ballPos();
    }

    // REQUIRES: turn[0,1]
    // MODIFIES: this
    // EFFECTS: choose who to set
    private int chooseSet(int turn) {
        int chance2 = (int) (Math.random() * 2);
        int chance3 = (int) (Math.random() * 3);
        int choice;
        if (turn == 0) {
            if (enemyTeam.isSetterBack()) {
                System.out.println("Enemy can attack from the left, middle, or right");
                return chance3;
            } else if (!enemyTeam.isSetterBack()) {
                System.out.println("Enemy can only attack form the left or middle");
                return chance2;
            }
        } else if (turn == 1) {
            if (myTeam.isSetterBack()) {
                System.out.println("Do you want to set the left side [0], middle [1]");
            } else {
                System.out.println("Do you want to set the left side [0], middle [1], or right side [2] to attack?");
            }
        }
        choice = scan.nextInt();
        System.out.println("you have picked " + choice);
        return choice;
    }

    // REQUIRES: turn [0, 1] attackNum[0, 2] setNum[0, 2]
    // EFFECTS: Choose how to defend before an attack
    private void chooseDefend(int turn, int setNum, int attackNum) {
        if (turn == 1) {
            enemyDefend(setNum, attackNum);
        } else {
            System.out.println("Let's choose our defence formation");
            myDefence();
        }

    }

    // REQUIRES: turn [0, 1] attackNum[0, 2] setNum[0, 2]
    // MODIFIES: EnemyTeam, this, players
    // EFFECTS: Enemy choose how to defend before an attack
    private void enemyDefend(int setNum, int attackNum) {
        int multiplier = enemyTeam.getChance();
        int chance = (int) (Math.random() * multiplier);
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


    // MODIFIES: EnemyTeam, this, players
    // EFFECTS: We choose how to defend before an attack
    private void myDefence() {
        if (myTeam.isSetterBack()) {
            myTeam.defendBSetter();
        } else {
            myTeam.defendFSetter();
        }

        for (Players p : myTeam.getStarters()) {

            if (p.getRotation() >= 4) {
                System.out.println("Choose this " + p.getRotation() + p.getPlayingPosition() + "'s block position");
                int x = scan.nextInt();
                p.moveToX(x);
                System.out.println(p.getRotation() + p.getPlayingPosition()
                        + "has been moved to (" + p.getPosX() + "," + p.getPosX() + ")");

            } else if (!p.getPlayingPosition().equals("Setter")) {
                System.out.println("Choose this " + p.getRotation() + p.getPlayingPosition() + "'s x receive position");
                int x = scan.nextInt();
                System.out.println("Choose this " + p.getRotation() + p.getPlayingPosition() + "'s y receive position");
                int y = scan.nextInt();
                p.moveToX(x);
                p.moveToY(y);
                System.out.println(p.getRotation() + p.getPlayingPosition()
                        + "has been moved to (" + p.getPosX() + "," + p.getPosY() + ")");

            }
        }
    }

    // MODIFIES: EnemyTeam, this, players
    // EFFECTS: Enemy choose how to defend before an attack if setNum ==1
    private void enemyMoveDefence1() {

        for (Players p : enemyTeam.getStarters()) {
            if (p.getRotation() == 4 || p.getRotation() == 5 || p.getRotation() == 6) {
                p.moveToX(2);
            } else if (p.getPlayingPosition().equals("Middle Blocker")) {
                p.moveToX(2);
                p.moveToY(5);
            } else if (p.getPlayingPosition().equals("Outside Hitter")) {
                p.moveToX(10);
                p.moveToY(5);
            }
        }

    }

    // MODIFIES: EnemyTeam, this, players
    // EFFECTS: Enemy choose how to defend before an attack if setNum == 0
    private void enemyMoveDefence0() {

        for (Players p : enemyTeam.getStarters()) {
            if (p.getRotation() == 4 || p.getRotation() == 5 || p.getRotation() == 6) {
                p.moveToX(6);
            } else if (p.getPlayingPosition().equals("Middle Blocker")) {
                p.moveToX(0);
                p.moveToY(3);
            } else if (p.getPlayingPosition().equals("Outside Hitter")) {
                p.moveToX(6);
                p.moveToY(2);
            }
        }
    }

    // MODIFIES: EnemyTeam, this, players
    // EFFECTS: Enemy choose how to defend before an attack if setNum == 2
    private void enemyMoveDefence2() {

        for (Players p : enemyTeam.getStarters()) {
            if (p.getRotation() == 4 || p.getRotation() == 5 || p.getRotation() == 6) {
                p.moveToX(10);
            } else if (p.getPlayingPosition().equals("Middle Blocker")) {
                p.moveToX(6);
                p.moveToY(3);
            } else if (p.getPlayingPosition().equals("Outside Hitter")) {
                p.moveToX(12);
                p.moveToY(4);
            }
        }

    }

    // REQUIRES: setNum [0, 2], turn [0, 1]
    // MODIFIES: tis
    // EFFECTS: teams choose how to attack
    private int chooseAttack(int setNum, int turn) {
        int choice = 1;
        int chance2 = (int) (Math.random() * 2);
        int chance3 = (int) (Math.random() * 3);
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
        System.out.println(choice);
        return -1; // Should only happen if all else fails
    }


    // EFFECTS: constructs an enemy team
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
        e = new EnemyTeam(name, set, mb1, mb2, oh1, oh2, op);
        e.setChance(chance);

        return e; // May cause errors with subtypes

    }

    // EFFECTS: constructs our team
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
        m = new MyTeam(name, set, mb1, mb2, oh1, oh2, op);

        return m; // May cause errors with subtypes
    }

    // EFFECTS: creates and adds a player to our team roster
    public void addPlayerToTeam() {

        int num;
        String pos;

        Players p;
        System.out.println("Which player do you want to make? type 's' for setter, "
                + "'m' for middle, 'oh' for outside hitter, or 'op' for opposite");
        pos = scan.next();
        System.out.println("Which number would you like to assign to this player?");
        num = scan.nextInt();
        if (pos.equals("s")) {
            p = new Setters(num, 1);
            myTeam.addPlayer(p);
        } else if (pos.equals("m")) {
            p = new MiddleBlockers(num, 1);
            myTeam.addPlayer(p);
        } else if (pos.equals("oh")) {
            p = new OutsideHitter(num, 1);
            myTeam.addPlayer(p);
        } else if (pos.equals("op")) {
            p = new OppositeHitter(num, 1);
            myTeam.addPlayer(p);
        }
        System.out.println(pos + " num " + num + "has been added to the roster");
        System.out.println("there are now " + myTeam.getRoster().size() + " Players in your team's roster");
    }

    // EFFECTS: picks an enemy team to play against
    private void chooseEnemyTeam() {
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

    // EFFECTS: displays the ball's position
    private void ballPos() {
        System.out.println("Ball Position is at [" + ball.getXPos() + " , " + ball.getYPos() + "]");
    }

    // EFFECTS: displays score and other information after a rally has ended
    private void endRally(int turn) {
        System.out.println("Turn number is " + turn);
        if (turn == 0) {
            System.out.println("Rally is over, enemy team scored");
            game.enemyScore();
        } else {
            System.out.println("Rally is over, our team scored");
            game.myScore();
        }
    }

}