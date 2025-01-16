package pt.xy_inc.xyAPI.business.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pt.xy_inc.xyAPI.business.model.POI;
import pt.xy_inc.xyAPI.repository.POIRepository;
import pt.xy_inc.xyAPI.repository.entity.POIEntity;
import pt.xy_inc.xyAPI.repository.mapper.POIRepositoryMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class POIService {

    private final POIRepository poiRepository;
    private final POIRepositoryMapper poiRepositoryMapper;

    public POIService(POIRepository poiRepository, POIRepositoryMapper poiRepositoryMapper) {
        this.poiRepository = poiRepository;
        this.poiRepositoryMapper = poiRepositoryMapper;
    }

    public List<POI> getAllPois() {
        List<POIEntity> pois = poiRepository.findAll();
        return poiRepositoryMapper.toPoiList(pois);
    }

    public POI createPoi(POI poi) {
        POIEntity poiEntity = poiRepositoryMapper.toPOIEntity(poi);
        POIEntity savedPoi = poiRepository.save(poiEntity);

        return poiRepositoryMapper.toPoi(savedPoi);
    }

    public List<POI> getPoisByCoordinates(Integer coordenadaX, Integer coordenadaY, Integer dmax) {
        List<POIEntity> pois = poiRepository.findAll();
        List<POIEntity> poisByCoordinates = new ArrayList<POIEntity>();

        for (POIEntity poi : pois) {
            if ( (Math.abs(poi.getCoordenadaX() - coordenadaX) + Math.abs(poi.getCoordenadaY() - coordenadaY)) <= dmax) {
                poisByCoordinates.add(poi);
            }
        }

        return poiRepositoryMapper.toPoiList(poisByCoordinates);
    }
}
