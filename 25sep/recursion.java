import java.util.Scanner;

class Recursion {
    
    // here we simply using for loop for iteration
    public static int iteration(int n) {  
        int factorial = 1;
        // here is multiply each value one by one and store it to the factorial variable
        for (int i = 1; i <= n; i++) {
            factorial = factorial * i; 
            //by this we calculate the factorial and add each calculated value
        }
        return factorial;
    }


    // basically this function call another function untill it finds the base case 
    public static int Recursion(int n) {
        //this is the base case where the function call end and send result from bottom to top
        if (n == 0) {
            return 1;
        }
        // we simply solve problem by to send the solution to the small instances to the current problem
        int factorialnm1 = Recursion(n - 1); 
        int factorial = n * factorialnm1;    //multiply them into the result 
        return factorial;
    }

    public static void main(String[] args) {
        //this scanner class is used to take input from user
        Scanner sc = new Scanner(System.in);    //system.in indicates that we take imput from keyboard
        System.out.print("Enter any number: ");
        int n = sc.nextInt(); // this function reads the input

       //we simply call the iteration function and print it
        System.out.println(iteration(n));

        //we simply call the recursion function and print it
        System.out.println(Recursion(n));
    }
}
