package thirukkural;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {

    ThirukkuralRunner thirukkuralRunner = new ThirukkuralRunner();

    Scanner in = new Scanner(System.in);

    public void dashboard() {

        System.out.println("""
                -=-=-=-=-=- WELCOME -=-=-=-=-=-
                
                    1 -> SEARCH BY NUMBER
                    2 -> SEARCH BY WORD
                    3 -> SEARCH BY CATEGORY
                    4 -> KURAL GAME
                    5 -> ALL KURALS
                    6 -> EXIT
                """);

        System.out.print("CHOOSE OPTION : ");
        try {
            byte option = in.nextByte();
            if (option > 6 || option < 1) {
                System.out.println("-=-=-= ENTER VALID NUMBER -=-=-=");
                dashboard();
            }
            if(option == 6)return;
            menuExecution(option);
        }catch (InputMismatchException e){
            System.out.println("-=-=-= ENTER VALID NUMBER -=-=-=");
            in.next();
            dashboard();
        }dashboard();

    }

    private void menuExecution(byte option) {

            switch (option) {
                case 1 -> {
                    while(true) {
                        System.out.print("\nENTER KURAL NUMBER : ");
                        int number = in.nextInt();
                        if(number < 1 || number > 1330){
                            System.out.println("\n-=-= ENTER BETWEEN 1-1330 -=-=");
                            continue;}
                        thirukkuralRunner.searchByNumber(number);
                        break;
                    }
                }
                case 2 -> {
                    System.out.print("ENTER WORD : ");
                    String word = in.next();
                    thirukkuralRunner.searchByWord(word);
                }
                case 3 -> {
                    System.out.println("1. ???????????????????????????????????? / Virtue");
                    System.out.println("2. ?????????????????????????????? / Wealth");
                    System.out.println("3. ??????????????????????????????????????? / Love");
                    System.out.println("ENTER OPTION : ");
                    String choice = in.next();
                    subCategories(choice);
                }
                case 4 -> thirukkuralRunner.kuralGame();
                case 5 -> thirukkuralRunner.everyKural();
                default -> System.out.print(" -=- ENTER VALID OPTION -=- ");
            }
    }

    private static boolean patternMatcher(String regex, String str){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    private void subCategories(String option) {

        switch (option) {
            case "1" -> {

                System.out.println("""
                        -=-=-= ???????????? =-=-=-
                        1. ?????????????????????????????? / Prologue
                        2. ?????????????????????????????? / Domestic Virtue
                        3. ?????????????????????????????? / Ascetic Virtue
                        4. ?????????????????? / Fate
                                            
                        CHOOSE OPTION :\s""");

                Scanner in = new Scanner(System.in);
                String choice = in.next();
                System.out.println("_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + "\n???????????? / Section : ???????????????????????????????????? / Virtue");
                switch (choice) {
                    case "1" -> thirukkuralRunner.boundaryPrinter(1, 40);
                    case "2" -> thirukkuralRunner.boundaryPrinter(41, 240);
                    case "3" -> thirukkuralRunner.boundaryPrinter(241, 370);
                    case "4" -> thirukkuralRunner.boundaryPrinter(371, 380);
                }
            }
            case "2" -> {

                System.out.println("""
                        -=-=-=-=- ???????????? -=-=-=-=-
                        1. ????????????????????? / Royalty
                        2. ?????????????????????????????? / Ministers of State
                        3. ????????????????????? / The Essentials of a State
                        4. ????????????????????? / Way of Making Wealth
                        5. ????????????????????? / The Excellence of an Army
                        6.???????????????????????? / Friendship
                        7.??????????????????????????? / Miscellaneous""");
                Scanner in = new Scanner(System.in);
                String choice = in.next();
                System.out.println("_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + "\n???????????? / Section : ???????????????????????????????????? / Virtue");
                switch (choice) {
                    case "1" -> thirukkuralRunner.boundaryPrinter(381, 630);
                    case "2" -> thirukkuralRunner.boundaryPrinter(631, 730);
                    case "3" -> thirukkuralRunner.boundaryPrinter(731, 750);
                    case "4" -> thirukkuralRunner.boundaryPrinter(751, 760);
                    case "5" -> thirukkuralRunner.boundaryPrinter(761, 780);
                    case "6" -> thirukkuralRunner.boundaryPrinter(781, 950);
                    case "7" -> thirukkuralRunner.boundaryPrinter(951, 1080);
                }
            }
            case "3" -> {
                System.out.println("""
                        -=-=-= ???????????? =-=-=-
                        1. ?????????????????????	The Pre-marital love
                        2. ????????????????????????	The Post-marital love
                                                
                        CHOOSE OPTION :\s""");
                Scanner in = new Scanner(System.in);
                String choice = in.next();
                System.out.println("_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + "\n???????????? / Section : ???????????????????????????????????? / Virtue");
                switch (choice) {
                    case "1" -> thirukkuralRunner.boundaryPrinter(1081, 1050);
                    case "2" -> thirukkuralRunner.boundaryPrinter(1051, 1330);
                }
            }
        }
    }
}

