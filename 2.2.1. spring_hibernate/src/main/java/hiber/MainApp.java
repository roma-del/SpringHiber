package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User User1 = new User("User1", "Lastname1", "user1@mail.ru");
      User User2 = new User("User2", "Lastname2", "user2@mail.ru");

      Car carFord = new Car("Ford", 3);
      Car carMustang = new Car("Mustang", 6);


      User1.setCar(carFord);
      User2.setCar(carMustang);
      carFord.setUser(User1);
      carMustang.setUser(User2);

      userService.add(User1);
      userService.add(User2);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Mode = " + user.getCar().getModel());
         System.out.println("serius = " + user.getCar().getSeries());
         System.out.println();
      }

      User userWithCar = userService.getUserWithCar("Ford",3);
      System.out.println("Id = "+ userWithCar.getId());
      System.out.println("First Name = "+ userWithCar.getFirstName());
      System.out.println("Last Name = "+ userWithCar.getLastName());
      System.out.println("Email = "+ userWithCar.getEmail());

      context.close();
   }
}
