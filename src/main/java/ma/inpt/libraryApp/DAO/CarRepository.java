package ma.inpt.libraryApp.DAO;

import ma.inpt.libraryApp.entities.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

}
