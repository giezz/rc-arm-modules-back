package ru.rightcode.arm.utils;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class PageableUtils {

    public <E> Page<E> getPageableResult(String name, Supplier<Page<E>> supplier1, Supplier<Page<E>> supplier2) {
        Page<E> page;
        if (name == null || name.isEmpty()) {
            page = supplier1.get();
        } else {
            page = supplier2.get();
        }

        return page;
    }
}
