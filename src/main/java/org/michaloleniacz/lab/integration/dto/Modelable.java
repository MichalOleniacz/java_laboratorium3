package org.michaloleniacz.lab.integration.dto;

@FunctionalInterface
public interface Modelable<T> {
    T toModel();
}
