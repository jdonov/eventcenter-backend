package event_center.ec.repository;

import event_center.ec.model.entity.Event;
import event_center.ec.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
    List<Event> findAllByPublic(boolean isPublic);
    List<Event> findAllByCreator(User user);
}
