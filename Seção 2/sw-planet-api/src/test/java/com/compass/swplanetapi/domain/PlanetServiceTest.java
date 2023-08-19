package com.compass.swplanetapi.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static com.compass.swplanetapi.common.PlanetConstants.INVALID_PLANET;
import static com.compass.swplanetapi.common.PlanetConstants.PLANET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
// @SpringBootTest(classes = PlanetService.class)
public class PlanetServiceTest {
    // @Autowired this annotation is for springboottest
    @InjectMocks
    private PlanetService planetService;

    // @MockBean this annotation is for springboottest
    @Mock
    private PlanetRepository planetRepository;

    // operacao_estado_return
    @Test
    public void createPlanet_WithValidData_ReturnsPlanet() {
        // AAA
        // Arrange
        when(planetRepository.save(PLANET)).thenReturn(PLANET);

        // Act
        // system under test
        Planet sut = planetService.create(PLANET);

        // Assert
        assertThat(sut).isEqualTo(PLANET);
    }

    @Test
    public void createPlanet_WithInvalidData_ThrowsException() {
        when(planetRepository.save(INVALID_PLANET)).thenThrow(RuntimeException.class);
        assertThatThrownBy(() -> planetService.create(INVALID_PLANET)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void getPlanet_ByExistingId_ReturnsPlanet() {
        when(planetRepository.findById(anyLong())).thenReturn(java.util.Optional.of(PLANET));

        Optional<Planet> sut = planetService.get(1L);

        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(PLANET);
    }

    @Test
    public void getPlanet_ByNonExistingId_ReturnsEmpty() {
        when(planetRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Planet> sut = planetService.get(1L);

        assertThat(sut).isEmpty();
    }
}
