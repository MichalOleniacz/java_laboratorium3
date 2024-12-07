package org.michaloleniacz.lab.integration.controller;

import lombok.RequiredArgsConstructor;
import org.michaloleniacz.lab.domain.model.Shape;
import org.michaloleniacz.lab.domain.service.ShapeService;
import org.michaloleniacz.lab.integration.dto.RectangleDTO;
import org.michaloleniacz.lab.integration.dto.ShapeDTO;
import org.michaloleniacz.lab.integration.dto.TriangleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/shape")
public class ShapeController {

    @Autowired
    private final ShapeService shapeService;

    @GetMapping("/{id}")
    public ResponseEntity<Shape> getShapeById(@PathVariable Long id) {
        final Optional<Shape> retrievedShape = shapeService.getShapeById(id);
        return retrievedShape
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity saveShape(@RequestBody ShapeDTO shapeDto) {
        final Optional<Shape> savedShape = shapeService.addShape(shapeDto);
        return savedShape
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/rectangle/add")
    public ResponseEntity saveRectangle(@RequestBody RectangleDTO rectangleDto) {
        final Optional<Shape> savedShape = shapeService.addRectangle(rectangleDto);
        return savedShape
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/triangle/add")
    public ResponseEntity saveTriangle(@RequestBody TriangleDTO triangleDto) {
        final Optional<Shape> savedShape = shapeService.addTriangle(triangleDto);
        return savedShape
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
    }
}
