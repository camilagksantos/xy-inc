package pt.xy_inc.xyAPI.repository.mapper;

import org.mapstruct.Mapper;
import pt.xy_inc.xyAPI.business.model.POI;
import pt.xy_inc.xyAPI.repository.entity.POIEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface POIRepositoryMapper {
    POI toPoi(POIEntity poiEntity);
    POIEntity toPOIEntity(POI poi);

    List<POIEntity> toPOIEntityList(List<POI> pois);
    List<POI> toPoiList(List<POIEntity> pois);
}
