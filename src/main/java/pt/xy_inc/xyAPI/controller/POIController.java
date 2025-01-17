package pt.xy_inc.xyAPI.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pt.xy_inc.xyAPI.business.model.POI;
import pt.xy_inc.xyAPI.business.service.POIService;
import pt.xy_inc.xyAPI.controller.dtoRequest.POIRequestDTO;
import pt.xy_inc.xyAPI.controller.dtoResponse.POIResponseDTO;
import pt.xy_inc.xyAPI.controller.mapper.POIControllerMapper;

import java.util.List;

@RestController
@RequestMapping("/pois")
public class POIController {

    private final POIService poiService;
    private final POIControllerMapper poiControllerMapper;

    public POIController(POIService poiService, POIControllerMapper poiControllerMapper) {
        this.poiService = poiService;
        this.poiControllerMapper = poiControllerMapper;
    }

    @GetMapping
    public List<POIResponseDTO> getAllPois() {
        return poiControllerMapper.toPOIResponseDTOList(poiService.getAllPois());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public POIResponseDTO createPoi(@RequestBody @Valid POIRequestDTO poiRequestDTO) {
        POI poi = poiControllerMapper.toPOI(poiRequestDTO);
        POI createdPoi = poiService.createPoi(poi);

        return poiControllerMapper.toPOIResponseDTO(createdPoi);
    }

    @GetMapping("/{coordenadaX}/{coordenadaY}/{dmax}")
    public List<POIResponseDTO> getPoisByCoordinates(@PathVariable("coordenadaX") Integer coordenadaX, @PathVariable("coordenadaY") Integer coordenadaY, @PathVariable("dmax") Integer dMax) {
        List<POI> poisByCoordinates = poiService.getPoisByCoordinates(coordenadaX, coordenadaY, dMax);

        return poiControllerMapper.toPOIResponseDTOList(poisByCoordinates);
    }
}

