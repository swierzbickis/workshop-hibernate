import model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Teacher.class) //dodajemy encje ktora bedzie zmapowana z dwoch tabel bazodanowych
                .addAnnotatedClass(RPGGame.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(StudentBook.class)
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Order.class)
                .buildSessionFactory();


        Teacher teacher = new Teacher();
        Teacher teacher2 = new Teacher(1, "09547",
                new Person("Jan", "Kowalski"),
                "Math teacher");

        //teacher.setType("Type");

        ComputerGame game = new RPGGame();



        sessionFactory.close();
    }

}
