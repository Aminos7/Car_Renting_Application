package ma.inpt.libraryApp.DAO;

import ma.inpt.libraryApp.entities.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {

}
