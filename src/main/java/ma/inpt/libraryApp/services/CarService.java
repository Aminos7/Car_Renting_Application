package ma.inpt.libraryApp.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import ma.inpt.libraryApp.DAO.CarRepository;
import ma.inpt.libraryApp.DAO.UserRepository;
import ma.inpt.libraryApp.entities.Car;
import ma.inpt.libraryApp.entities.User;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    // class attributes :
    final CarRepository carRepository;
    final UserRepository usRepo;

    // class constructor :
    public CarService(CarRepository carRepository, UserRepository usRepo) {
        this.carRepository = carRepository;
        this.usRepo = usRepo;
    }

    // class methods :
    public void save(Car car) {
        carRepository.save(car);
    }

    public void saveById(Long carId) {
        carRepository.save(carRepository.findById(carId).get());
    }

    public List<Car> findAll() {
        return (List<Car>) carRepository.findAll();
    }

    public Car findById(long carId) {
        return carRepository.findById(carId).get();
    }

    public List<Car> searchCars(String title, String author) {

        List<Car> searchedCars = new ArrayList<Car>();

        if (title != null && author == null) {
            searchedCars = getByTitle(title);
        } else if (title == null && author != null) {
            searchedCars = getByAuthor(author);
        } else if (title != null) {
            searchedCars = getByTitleAndAuthor(title, author);
        }
        return searchedCars;
    }

    public List<Car> getByTitle(String title) {
        List<Car> cars = new ArrayList<>();
        for (Car car : carRepository.findAll()) {
            if (car.getTitle().toLowerCase().contains(title.toLowerCase())) {
                cars.add(car);
            }
        }
        return cars;
    }

    public List<Car> getByAuthor(String author) {
        List<Car> cars = new ArrayList<>();
        for (Car car : carRepository.findAll()) {
            if (car.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                cars.add(car);
            }
        }
        return cars;
    }

    public List<Car> getByTitleAndAuthor(String title, String author) {
        List<Car> cars = new ArrayList<>();
        for (Car car : carRepository.findAll()) {
            if (car.getTitle().toLowerCase().contains(title.toLowerCase()) &&
                    car.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                cars.add(car);
            }
        }
        return cars;
    }

    public void deleteById(long carId) {
        carRepository.deleteById(carId);
    }

    public List<Car> getUnprocessedCarReservations() {
        List<Car> unprocessedCarReservations = new ArrayList<Car>();
        for (Car car : carRepository.findAll()) {
            if (car.getReservedByUser() != null && !car.getReadyForPickUp()) {
                unprocessedCarReservations.add(car);
            }
        }
        return unprocessedCarReservations;
    }

    public List<Car> getProcessedCarReservations() {
        List<Car> processedCarReservations = new ArrayList<Car>();
        for (Car car : carRepository.findAll()) {
            if (car.getReservedByUser() != null && car.getReadyForPickUp()) {
                processedCarReservations.add(car);
            }
        }
        return processedCarReservations;
    }

    public List<Car> convertIdsCollectionToCarsList(Collection<Long> carIds) {
        List<Car> cars = new ArrayList<Car>();
        for (Long carId : carIds) cars.add(carRepository.findById(carId).get());
        return cars;
    }

    public void removeCurrentUserOfMultipleCars(List<Car> cars) {
        for (Car car : cars) removeCurrentUserOfCar(car);
    }

    public void removeCurrentUserOfCar(Car car) {
        User currentUser = car.getTheUser();
        for (int i = 0; i < currentUser.getCars().size(); i++) {
            if (currentUser.getCars().get(i).getCarId() == car.getCarId()) {
                currentUser.getCars().remove(i);
                break;
            }
        }
        usRepo.save(currentUser);
        car.setTheUser(null);
        car.setReturnDate(null);
        car.setTimesExtended(0);
        carRepository.save(car);
    }

    public void removeReservation(Car car) {
        User reservedByUser = car.getReservedByUser();
        for (int i = 0; i < reservedByUser.getReversedCars().size(); i++) {
            if (reservedByUser.getReversedCars().get(i).getCarId() == car.getCarId()) {
                reservedByUser.getReversedCars().remove(i);
                break;
            }
        }
        usRepo.save(reservedByUser);
        car.setStartReservationDate(null);
        car.setEndReservationDate(null);
        car.setReadyForPickup(false);
        carRepository.save(car);
    }

    public void saveCarOrder(Collection<Long> selectedCarIds, User user) {
        for (Long carId : selectedCarIds) {
            Car car = carRepository.findById(carId).get();
            car.setReturnDate(LocalDate.now().plusDays(20));
            car.setStartReservationDate(null);
            car.setEndReservationDate(null);
            car.setReservedByUser(null);
            
            car.setReadyForPickup(false);
            car.setTheUser(user);
            carRepository.save(car);
            usRepo.save(user);
        }
    }
}
