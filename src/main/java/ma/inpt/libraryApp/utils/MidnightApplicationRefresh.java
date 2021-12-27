package ma.inpt.libraryApp.utils;

import java.time.LocalDate;
import ma.inpt.libraryApp.entities.Car;
import ma.inpt.libraryApp.entities.Notification;
import ma.inpt.libraryApp.entities.User;
import org.springframework.stereotype.Component;
import ma.inpt.libraryApp.services.CarService;
import ma.inpt.libraryApp.services.NotificationService;
import ma.inpt.libraryApp.services.UserService;

@Component
public class MidnightApplicationRefresh {

	// class attributes :
    final CarService carService;
    final UserService userService;
    final NotificationService notificationService;

    // class constructor :
	public MidnightApplicationRefresh(CarService carService, UserService userService, NotificationService notificationService) {
		this.carService = carService;
		this.userService = userService;
		this.notificationService = notificationService;
	}

	// class methods :
    public void midnightApplicationRefresher() { //Removes overdue reservations and notifications.

        for (Car car : carService.findAll()) {
            if (car.getEndReservationDate() != null) {
                if (car.getEndReservationDate().compareTo(LocalDate.now()) < 0) {
                    if (car.getReservedByUser() != null) {
                        User user = car.getReservedByUser();
                        car.setReservedByUser(null);
                        userService.save(user);
                    }
                    car.setStartReservationDate(null);
                    car.setEndReservationDate(null);
                    car.setReadyForPickup(false);
                    carService.save(car);
                }
            }
        }

        for (Notification notification : notificationService.findAll()) {
            if (notification.getValidUntilDate() != null) {
                if (notification.getValidUntilDate().compareTo(LocalDate.now()) < 0) {
                    notificationService.deleteById(notification.getNotificationId());
                }
            }
        }
    }
}
