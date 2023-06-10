package JavaVersion;
public class Start {
    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Expected atleast 1 value, the name of the csv file");
            return;
        }

        String fileString = args[0];
        System.out.println(fileString);
        parser parse = new parser();
        parse.parseCSV(fileString);
        
    }
}
