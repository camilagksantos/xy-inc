package pt.xy_inc.xyAPI.business.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.xy_inc.xyAPI.business.exceptions.NegativeNumberException;
import pt.xy_inc.xyAPI.business.model.POI;
import pt.xy_inc.xyAPI.repository.POIRepository;
import pt.xy_inc.xyAPI.repository.entity.POIEntity;
import pt.xy_inc.xyAPI.repository.mapper.POIRepositoryMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
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
        // Given
        POIEntity poiEntity = new POIEntity();
        poiEntity.setId(1L);
        poiEntity.setNomePoi("Test POI");
        poiEntity.setCoordenadaX(10);
        poiEntity.setCoordenadaY(10);

        POI expectedPoi = new POI();
        expectedPoi.setId(1L);
        expectedPoi.setNomePoi("Test POI");
        expectedPoi.setCoordenadaX(10);
        expectedPoi.setCoordenadaY(10);

        // When
        when(poiRepository.findPoisWithinDistance(anyInt(), anyInt(), anyInt()))
                .thenReturn(List.of(poiEntity));
        when(poiRepositoryMapper.toPoiList(List.of(poiEntity)))
                .thenReturn(List.of(expectedPoi));

        // Then
        List<POI> result = poiService.getPoisByCoordinates(20, 10, 10);
        assertEquals(1, result.size());
        assertEquals(expectedPoi, result.get(0));
    }

    @Test
    void shouldThrowExceptionWhenCoordinatesAreNegative() {
        assertThrows(NegativeNumberException.class, () ->
                poiService.getPoisByCoordinates(-1, 10, 10));

        assertThrows(NegativeNumberException.class, () ->
                poiService.getPoisByCoordinates(10, -1, 10));

        assertThrows(NegativeNumberException.class, () ->
                poiService.getPoisByCoordinates(10, 10, -1));
    }
}
