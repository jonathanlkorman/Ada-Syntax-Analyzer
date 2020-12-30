public class Main{

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("No file provided");
            System.exit(1);
        }
        String s = args[0];
        LexicalAnalyzer la = new LexicalAnalyzer(s);
        Parser p = new Parser(la);

        p.Start();
    }
}