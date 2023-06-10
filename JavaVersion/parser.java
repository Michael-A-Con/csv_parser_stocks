package JavaVersion;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class parser {
    public void parseCSV(String file) {
        String line;
        String limiter = ",";
        int max = 9;
        int now = 0;
        Entry entry = new Entry();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(limiter);
                for (String value : data) {
                    if (value.contains("CUSIP") || value.contains("order batcher")) {
                        continue;
                    }

                    value = value.replaceAll("\"", "");
                    value = value.replace("$", "");
                    value = value.replace("(","");
                    value = value.replace(")", "");

                    if (now == 0) { //date
                        entry.date = value;
                    } else if (now == 3) { //stock
                        entry.stock = value;
                    } else if (now == 5) { //type action
                        entry.type = value;
                    } else if (now == 7) { //price 
                        entry.price = value;
                    } else if (now == 8) { //amount
                        if (entry.type.contains("Buy")) { //if its a buy
                            entry.amount = value;
                            entry.dividend = "0";
                        } else if (entry.type.contains("CDIV")) { //if its a dividend 
                            entry.amount = "0";
                            entry.dividend = value;
                        } else if (entry.type.contains("ACH")) { //if its a dividend 
                            entry.amount = value;
                            entry.dividend = "0";
                        }
                    }

                    now++;
                    //restart now to 0 since new entry
                    if (now >= max) {
                        entry.printString();
                        outputSheets(entry);
                        entry.reset();
                        now = 0;
                    }
                }

                //System.out.println(); // Move to the next line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void outputSheets(Entry entry) {



    }
}