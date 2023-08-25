package step.learning.basics.oop;

public class Journal extends Literature implements Periodic, Copyable {
    private int number;
    public String getNumber() {
        return Integer.toString(number);
    }

    public void setNumber(int number) {
        this.number = number;
    }


    @Override
    public String getCard() {
        return String.format(
                "Journal : '%s' №%s",
                super.getTitle(),
                this.getNumber()
        );
    }

    @Override
    public String getPeriod() {
        return "Monthly";
    }
}
