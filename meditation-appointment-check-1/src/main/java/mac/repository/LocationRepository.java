package mac.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import mac.domains.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location , Integer> {

}
