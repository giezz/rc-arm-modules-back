package ru.rightcode.arm.mapper;

public interface Mapper<F, T> {

    T map(F object);
}
