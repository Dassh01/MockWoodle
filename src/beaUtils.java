import java.util.HashSet;
import java.util.Scanner;
import java.util.function.Function;

public class beaUtils {

    protected static final String GREEN = "\u001B[32m";
    protected static final String YELLOW = "\u001B[33m";
    protected static final String RESET = "\u001B[0m";

    /**
     * Comes from retyping user prompts and scanner methods over and over in APCSA projects :C
     * Used to simplify the above-mentioned process, does the prompt and scan all in one (now with an embedded try catch!) (InputMismatch be gone!)
     * @param askText [String] Prompt the user is asked in console
     * @param inputFunction [Scanner] lambda method (i.e: Scanner::nextLine, Scanner::nextInt), dependent on what data type you want the function to return as its return type is generic
     * @param scanner [Scanner] (hopefully connected to System input...)
     * @return Returns data type dependent on the scanner lambda inserted
     */
    protected static <T> T askForThing(String askText, Function<Scanner, T> inputFunction, Scanner scanner) {
        System.out.println(askText);
        T returningInformation;
        while (true) {
            try {
                returningInformation = inputFunction.apply(scanner);
                if (returningInformation instanceof Integer) {
                    scanner.nextLine();
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println(askText);
                continue;
            }
            break;
        }
        return returningInformation;
    }

    /**
     * Interprets a string into a boolean. Worked with a (yes/no) or a (y/n) prompt, case-insensitive
     * @param userInput [String] Raw user input as answer to a prompt similar to the ones listed above
     * @return [Boolean] true IF user inputted something that looks like a yes, false if user inputted something that looks something other than a yes
     */
    protected static boolean userInputBoolean(String userInput) {
        String formattedUserInput = userInput.toLowerCase().trim();
        return formattedUserInput.equals("y") || formattedUserInput.contains("yes");
    }

    /**
     * Interprets a string into a boolean. Worked with a (yes/no) or a (y/n) prompt, case-insensitive.
     * The more manual/specific version that allows you to choose values that will return true
     * @param UserInput [String] Raw user input as answer to a prompt similar to the ones listed above
     * @param trueValues [HashSet] Values that, if user has entered the same, will make the method return true
     * @return [Boolean] true IF user inputted something that looks like a yes by the standards of the HashSet, false if user inputted something that looks something other than a yes
     */
    protected static boolean userInputBoolean(String UserInput, HashSet<String> trueValues) {
        String formattedUserInput = UserInput.toLowerCase().trim();
        return trueValues.contains(formattedUserInput);
    }

}
