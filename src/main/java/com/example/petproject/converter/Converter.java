package com.example.petproject.converter;

import java.util.List;

public interface Converter<S, T> {

    T convert (S source);

    List<T> convert (List<S> source);
}
