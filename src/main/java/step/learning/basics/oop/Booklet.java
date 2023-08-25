package step.learning.basics.oop;

public class Booklet extends Literature implements Copyable
{
    private Publisher publisher;

    public Booklet(String publisherName) {
        publisher = new Publisher();
        publisher.setName(publisherName);
    }

    @Override
    public String getCard() {
        return String.format(
                "Booklet: %s '%s'",
                this.getPublisher(),
                super.getTitle()
        );
    }

    public String getPublisher() {
        return publisher.getName();
    }

    public void setPublisher(String name) {
        this.publisher.setName(name);
    }
}
