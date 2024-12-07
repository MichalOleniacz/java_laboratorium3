package org.michaloleniacz.lab.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.michaloleniacz.lab.domain.model.Shape;
import org.michaloleniacz.lab.integration.dto.RectangleDTO;
import org.michaloleniacz.lab.integration.dto.ShapeDTO;
import org.michaloleniacz.lab.integration.dto.TriangleDTO;
import org.michaloleniacz.lab.integration.repository.ShapeRepository;
import org.michaloleniacz.lab.util.ShapeDescriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShapeService {

    @Autowired
    private final ShapeRepository shapeRepository;

    public Optional<Shape> getShapeById(Long shapeId) {
        try {
            final Shape retrievedShape = shapeRepository.getShapeById(shapeId);
            ShapeDescriber.describe(retrievedShape);
            log.debug(String.valueOf(retrievedShape.getClass()));
            return Optional.of(retrievedShape);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Shape> addShape(ShapeDTO shapeDto) {
        try {
            Shape parsedShape = shapeDto.toModel();
            Shape saved = shapeRepository.save(parsedShape);
            ShapeDescriber.describe(saved);
            return Optional.ofNullable(saved);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Shape> addRectangle(RectangleDTO rectangleDto) {
        try {
            Shape parsedShape = rectangleDto.toModel();
            Shape saved = shapeRepository.save(parsedShape);
            ShapeDescriber.describe(saved);
            return Optional.ofNullable(saved);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Shape> addTriangle(TriangleDTO triangleDTO) {
        try {
            Shape parsedShape = triangleDTO.toModel();
            Shape saved = shapeRepository.save(parsedShape);
            ShapeDescriber.describe(saved);
            return Optional.ofNullable(saved);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
