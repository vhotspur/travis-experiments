import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;

public class Experiment {
    private static final String VERSION = "0.3";

    private static final String[] INTERESTING_PREFIXES = new String[] {
        "java.vm.",
        "java.vendor.",
        "java.version",
    };
    public static void main(String args[]) throws IOException {
        if (args.length == 0) {
            printAll();
        } else if (args[0].equals("--update-version")) {
            PrintStream ver = new PrintStream("version.txt");
            ver.println(VERSION);
            ver.close();
        } else if (args[0].equals("--list")) {
            System.out.println("java.vm.name");
            System.out.println("java.vm.vendor");
        } else {
            System.out.println(System.getProperty(args[0], ""));
        }
    }
    
    private static void printAll() {
        for (Map.Entry<?, ?> entry : System.getProperties().entrySet()) {
            String name = entry.getKey().toString();
            for (String prefix : INTERESTING_PREFIXES) {
                if (name.startsWith(prefix)) {
                    String value = entry.getValue().toString();
                    System.out.printf("%s = %s\n", name, value);
                }
            }
        }
    }
}
