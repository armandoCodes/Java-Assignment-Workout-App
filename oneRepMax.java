/*
@Author: Armando Acevedo
@Email: acevedoramir@wisc.edu
@Class: CS 400, Florian Heimerl
 */
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;


public class oneRepMax {
    private static final Scanner SC = new Scanner(System.in);
    private static final String NAME_EMAIL = "acevedoramir@wisc.edu, Armando Acevedo";
    public static void main(String[] args){
        double weight;
        int reps;
        int height;
        int age;
        String gender = "";
        String response = "";
        double[] mainLifts = new double[6];
        System.out.println("Welcome to the WorkOutProgram and TDEE calculator!");
        System.out.println("Use this program to calculate your estimated one rep max for your bench, deadlift, squat " +
                "and press, your next workout, or your total daily energy expenditure");
        while(true) {
            System.out.println("*************************");
            printMenu();
            response = SC.next();
            System.out.println("*************************");
            switch(response) {
                case "o":
                    try {
                        System.out.print("Enter weight in lbs: ");
                        weight = SC.nextDouble();
                        System.out.print("Enter max reps for this weight: ");
                        reps = SC.nextInt();
                        double oneRepMax = calcOneRepMax(weight, reps);
                        System.out.println("Your one rep max is: " + oneRepMax);
                    } catch (InputMismatchException e) {
                        System.out.println("Error: User did not enter a number");
                    }
                    break;
                case "t":
                    try {
                        System.out.print("Enter your weight in lbs: ");
                        weight = SC.nextDouble();
                        System.out.print("Enter height in inches: ");
                        height = SC.nextInt();
                        System.out.print("Enter your age: ");
                        age = SC.nextInt();
                        System.out.print("male or female: ");
                        gender = SC.next();
                        System.out.println("Calculating TDEE....");
                        double tdee = calcTDEE(weight, height, age, gender);
                        System.out.println("Estimated Total Daily Energy Expenditure: " + tdee+" calories");
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Must enter valid numbers in fields above");
                    } catch (NoSuchElementException e) {
                        System.out.println("Error: Must enter a valid String");
                    }
                    break;
                case "c":
                    //create workout log based on Phrak's 3x5 training Style
                    print3x5Desc();
                    File newWorkoutLog = new File("./newWorkOutLog.txt");
                    File liftLogs = new File("./inputLifts.txt");
                    try {
                        createWorkOutLogHelper(SC, new PrintWriter(newWorkoutLog), mainLifts);
                        logLifts(new PrintWriter(liftLogs), mainLifts);
                    } catch (FileNotFoundException e){
                        System.out.println("error found with file");
                    }
                    break;
                case "u":
                    System.out.println("Let's update the workout log........");
                    System.out.print("Enter current liftLog file (ie. inputLifts.txt): ");
                    response = SC.next();
                    File inputLifts = new File("./"+response);
                    File workOutLog = new File("./newWorkOutLog.txt");
                    try {
                        //reads the txt file from the user, updates mainLift array
                        readLiftLog(new Scanner(inputLifts), mainLifts);
                    } catch (FileNotFoundException e) {
                        System.out.println("Error: Lift log not found.");
                    }
                    if (workOutLog.exists()) {
                        System.out.print("Did you complete your workouts for this week? (enter yes or no): ");
                        response = SC.next();
                        if (response.equals("yes")) {
                            //adds 2.5 lbs to all lifts
                            for (int i=0; i < mainLifts.length;i++) {
                                mainLifts[i] += 2.5;
                            }
                            System.out.println("Updating your workout...");
                            try {
                                logLifts(new PrintWriter(inputLifts), mainLifts);
                                workOutLogger(new PrintWriter(workOutLog), mainLifts);
                                System.out.println("Workout log updated!");
                            } catch (FileNotFoundException e) {
                                System.out.println("Error: Workout Log not found.");
                            }

                        } else if (response.equals("no")) {
                            System.out.print("Which lift did you fail at? (Enter bench, overhead-press, squat, barbell-row, " +
                                    "deadlift, or chinup): ");
                            response = SC.next();

                            switch(response) {
                                //update lift log here
                                case "bench":
                                    mainLifts[0] = calc5RepMax(mainLifts[0]);
                                    try {
                                        logLifts(new PrintWriter(inputLifts), mainLifts);
                                        workOutLogger(new PrintWriter(workOutLog), mainLifts);
                                        System.out.println("Workout log updated!");
                                    } catch (FileNotFoundException e) {
                                        System.out.println("Error: Workout Log not found.");
                                    }
                                    break;
                                case "overhead-press":
                                    mainLifts[1] = calc5RepMax(mainLifts[1]);
                                    try {
                                        logLifts(new PrintWriter(inputLifts), mainLifts);
                                        workOutLogger(new PrintWriter(workOutLog), mainLifts);
                                        System.out.println("Workout log updated!");
                                    } catch (FileNotFoundException e) {
                                        System.out.println("Error: Workout Log not found.");
                                    }
                                    break;
                                case "squat":
                                    mainLifts[2] = calc5RepMax(mainLifts[2]);
                                    try {
                                        logLifts(new PrintWriter(inputLifts), mainLifts);
                                        workOutLogger(new PrintWriter(workOutLog), mainLifts);
                                        System.out.println("Workout log updated!");
                                    } catch (FileNotFoundException e) {
                                        System.out.println("Error: Workout Log not found.");
                                    }
                                    break;
                                case "barbell-row":
                                    mainLifts[3] = calc5RepMax(mainLifts[3]);
                                    try {
                                        logLifts(new PrintWriter(inputLifts), mainLifts);
                                        workOutLogger(new PrintWriter(workOutLog), mainLifts);
                                        System.out.println("Workout log updated!");
                                    } catch (FileNotFoundException e) {
                                        System.out.println("Error: Workout Log not found.");
                                    }
                                    break;
                                case "deadlift":
                                    mainLifts[4] = calc5RepMax(mainLifts[4]);
                                    try {
                                        logLifts(new PrintWriter(inputLifts), mainLifts);
                                        workOutLogger(new PrintWriter(workOutLog), mainLifts);
                                        System.out.println("Workout log updated!");
                                    } catch (FileNotFoundException e) {
                                        System.out.println("Error: Workout Log not found.");
                                    }
                                    break;
                                case "chinup":
                                    mainLifts[5] = calc5RepMax(mainLifts[5]);
                                    try {
                                        logLifts(new PrintWriter(inputLifts), mainLifts);
                                        workOutLogger(new PrintWriter(workOutLog), mainLifts);
                                        System.out.println("Workout log updated!");
                                    } catch (FileNotFoundException e) {
                                        System.out.println("Error: Workout Log not found.");
                                    }
                                    break;
                                default:
                                    System.out.println("The lift you entered is not in the workout log");
                                    break;
                            }

                        } else {
                            System.out.println("Error: User did not enter yes or no.");
                        }
                    } else {
                        System.out.println("File does not exist within local directory.");
                    }
                    break;
                case "q":
                    System.out.println("Quitting now...");
                    System.out.println("Thanks for using the program");
                    System.exit(0);
                    break;
                default:
                    System.out.println("This is not a valid menu option. Try again");
                    break;
            }
        }
    }

    /**
     * Calculates estimated one rep max for lift entered by the user based on the weight used and the amount
     * of reps completed at that weight.
     * @param weight - user inputed element
     * @param reps - user elemenmt
     * @return - one rep max
     */
    private static double calcOneRepMax(double weight, int reps){
        return weight*(1+(reps/(double)30));
    }

    /**
     * Calculates the user's total daily energy expenditure based on weight, height, age, gender
     * @param weight
     * @param height
     * @param age
     * @param gender
     * @return - TDEE
     */
    private static double calcTDEE(double weight, double height, int age, String gender){
        double weightInKG = weight*0.45359237;
        double heightInCM = height*2.54;

        double TDEE = (10.0*weightInKG) + (6.25*heightInCM) - (5.0*age);
        if (gender.equalsIgnoreCase("male")) {
            TDEE+=5;
        } else if(gender.equalsIgnoreCase("female")) {
            TDEE-=161;
        }
        return TDEE*1.2;
    }

    /**
     * calculates 5 rep max based on one rep max
     * @param orm - one rep max variable
     * @return - 5 rep max
     */
    private static double calc5RepMax(double orm) {
        return orm*.85;
    }

    /**
     * Main menu helper method
     */
    private static void printMenu() {
        System.out.println("<----Welcome to the Workout app---->");
        System.out.println("o: oneRepMax");
        System.out.println("t: TDEE");
        System.out.println("c: create new workout");
        System.out.println("u: update workout");
        System.out.println("press q to quit");
        System.out.print("response ---> ");
    }

    /**
     * Prints the 3x5 workout description
     */
    private static void print3x5Desc() {
        System.out.println("Creating new 3x5 training log based on your 5 rep max...");
        System.out.println("The 5x5 training philosophy is based on 6 key lifts:" +
                " bench, squat, deadlift, row, overheadpress, and chinups.");
        System.out.println("Only three lifts are prescribed each training day performed" +
                "three days out of the week.");
        System.out.println("Day 1:\n" +
                "\t3x5 bench press / overhead press (alternating)\n" +
                "\t3x5 chinups / barbell rows (alternating)\n" +
                "\t3x5 squats");
        System.out.println("Day 2:\n" +
                "\t3x5 overhead press / bench press (alternating)\n" +
                "\t3x5 barbell rows / chinups (alternating)\n" +
                "\t1x5 deadlifts");
        System.out.println("Day 3:\n" +
                "\t3x5 bench press / overhead press (alternating)\n" +
                "\t3x5 chinups / barbell rows (alternating)\n" +
                "\t3x5 squats");
    }

    /**
     * Helper method that asks user for information on the 6 main lifts
     * @param sc - Scanner variable used for user input
     * @param pr - printWriter used to write to file
     * @param mainLifts - double array used to store all lifts from the user
     */
    private static void createWorkOutLogHelper(Scanner sc, PrintWriter pr, double[] mainLifts) {
        System.out.println("Enter your estimated one rep maxes for the following in lbs: ");
        System.out.print("Bench press: ");
        double bp = SC.nextDouble();
        mainLifts[0] = calc5RepMax(bp);

        System.out.print("Overheadpress press: ");
        double op = SC.nextDouble();
        mainLifts[1] = calc5RepMax(op);

        System.out.print("Squat: ");
        double squat = SC.nextDouble();
        mainLifts[2] = calc5RepMax(squat);

        System.out.print("Barbell row: ");
        double br = SC.nextDouble();
        mainLifts[3] = calc5RepMax(br);

        System.out.print("Deadlifts: ");
        double dl = SC.nextDouble();
        mainLifts[4] = calc5RepMax(dl);

        System.out.print("Chinups: ");
        int chin = SC.nextInt();
        mainLifts[5] = calc5RepMax(chin);
        System.out.println("Creating workout log...done!");
        workOutLogger(pr, mainLifts);
    }

    /**
     * Writes 3x5 workout to file
     * @param pr - printWriter variable
     * @param lifts - double array used to store lifts from the user
     */
    private static void workOutLogger(PrintWriter pr, double[] lifts) {
        pr.println("This Week's Workout: ");
        pr.print("Day 1:\n" +
                "\t3x5 Bench: "+lifts[0]+"\n" +
                "\t3x5 Chinups: "+lifts[5]+"\n" +
                "\t3x5 Squats: "+lifts[2]+"\n");
        pr.print("Day 2:\n" +
                "\t3x5 Overhead press: "+lifts[1]+"\n" +
                "\t3x5 Barbell Rows: " +lifts[3]+"\n"+
                "\t1x5 Deadlifts: "+lifts[4]+"\n");
        pr.print("Day 3:\n" +
                "\t3x5 Bench: "+lifts[0]+"\n" +
                "\t3x5 Chinups: "+lifts[5]+"\n" +
                "\t3x5 Squats: "+lifts[2]+"\n");
        pr.close();
    }

    /**
     * Helper method that writes to file each lift per line
     * in the order of bench press, overhead press, squat, barbell row, deadlift, and chinup
     * @param pr - printWriter element
     * @param lifts - double array used to store lifts from the user
     */
    private static void logLifts(PrintWriter pr, double[] lifts) {
        for(double lift: lifts){
            pr.println(lift);
        }
        pr.close();
    }

    /**
     * Helper method that reads txt file from user containing lifts for the week,
     * mainLifts array
     * @param sc - Scanner element
     * @param lifts - double array used to store lifts from the user
     */
    private static void readLiftLog(Scanner sc, double[] lifts) {
        int i =0;
        while(sc.hasNextDouble()){
            lifts[i] = sc.nextDouble();
            i++;
        }
    }
}
