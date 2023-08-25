package step.learning.basics.oop;

public class Poster extends Literature {
    private boolean isColored;

    @Override
    public String getCard() {
        return String.format(
                "Poster : '%s' %s",
                super.getTitle(),
                isColored() ? "Colored" : "Not colored"
        );
    }

    public boolean isColored() {
        return isColored;
    }

    public void setColored(boolean colored) {
        isColored = colored;
    }
}
