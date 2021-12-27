package ma.inpt.libraryApp;

import java.time.LocalDate;
import java.util.Arrays;

import ma.inpt.libraryApp.entities.Car;
import ma.inpt.libraryApp.entities.User;
import ma.inpt.libraryApp.utils.MidnightApplicationRefresh;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ma.inpt.libraryApp.services.CarService;
import ma.inpt.libraryApp.services.NotificationService;
import ma.inpt.libraryApp.services.UserService;

@SpringBootApplication
public class LibraryApplication {

    // class attributes :
    final CarService carService;
    final UserService userService;
    final NotificationService notificationService;
    final BCryptPasswordEncoder pwEncoder;
    final MidnightApplicationRefresh midAppRef;

    // class constructor :
    public LibraryApplication(CarService carService, UserService userService, NotificationService notificationService, BCryptPasswordEncoder pwEncoder, MidnightApplicationRefresh midAppRef) {
        this.carService = carService;
        this.userService = userService;
        this.notificationService = notificationService;
        this.pwEncoder = pwEncoder;
        this.midAppRef = midAppRef;
    }

    // class methods :
    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {

            User user1 = new User("labriji", pwEncoder.encode("labriji"), "labrijisaad@gmail.com", "labriji", "saad", "el omrane 1, rue 1, lot 6", "07-62436500", "el-hajeb");
            user1.setRole("ROLE_ADMIN");

            User user2 = new User("snoussi", pwEncoder.encode("snoussi"), "snoussi@gmail.com", "snoussi", "amine", "chichawa chichawa", "07- ... ", "chichawa");
            user2.setRole("ROLE_EMPLOYEE");

            User user3 = new User("jawhar", pwEncoder.encode("jawhar"), "jawhar@gmail.com", "jawhar", "jawhar", "el om......r", "07-456789456", "fes");
            user3.setRole("ROLE_USER");

            User user4 = new User("isbaine", pwEncoder.encode("isbaine"), "isbaine@gmail.com", "isbaine", "mohamed", "el 1   33333333333 21 3, lot 6", "07-123459", "afourar");
            user4.setRole("ROLE_ADMIN");


            User user5 = new User("aakil", pwEncoder.encode("aakil"), "aakil.mahmoud@gmail.com", "Aakil", "mahmoud", "hay salam ,lot 38", "06-12347895", "Larache");
            User user6 = new User("hachami", pwEncoder.encode("hachami"), "hachami@gmail.com", "Hachami", "mohamed", "hey karima ,lot 27", "06-45681329", "Meknes");
            User user7 = new User("mustapha", pwEncoder.encode("mustapha"), "mustapha@gmail.com", "Mustapha", "Mustapha", "hey salam , lot 123", "06-4568162", "salé");
            User user8 = new User("mikasa", pwEncoder.encode("mikasa"), "Akerman.mikasa@gmail.com", "Ackerman", "Mikasa", "Shiganshina", "06-12365482", "Shiganshina : Wall Maria");
            User user9 = new User("meliodas", pwEncoder.encode("meliodas"), "melosdas@gmail.com", "Dragon Sin of Wrath", "Meliodas", "Kingdom of Danafor", "06-14587892", "Britannia");
            User user10 = new User("tanjiro", pwEncoder.encode("tanjiro"), "tanjiro@gmail.com", "Kamado", "tanjiro", "countryside of Japan", "06-12349875", "Japan");
            User user11 = new User("shinobu", pwEncoder.encode("shinobu"), "shinobu@gmail.com", "Kocho", "Shinobu", "countryside of Japan", "06-78945213", "Japan");
            User user12 = new User("midoriya", pwEncoder.encode("izuku"), "midoriya@gmail.com", "Izuku", "midoriya", "Musutafu, Japan", "06-12394567", "Japan");

            userService.save(user1);
            userService.save(user2);
            userService.save(user3);
            userService.save(user4);
            userService.save(user5);
            userService.save(user6);
            userService.save(user7);
            userService.save(user8);
            userService.save(user9);
            userService.save(user10);
            userService.save(user11);
            userService.save(user12);

            Car car1 = new Car("A Short History of Nearly Everything", "Bill Bryson", 2001, 4);
            Car car2 = new Car("In a Sunburned Country", "Bill Bryson", 2000, 1);
            Car car3 = new Car("Notes from a Small Island", "Bill Bryson", 2012, 3);
            Car car4 = new Car("The Lost Continent: Travels in Small Town America", "Bill Bryson", 2007, 2);
            Car car5 = new Car("The Lord of the Rings", "J.R.R. Tolkien", 2013, 6);
            Car car6 = new Car("The Known World", "Edward P. Jones", 1999, 1);
            Car car7 = new Car("Uncommon Carriers", "John McPhee", 2002, 2);
            Car car8 = new Car("The Control of Nature", "John McPhee", 2011, 3);
            Car car9 = new Car("Coming Into the Country", "John McPhee", 2009, 2);
            Car car10 = new Car("The Changeling", "Kate Horsley", 2015, 4);
            Car car11 = new Car("Harry Potter and the Half-Blood Prince", "J.K. Rowling , Mary GrandPré", 1998, 3);
            Car car12 = new Car("Harry Potter and the Order of the Phoenix", "J.K. Rowling , Mary GrandPré", 1999, 3);
            Car car13 = new Car("Harry Potter and the Chamber of Secrets", "J.K. Rowling", 2000, 3);
            Car car14 = new Car("Harry Potter and the Prisoner of Azkaban", "J.K. Rowling , Mary GrandPré", 2001, 3);
            Car car15 = new Car("Harry Potter Collection", "J.K. Rowling , Mary GrandPré", 2005, 3);
            Car car16 = new Car("The Road to Dune", "Frank Herbert , Brian Herbert , Kevin J. Anderson", 1998, 1);
            Car car17 = new Car("I am Charlotte Simmons", "Tom Wolfe", 2014, 2);
            Car car18 = new Car("The War", "Marguerite Duras", 2012, 4);
            Car car19 = new Car("The Broken Wings", "Kahlil Gibran", 2009, 3);
            Car car20 = new Car("Treasure Island", "Robert Louis Stevenson", 2016, 2);
            Car car21 = new Car("Underworld", "Don DeLillo", 2006, 1);
            Car car22 = new Car("Cosmopolis", "Don DeLillo", 1995, 6);
            Car car23 = new Car("Against the Day", "Thomas Pynchon", 2001, 1);
            Car car24 = new Car("Gravity's Rainbow", "Thomas Pynchon", 2017, 2);
            Car car25 = new Car("Democracy", "Joan Didion", 2016, 5);
            Car car26 = new Car("War and Peace", "Leo Tolstoy , Henry Gifford , Aylmer Maude , Louise Maude", 2009, 2);
            Car car27 = new Car("Anthem", "Ayn Rand", 2011, 1);
            Car car28 = new Car("Fullmetal Alchemist", "Hiromu Arakawa , Akira Watanabe", 2010, 3);
            Car car29 = new Car("Snow Crash", "Neal Stephenson , Guy Abadia", 2017, 6);
            Car car30 = new Car("Da Vinci Code", "Dan Brown", 2013, 4);
            Car car31 = new Car("Boku no Hero Academia", "Kōhei Horikoshi", 2014, 3);
            Car car32 = new Car("Attack on titan", "Hajime Isayama", 2009, 7);
            Car car33 = new Car("The Beginning After The End", "TurtleMe : Fuyuki23", 2020, 17);
            Car car34 = new Car("Solo leveling", "Chugong", 2019, 22);
            Car car35 = new Car("Tales of Demons & Gods", "Mad Snail", 2016, 162);

            carService.save(car1);
            carService.save(car2);
            carService.save(car3);
            carService.save(car4);
            carService.save(car5);
            carService.save(car6);
            carService.save(car7);
            carService.save(car8);
            carService.save(car9);
            carService.save(car10);
            carService.save(car11);
            carService.save(car12);
            carService.save(car13);
            carService.save(car14);
            carService.save(car15);
            carService.save(car16);
            carService.save(car17);
            carService.save(car18);
            carService.save(car19);
            carService.save(car20);
            carService.save(car21);
            carService.save(car22);
            carService.save(car23);
            carService.save(car24);
            carService.save(car25);
            carService.save(car26);
            carService.save(car27);
            carService.save(car28);
            carService.save(car29);
            carService.save(car30);
            carService.save(car31);
            carService.save(car32);
            carService.save(car33);
            carService.save(car34);
            carService.save(car35);

            car10.setTheUser(user3);
            car10.setReturnDate(LocalDate.of(2021, 5, 23));

            car34.setTheUser(user12);
            car34.setReturnDate(LocalDate.of(2021, 12, 28));

            car1.setTheUser(user3);
            car1.setReturnDate(LocalDate.of(2021, 5, 05));

            user3.setCars(Arrays.asList(car10, car1));

            carService.save(car1);
            carService.save(car10);
            userService.save(user3);

            midAppRef.midnightApplicationRefresher();
        };
    }
}
