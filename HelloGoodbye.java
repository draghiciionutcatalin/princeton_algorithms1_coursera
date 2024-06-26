/* *****************************************************************************
 *  Name:              Ionut Draghici
 *  Created:           13 February 2024
 **************************************************************************** */

public class HelloGoodbye {
    public static void main(String[] args) {
        if (args.length == 2) {
            System.out.printf("Hello %s and %s.%n", args[0], args[1]);
            System.out.printf("Goodbye %s and %s.%n", args[1], args[0]);
        }
        else {
            System.out.println("ERROR: incorrect numbers of arguments");
        }
    }
}
