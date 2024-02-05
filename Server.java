import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Calculator {
    public Server() throws RemoteException {
        super();
    }

    @Override
    public double add(double num1, double num2) throws RemoteException {
        return num1 + num2;
    }

    @Override
    public double subtract(double num1, double num2) throws RemoteException {
        return num1 - num2;
    }

    @Override
    public double multiply(double num1, double num2) throws RemoteException {
        return num1 * num2;
    }

    @Override
    public double divide(double num1, double num2) throws RemoteException {
        if (num2 != 0) {
            return num1 / num2;
        } else {
            throw new RemoteException("Cannot divide by zero");
        }
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
            Calculator stub = (Calculator) UnicastRemoteObject.exportObject(server, 0);

            // Bind the remote object's stub in the registry using Naming.rebind
            String serverName = "CalculatorService";
            java.rmi.Naming.rebind(serverName, stub);

            System.out.println("Server is ready to receive requests.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
