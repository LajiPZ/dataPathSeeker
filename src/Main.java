import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HashMap<String,HashMap<String, ArrayList<String>>> sockets = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input sockets:");
        while (true) {
            String socketName = scanner.next();
            if (socketName.equals("-1")) {
                System.out.println("Socket input finished.");
                break;
            }
            HashMap<String, ArrayList<String>> signals = new HashMap<>();
            sockets.put(socketName,signals);
        }
        System.out.println("Input signals and the corresponding ports:");
        while (true) {
            System.out.printf("Input instruction:");
            String instrName = scanner.next();
            System.out.print("\n");
            if (instrName.equals("-1")) {
                System.out.println("Ins + Signal / Port input finished.");
                break;
            }
            while (true) {
                System.out.printf("Input signal:");
                String signalName = scanner.next();
                System.out.print("\n");
                if (signalName.equals("-1")) {
                    System.out.println("Signal / Port input finished.");
                    break;
                }
                System.out.printf("Input destination port:");
                String portName = scanner.next();
                System.out.print("\n");
                if (signalName.equals("/")) {
                    System.out.println("\"/\" detected. Not adding it to the list.");
                } else {
                    if (sockets.containsKey(portName)) {
                        if (sockets.get(portName).containsKey(signalName)) {
                            sockets.get(portName).get(signalName).add(instrName);
                        } else {
                            ArrayList<String> instrs = new ArrayList<>();
                            instrs.add(instrName);
                            sockets.get(portName).put(signalName,instrs);
                        }
                    } else {
                        System.out.println("No specified port found. Signal input invalid.");
                    }
                }
            }
        }
        System.out.println("Input complete.\n\n" +
                "Input 1 to locate signals connected to a specified port,\n" +
                "or input 2 to locate instructions associated with a signal input to the specified port,\n" +
                "or input 3 for statistics.\n" +
                "Input 0 will terminate the program.\n");
        while (true) {
            System.out.printf("Input operation:");
            int action = scanner.nextInt();
            switch (action) {
                case(0):
                    System.out.println("Exiting.");
                    return;
                case(1):
                    System.out.printf("Specify port:");
                    String port = scanner.next();
                    System.out.print("\n");
                    if (sockets.containsKey(port)) {
                        System.out.printf("PORT %s is connected to the following signal(s):\n",port);
                        sockets.get(port).forEach((signalName,instrs) -> System.out.printf("%s ",signalName));
                        System.out.print("\n\n");
                    } else {
                        System.out.println("No port with the specified name found!");
                    }
                    break;
                case(2):
                    System.out.printf("Specify port:");
                    String port1 = scanner.next();
                    if (sockets.containsKey(port1)) {
                        System.out.printf("Specify signal:");
                        String signalName = scanner.next();
                        System.out.print("\n");
                        if (sockets.get(port1).containsKey(signalName)) {
                            System.out.printf("SIGNAL %s, connected to %s, is from the following instruction(s):\n",signalName,port1);
                            sockets.get(port1).get(signalName).forEach((instrName) -> System.out.printf("%s ",instrName));
                            System.out.print("\n\n");
                        }
                        else {
                            System.out.println("No input with the specified name found!");
                        }
                    } else {
                        System.out.println("No port with the specified name found!");
                    }
                    break;
                case(3):
                    sockets.forEach((socket,signals) -> {
                        System.out.print("\n");
                        System.out.printf("PORT %s is connected to %d signal(s):\n",socket,signals.size());
                        signals.forEach((signal,instrs) -> {
                            System.out.printf("SIGNAL %s, invocated by %d instruction(s): ",signal,instrs.size());
                            instrs.forEach((instrName) -> System.out.printf("%s ",instrName));
                            System.out.print("\n");
                        });
                    });
                    break;
                default:
                    System.out.println("Invalid operation!");
                    break;
            }
        }
    }
}
