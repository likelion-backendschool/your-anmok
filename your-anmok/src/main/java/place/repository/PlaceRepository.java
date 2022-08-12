package place.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import place.entity.Place;

@Repository
public interface PlaceRepository  extends JpaRepository<Place, Integer> {
}
