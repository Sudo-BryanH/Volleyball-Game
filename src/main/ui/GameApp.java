package ui;

import java.util.Scanner;

import model.*;


public class GameApp {

    Team myTeam;
    Team weakTeam;
    Team strongTeam;
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
    Team enemyTeam;


    public GameApp() {
        setUp();
        beginGame();
    }


    // EFFECTS: Creates enemies teams and sets up your team. Then, decide which team you will be playing
    public void setUp() {

        // Make enemy teams
        weakTeam = enemyTeamConstructor("weakTeam", weakMB1, weakMB2, weakSet, weakOP, weakOH1, weakOH2, 10);
        strongTeam = enemyTeamConstructor("strongTeam", strongMB1, strongMB2, strongSet,
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
        System.out.println("Let us begin this game. Whenever prompted, follow the onscreen instructions");

        while (game.isGameOver() == false) {
            beginRally();
        }

        System.out.println("Game has ended");
        System.out.println("The final score for this set is " + game.getScore() + "your team to the enemy team.");
        System.out.println("Thank you for playing");

    }

    public void beginRally() {
        int turn = game.getTurnNum();
        boolean isOver = false;

        serve(turn);
        while (isOver = false) {
            game.flipTurnNum();
            // receive
            // set


        }

    }


    public Team enemyTeamConstructor(String name, Players mb1, Players mb2,
                                      Players set, Players op, Players oh1,
                                      Players oh2, int chance) {
        set = new Setters(1, 0);
        mb1 = new MiddleBlockers(2, 0);
        oh1 = new OutsideHitter(3, 0);
        op = new OppositeHitter(4, 0);
        mb2 = new MiddleBlockers(5,0);
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
        mb2 = new MiddleBlockers(n,1);
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
