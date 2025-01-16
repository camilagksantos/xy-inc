package pt.xy_inc.xyAPI.controller.mapper;

import org.mapstruct.Mapper;
import pt.xy_inc.xyAPI.business.model.POI;
import pt.xy_inc.xyAPI.controller.dtoRequest.POIRequestDTO;
import pt.xy_inc.xyAPI.controller.dtoResponse.POIResponseDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface POIControllerMapper {
    POI toPOI(POIRequestDTO poiRequestDTO);
    POIResponseDTO toPOIResponseDTO(POI poi);

    List<POI> toPOIList(List<POIRequestDTO> poiRequestDTOList);
    List<POIResponseDTO> toPOIResponseDTOList(List<POI> poiList);
}
