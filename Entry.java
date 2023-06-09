public class Entry {
    String date;
    String type;
    String stock;
    String amount;
    String price;
    String fees = "0";
    String split = "1";
    String dividend;

    public void printString() {
        System.out.println(this.date +", "+ this.type +", "+ this.stock +", "+ this.amount +", "+ this.price +", "+ this.dividend);
    }

    public void reset() {
        this.date = "";
        this.type = "";
        this.stock = "";
        this.amount = "";
        this.price = "";
        this.dividend = "";
    }
}
