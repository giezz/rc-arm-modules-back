package ru.rightcode.anketi.mapper;

public interface Mapper<F, T> {

    T toEntity(F object);
    F toDto(T object);
}
