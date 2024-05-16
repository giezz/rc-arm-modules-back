package ru.rightcode.medcart.dto;

import lombok.Value;

@Value
public class MedCartResponse<T> {
    String message;
    T response;
}
