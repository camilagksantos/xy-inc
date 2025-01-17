package pt.xy_inc.xyAPI.business.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.xy_inc.xyAPI.business.model.POI;
import pt.xy_inc.xyAPI.repository.POIRepository;
import pt.xy_inc.xyAPI.repository.entity.POIEntity;
import pt.xy_inc.xyAPI.repository.mapper.POIRepositoryMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PoiServiceTest {

    @Mock
    private POIRepository poiRepository;

    @Mock
    private POIRepositoryMapper poiRepositoryMapper;

    @InjectMocks
    private POIService poiService;

    @Test
    void shouldGetAllPois(){
        //Arrannge
        List<POIEntity> poiEntityList = Arrays.asList(new POIEntity(), new POIEntity());
        List<POI> expectedPoiList = Arrays.asList(new POI(), new POI());

        when(poiRepository.findAll()).thenReturn(poiEntityList);
        when(poiRepositoryMapper.toPoiList(poiEntityList)).thenReturn(expectedPoiList);

        // Act
        List<POI> result = poiService.getAllPois();

        // Assert
        assertEquals(expectedPoiList, result);
        verify(poiRepository).findAll();
        verify(poiRepositoryMapper).toPoiList(poiEntityList);
    }

    @Test
    void shouldCreatePoi(){
        //Arrange
        POI poi = new POI(); //Obj que vou receber
        poi.setNomePoi("Padaria");
        poi.setCoordenadaX(20);
        poi.setCoordenadaY(10);

        POIEntity poiEntity = new POIEntity(); //Obj convertido pelo mapper para entity
        poiEntity.setNomePoi("Padaria");
        poiEntity.setCoordenadaX(20);
        poiEntity.setCoordenadaY(10);

        POIEntity savedPoiEntity = new POIEntity(); //Obj retornado apos a chamada do save
        savedPoiEntity.setId(1L);
        savedPoiEntity.setNomePoi("Padaria");
        savedPoiEntity.setCoordenadaX(20);
        savedPoiEntity.setCoordenadaY(10);

        POI expectedPoi = new POI(); //Obj convertido novamente para POI
        expectedPoi.setId(1L);
        expectedPoi.setNomePoi("Padaria");
        expectedPoi.setCoordenadaX(20);
        expectedPoi.setCoordenadaY(10);

        when(poiRepositoryMapper.toPOIEntity(poi)).thenReturn(poiEntity); //quando chamar o metodo para converter poi retornar poiEntity
        when(poiRepository.save(poiEntity)).thenReturn(savedPoiEntity); //quando salvar poiEntity retornar savedPoiEntity
        when(poiRepositoryMapper.toPoi(savedPoiEntity)).thenReturn(expectedPoi); //quando chamar o metodo para converter para poi o obj savedPoiENtity retornar expectedPoi

        //Act
        POI result = poiService.createPoi(poi);

        //Assert
        assertEquals(expectedPoi, result);
        verify(poiRepositoryMapper).toPOIEntity(poi);
        verify(poiRepository).save(poiEntity);
        verify(poiRepositoryMapper).toPoi(savedPoiEntity);
    }

    @Test
    void shouldGetPoiByCoordenada(){
        Integer coordenadaX = 20;
        Integer coordenadaY = 10;
        Integer dMax = 10;

        POIEntity poiEntity1 = new POIEntity();
        poiEntity1.setId(1L);
        poiEntity1.setNomePoi("Padaria");
        poiEntity1.setCoordenadaX(22);
        poiEntity1.setCoordenadaY(15);
        POIEntity poiEntity2 = new POIEntity();
        poiEntity2.setId(2L);
        poiEntity2.setNomePoi("Supermercado");
        poiEntity2.setCoordenadaX(40);
        poiEntity2.setCoordenadaY(15);
        POIEntity poiEntity3 = new POIEntity();
        poiEntity3.setId(3L);
        poiEntity3.setNomePoi("Loja de Roupas");
        poiEntity3.setCoordenadaX(32);
        poiEntity3.setCoordenadaY(17);

        POI expectedPoi1 = new POI();
        expectedPoi1.setId(1L);
        expectedPoi1.setNomePoi("Padaria");
        expectedPoi1.setCoordenadaX(22);
        expectedPoi1.setCoordenadaY(15);

        List<POIEntity> poisEntityByCoordenate = Arrays.asList(poiEntity1);

        when(poiRepository.findAll()).thenReturn(Arrays.asList(poiEntity1, poiEntity2, poiEntity3));
        when(poiRepositoryMapper.toPoiList(poisEntityByCoordenate)).thenReturn(Arrays.asList(expectedPoi1));

        //Act
        List<POI> poisByCoordenates = poiService.getPoisByCoordinates(coordenadaX, coordenadaY, dMax);

        //Assert
        assertEquals(expectedPoi1, poisByCoordenates.get(0));
        verify(poiRepositoryMapper).toPoiList(poisEntityByCoordenate);
        verify(poiRepository).findAll();
    }
}
