package pt.xy_inc.xyAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.xy_inc.xyAPI.repository.entity.POIEntity;

@Repository
public interface POIRepository extends JpaRepository<POIEntity, Long> {
}
