package iaroslav.eremeev.util;

import iaroslav.eremeev.model.List;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Util {
    public static String getArgument(String[] inputParts, int indexToStart){
        StringBuilder sb = new StringBuilder();
        for (int i = indexToStart; i < inputParts.length; i++) {
            sb.append(inputParts[i]);
            if (!(i == inputParts.length - 1)) sb.append(" ");
        }
        return sb.toString();
    }
    public static void showInfo(){
        System.out.println("Welcome to the list simulator.");
        System.out.println("To show the actual list type SHOW");
        System.out.println("To add an element to the list type ADD x (x is the element)");
        System.out.println("Make sure all the elements belong to the same type");
        System.out.println("You can also type ADD x i (x is the element, i is the index to put the element to");
        System.out.println("To remove the element type RM i (i is the index of element to remove)");
        System.out.println("To get the element by index type GET i (i is the index)");
        System.out.println("To check the first index of a certain element type INDEX x (x is the element)");
        System.out.println("To check if the list contains an element type CONTAINS x (x is the element)");
        System.out.println("To clear the list type CLEAR");
        System.out.println("To check if the list is empty type EMPTY? (with the question mark)");
        System.out.println("To check the size of the list type SIZE");
        System.out.println("To show this information once again type INFO");
        System.out.println("To exit the program type EXIT");
    }

    public static void runListOperations(List list, Scanner scanner){
        String input = scanner.nextLine();
        String[] inputParts = input.split(" ");
        while (!inputParts[0].equals("EXIT")){
            if (inputParts[0].equals("SHOW")) {
                System.out.println(list.toString());
            }
            if (inputParts[0].equals("ADD")){
                try {
                    int index = Integer.parseInt(inputParts[1]);
                    list.add(index, getArgument(inputParts, 2));
                }
                catch (NumberFormatException e){
                    list.add(getArgument(inputParts, 1));
                }
            }
            if (inputParts[0].equals("RM")){
                try {
                    list.remove(Integer.parseInt(inputParts[1]));
                }
                catch (NumberFormatException e){
                    System.out.println("Incorrect index after RM");
                }
            }
            if (inputParts[0].equals("GET")){
                try {
                    System.out.println(list.get(Integer.parseInt(inputParts[1])));
                }
                catch (NumberFormatException|ArrayIndexOutOfBoundsException e){
                    System.out.println("Incorrect index after RM");
                }
            }
            if (inputParts[0].equals("INDEX")){
                System.out.println(list.indexOf(getArgument(inputParts, 1)));
            }
            if (inputParts[0].equals("CONTAINS")){
                System.out.println(list.contains(getArgument(inputParts, 1)));
            }
            if (inputParts[0].equals("CLEAR")){
                list.clear();
            }
            if (inputParts[0].equals("EMPTY?")){
                System.out.println(list.isEmpty());
            }
            if (inputParts[0].equals("SIZE")){
                System.out.println("Size of the list is " + list.size());
            }
            if (inputParts[0].equals("INFO")){
                showInfo();
            }
            input = scanner.nextLine();
            inputParts = input.split(" ");
        }
    }

    public static void runProgram(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("To create a new list type START. To exit right now type anything else");
        String input = scanner.next();
        if (input.equals("START")){
            List list = new List();
            showInfo();
            runListOperations(list, scanner);
        }
        else {
            try {
                System.out.println("Are you sure to exit? Type YES or NO");
                String yesNoInput = scanner.nextLine();
                if (yesNoInput.equals("NO")) runProgram();
                else if (!yesNoInput.equals("YES")) throw new InputMismatchException();
            }
            catch (InputMismatchException e){
                System.out.println("Try again. You should type either YES or NO!");
            }
        }
    }
}
