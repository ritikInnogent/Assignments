import java.util.Scanner;
import java.math.BigInteger;

class Recursion {
    // // Iterative Function
    // public static long iteration(int n) {
    //     long factorial = 1;
    //     for (int i = 1; i <= n; i++) {
    //         factorial = factorial * i;
    //     }
    //     return factorial;
    // }

    // // Recursive Function
    // public static long recursion(int n) {
    //     if (n == 0) {
    //         return 1;
    //     }
    //     return n * recursion(n - 1);
    // }


    // Iterative Function
    public static BigInteger iteration(int n) {
        BigInteger factorial = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }

    // Recursive Function
    public static BigInteger recursion(int n) {
        if (n == 0) {
            return BigInteger.ONE;
        }
        return BigInteger.valueOf(n).multiply(recursion(n - 1));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 1;

        while (true) {
            System.out.print("Enter any non-negative number: ");
            String input = sc.nextLine();

            if (input.matches("\\d+")) {
                n = Integer.parseInt(input);
                break;
            } else {
                System.out.println("Invalid input! Please enter a valid non-negative integer");
            }
        }

        System.out.println("Iterative factorial of " + n + " is: " + iteration(n));
        System.out.println("Recursive factorial of " + n + " is: " + recursion(n));
    }
}
