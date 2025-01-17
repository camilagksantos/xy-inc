package pt.xy_inc.xyAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pt.xy_inc.xyAPI.repository.entity.POIEntity;

import java.util.List;

@Repository
public interface POIRepository extends JpaRepository<POIEntity, Long> {

    @Query(value = "SELECT * FROM poi p WHERE ABS(p.coordenadax - ?1) + ABS(p.coordenaday - ?2) <= ?3", nativeQuery = true)
    List<POIEntity> findPoisWithinDistance(Integer coordenadaX, Integer coordenadaY, Integer dmax);
}
