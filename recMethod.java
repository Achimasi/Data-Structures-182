import java.util.Scanner;


public class recMethod {

    public static void main(String[] args) {
        boolean useProg = true;
        while (useProg) {

            boolean validInput = false;
            Scanner scanner = new Scanner(System.in);
            while (!validInput) {
                System.out.println("1. Type 2 integers to get all integers in between. Smallest integer first and larger second.");
                System.out.println("2. Write a string and find the minimum character.");
                System.out.println("3. Your job pays confusingly, lets calculate how much you will have made on a specific day.");
                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    if (choice >= 1 && choice <= 3) {
                        validInput = true;


                        switch (choice) {
                            case 1:

                                System.out.println("Enter smallest number");
                                int small = scanner.nextInt();
                                System.out.println("Enter largest number");
                                int large = scanner.nextInt();
                                System.out.println("Sum of integers between " + small + " and " + large + " are " + sumInts(small, large));
                                break;
                            case 2:

                                System.out.println("Type a string");
                                scanner.nextLine();
                                String input = scanner.nextLine();
                                System.out.println("The minimum string in the line was " + minChar(input));
                                break;

                            case 3:
                                System.out.println("Type a day to see how much you would profit on that day.");
                                scanner.nextLine();
                                int days = scanner.nextInt();
                                System.out.printf("You would make $%.2f in %d day(s) .%n", pay(days), days);
                                break;
                        }

                    } else {
                        System.out.println("Invalid number. Please pick the options 1-3");
                        scanner.nextLine();
                    }
                }
            }
        }

        }


        public static int sumInts ( int small, int large){
            if (small == large) {
                System.out.println(small);
            } else if (small > large) {
                System.out.println("Please enter small integer first");
                return 0;
            } else {
                return small + sumInts(small + 1, large);

            }
            return 0;
        }

        public static char minChar (String string){
            if (string.length() == 1) {
                return string.charAt(0);
            }
            char min = minChar(string.substring(1));
            return (string.charAt(0) < min) ? string.charAt(0) : min;

        }

        public static double pay ( int day){
            if (day == 1) {
                return 0.01;
            } else {
                return 2 * pay(day - 1);
            }
        }
    }






