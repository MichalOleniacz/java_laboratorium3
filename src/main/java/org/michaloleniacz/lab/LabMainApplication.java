package org.michaloleniacz.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application entry point
 */
@SpringBootApplication
@SuppressWarnings({"PublicMethodWithoutLogging"})
public class LabMainApplication {

    public static final String HIBERNATE_CFG_PATH = "hibernate.cfg.xml";

    /**
     * Application entry point
     * @param args arg list
     */
    public static void main(final String[] args) {
//        HibernateUtil.initialize(HIBERNATE_CFG_PATH);
//
//        ShapeDAO shapeDAO = new ShapeDAO();
//
//        for(int i = 1; i <= 100; i++) {
//            Shape shape;
//            if (i % 2 == 0) shape = new Rectangle(i, i, Color.RED);
//            else shape = new Triangle(i, i, i, Color.BLUE);
//            shapeDAO.save(shape);
//            ShapeDescriber.describe(shapeDAO.findById(shape.getId()));
//            shapeDAO.delete(shape);
//        }
        SpringApplication.run(LabMainApplication.class, args);
    }
}
