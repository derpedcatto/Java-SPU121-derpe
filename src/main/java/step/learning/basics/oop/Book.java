package step.learning.basics.oop;

public class Book extends Literature implements Copyable {
    private String author;

    @Override
    public String getCard() {
        return String.format(
                "Book: %s '%s'",
                this.getAuthor(),
                super.getTitle()
        );
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

/*
Спадкування

Literature [ title getTitle() ]

Book extends Literature
book.title book.getTitle()
 |          |
 x  [ ------|--- author getAuthor() ] this
[ title getTitle() ] super
            |
       lit.getTitle()


       book.getTitle()
            |
         override                               | Ілюстрація різниці
      [ getTitle() author getAuthor() ] this    | this.getTitle() та
[ title getTitle() ] super                      | super.getTitle()
            |                                   | ! в межах одного об'єкту
        lit.getTitle()

!!! Помилкогенність - вживання у кодах Book просто виклику getTitle()
бо насправді методів може бути два - this.getTitle() та super.getTitle()
Якщо this.getTitle() не створено, то викликається super.getTitle()
АЛЕ якщо описати this.getTitle(), то виклик автоматично переключиться на нього

Висновок: дуже бажано вживати префікси this. або .super
 */