package pt.xy_inc.xyAPI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.xy_inc.xyAPI.business.service.POIService;
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
}
