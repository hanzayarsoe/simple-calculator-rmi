import java.rmi.Naming;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            // Lookup the remote object from the registry using Naming.lookup
            String serverName = "rmi://localhost/CalculatorService";
            Calculator calculator = (Calculator) Naming.lookup(serverName);

            try (// Get user inputs
            Scanner scanner = new Scanner(System.in)) {
                System.out.print("Enter command (add for 1 \nsubtract for 2\nmultiply for 3\ndivide for 4 : ");
                String command = scanner.nextLine();

                System.out.print("Enter first number: ");
                double num1 = scanner.nextDouble();

                System.out.print("Enter second number: ");
                double num2 = scanner.nextDouble();

                // Perform calculation based on user command
                double result = 0;
                switch (command.toLowerCase()) {
                    case "1":
                        result = calculator.add(num1, num2);
                        break;
                    case "2":
                        result = calculator.subtract(num1, num2);
                        break;
                    case "3":
                        result = calculator.multiply(num1, num2);
                        break;
                    case "4":
                        result = calculator.divide(num1, num2);
                        break;
                    default:
                        System.out.println("Invalid command");
                        System.exit(1);
                }

                System.out.println("Result: " + result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
