package cricket_score;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Ground {
    Random rand  = new Random();
    Scanner in = new Scanner(System.in);
    int[][] userOver, computerOver;
    Team team ;
    int userruns, ballsRemaining, computerruns, wicket, target;
    Ground(int over){
        this.userOver = new int[over][6];
        this.computerOver = new int[over][6];
        this.ballsRemaining = 6 * over;
    }

    public void start() {

        System.out.print("\n+-----------------------------------------------+");
        System.out.print("\n|   1 --> SELECT TEAM   |   2 --> CREATE TEAM   |");
        System.out.print("\n+-----------------------------------------------+\n\nENTER OPTION : ");
        int option = 0;

        try {
            option = in.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("-= ENTER VALID NUMBER -=");
            start();
        }

        Player[] members = getTeamPlayers(option, true);

        int innings, computerIndex;
        int playerIndex = innings = computerIndex = target = 0;

        Player[] opponents = getTeamPlayers(1, false);

        boolean flag = this.toss();

        String tossChoosing = "\n+-----------------------------------------------+\n|      1 -->  BATTING    |    2 -->  BOWLING    |\n+-----------------------------------------------+";

        System.out.print((flag) ? tossChoosing : "\n OPPONENT WON THE TOSS CHOOSE TO BAT FIRST");

        if (flag) {

            System.out.print("\n\nWON THE TOSS CHOOSE OPTION : ");

            int userchoose;

            while (true) {
                try {
                    userchoose = in.nextInt();

                    if (userchoose == 1 || userchoose == 2)  break;

                } catch (InputMismatchException e) { System.out.println("-= ENTER VALID INPUT -=");}
            }
        }

        while (innings++ < 2) {

            int remainingballs = this.ballsRemaining;

            System.out.println((flag) ? "\n              | YOU ARE BATTING |" : "\n              | YOU ARE BOWLING |");

            for (int i = 1; i <= this.userOver.length * 6; i++) {

                System.out.print((flag) ? "\nHIT : " : "\nBOWl : ");

                int hit ;

                while(true) {

                    try{hit = in.nextInt();

                        if(hit< 0 || hit > 6) {

                            System.out.println("ENTER RANGE 0-6");

                        } else break;
                    }
                    catch (InputMismatchException e){

                        System.out.println("-= ENTER VALID INPUT -=");
                    }
                }

                int computer = this.rand.nextInt(7);

                if (flag) {

                    if (computer == hit || computer == 0) {

                        System.out.println("+-----------------------------------------------+");
                        System.out.printf("     OUT!   |    %s   ->    RUNS    :    %s    ", members[playerIndex].name, members[playerIndex].currentRuns);
                        System.out.println("\n+-----------------------------------------------+");

                        members[playerIndex].totalRuns += members[playerIndex].currentRuns;
                        playerIndex++;

                        this.userOver[(i - 1) / 6][(i - 1) % 6] = -1;
                        this.wicket++;

                    } else {

                        this.userOver[(i - 1) / 6][(i - 1) % 6] = hit;

                        members[playerIndex].currentRuns += hit;

                        this.userruns += hit;

                        target -= userruns;

                        if(target == 0 )break;

                    }

                    this.ballsRemaining--;

                    if ((i % 6 == 0)) {

                        this.scoreboard(this.userruns, members[playerIndex].name, i / 6, this.ballsRemaining, true, target);

                        System.out.println();
                    }

                } else {

                    if (computer == hit || computer == 0) {

                        System.out.println("+-----------------------------------------------+");
                        System.out.printf("   WICKET   |    %s    ->   RUNS    :    %s    ", opponents[computerIndex].name, opponents[computerIndex].currentRuns);
                        System.out.println("\n+-----------------------------------------------+");

                        opponents[computerIndex].totalRuns += opponents[computerIndex].currentRuns;

                        computerIndex++;

                        this.computerOver[(i - 1) / 6][(i - 1) % 6] = -1;

                        this.wicket++;

                    } else {

                        this.computerOver[(i - 1) / 6][(i - 1) % 6] = hit;

                        opponents[computerIndex].currentRuns += hit;

                        this.computerruns += hit;

                        target -= computerruns;

                        if(target == 0)break;

                    }

                    this.ballsRemaining--;

                    if ((i % 6 == 0)) {

                        this.scoreboard(this.computerruns, opponents[computerIndex].name, (i / 6), this.ballsRemaining, false, target);

                        System.out.println();
                    }

                }


                if (wicket == 11) {

                    this.wicket = 0;

                    flag = !flag;

                    System.out.println("-- ALL OUT --");

                    break;
                }

            }


            target = (flag) ? userruns+1 : computerruns+1;

            flag = !flag;

            this.wicket = 0;

            this.ballsRemaining = remainingballs;
        }
        System.out.println("     YOUR SCORE : " + this.userruns + "    |    COMPUTER SCORE : " + this.computerruns);
        System.out.println("+-----------------------------------------------+");
        if (this.userruns > this.computerruns) System.out.println("              \\\\\\\\\\  YOU WON  /////");
        else System.out.println("             \\\\\\\\\\  YOU LOSE  /////");
        System.out.println("+-----------------------------------------------+");
        System.out.print("\nDO YOU WANNA SEE MATCH SUMMARY 1.YES \\ 2.NO : ");

        int sum = in.nextInt();

        if (sum == 1) {
            matchSummary(true);
            player(members);
            player(opponents);
            System.out.print("\nWANT TO PLAY AGAIN 1-> YES / 2 -> NO :");
            int re = in.nextInt();
            boolean r = re == 1;
            if (r) start();
            else System.out.println("____________  THANK YOU _____________");
        }
    }

    public void matchSummary(boolean b){

        for (int i = 0; i < 2; i++) {

            System.out.println("\n+-----------------------------------------------+" + ((b) ? "\n|                PLAYER STATICS                 |" : "\n|              COMPUTER STATICS                 |"));
            System.out.println("+-----------------------------------------------+");
            for (int j = 0; j < this.userOver.length; j++) {
                System.out.print("|       OVER : " + (j + 1) + " : [ ");
                for (int k = 0; k < 6; k++) {
                    int a = (b) ? this.computerOver[j][k] : this.userOver[j][k];
                    System.out.print(((a < 0) ? "W" : a) + ", ");
                }
                System.out.println("]       |");
            }
            System.out.println("+-----------------------------------------------+");
            System.out.printf("|                  TOTAL RUNS %d                |" , ((b) ? this.userruns : this.computerruns));
            b = !b;
            System.out.println("\n+-----------------------------------------------+");

        }
    }

    void player(Player[] player){
        System.out.println("\n               | PLAYERs STATS | \n+-----------------------------------------------+");
        System.out.printf("|" + centerString(15,"NAME") +"|" + centerString(15,"POS") + "|" + centerString(15,"RUNS")+ "|");
        System.out.print("\n+-----------------------------------------------+");
        for (Player x: player){
            System.out.printf("\n|" + centerString(15,x.name) +"|" + centerString(15,x.position) + "|" + centerString(15,String.valueOf(x.currentRuns))+ "|");
        }
        System.out.println("\n+-----------------------------------------------+");
    }

    boolean toss() {

        int tossing = (int) (Math.random() * (4));

        System.out.print("\n+-----------------------------------------------+\n|     1 --> HEAD      |      2 --> TAILS        |\n+-----------------------------------------------+\n");

        System.out.print("\nCHOOSE TOSS : ");
        int choice;

        while (true) {
            try {
                choice = in.nextInt();
                if (choice == 1 || choice == 2) break;
                else continue;
            } catch (InputMismatchException e) {
                System.out.println("-= ENTER VALID INPUT -=");
            }
        }

        return (choice == tossing || tossing == 3);
    }

    public Player[] getTeamPlayers(int selection, boolean flag) {

        if (selection == 1) {
            String option = "\n+-----------------------------------------------+\n|   1 --> CSK   |   2 --> MI   |   3 --> RCB    |\n+-----------------------------------------------+\n\nSELECT TEAM : ";
            System.out.print((flag) ? option : "");
            int select;

            if(flag) {
                while(true){
                try { select = in.nextInt();
                if(select<1 || select>3) System.out.println("ENTER VALID OPTION");
                else break;
                }
                catch (InputMismatchException e){ System.out.println("ENTER VALID INPUT !");}
                }
            }else {
                select =  (int) ((Math.random() * (3 - 1)) + 1);
            }
            if(select == 1) {

                this.team = new Team("CSK");
                return this.team.csk;

            } else if (select == 2) {

                this.team = new Team("MI");
                return this.team.mi;

            }else if (select == 3) {
                this.team = new Team("RCB");
                return this.team.rcb;
            }
        }
        Team getTeam = new Team();
        return getTeam.self;
    }

    public static String centerString (int width, String s) {
        return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }


    public void scoreboard(int runs, String name, int over, int ballsRemaining, boolean b, int target){
        System.out.printf("\n+-----------------------------------------------+\n                 AFTER %d OVERS               \n+-----------------------------------------------+", over);
        System.out.printf("\n|" + centerString(15,"RUNS") +"|" + centerString(15,"OVER") + "|" + centerString(15,"WICKETS")+ "|");
        System.out.println("\n|  - - - - - -     - - - - - -     - - - - - -  |");
        System.out.printf("|" + centerString(15,String.valueOf(runs)) +"|" + centerString(15,String.valueOf(over)) + "|" + centerString(15,String.valueOf(this.wicket))+ "|");
        System.out.print("\n+-----------------------------------------------+");
        System.out.printf("\n|" + centerString(15,"STRIKER") +"|" + centerString(15,"TARGET") + "|" + centerString(15,"BALLS LEFT")+ "|");
        System.out.println("\n|  - - - - - -     - - - - - -     - - - - - -  |");
        System.out.printf("|" + centerString(15,name + "*") +"|" + centerString(15,String.valueOf(target)) + "|" + centerString(15,String.valueOf(ballsRemaining))+ "|");
        System.out.print("\n+-----------------------------------------------+");
    }


}
