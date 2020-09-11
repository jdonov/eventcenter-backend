package event_center.ec.repository;

import event_center.ec.model.entity.Event;
import event_center.ec.model.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, String> {
    List<Guest> findAllByEvent(Event event);
}
