package org.example;

import java.util.Scanner;


public class InputUtils {
    public static Scanner scanner = new Scanner(System.in);
    static public int getIntInput(){
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            try {
                return Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e)
            {
                System.out.println("Try again! Need a number.");
            }
        }
    }
}
