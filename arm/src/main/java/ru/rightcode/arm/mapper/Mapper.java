package ru.rightcode.arm.mapper;

import java.security.Principal;

public interface Mapper<F, T> {

    T map(F object);
}
