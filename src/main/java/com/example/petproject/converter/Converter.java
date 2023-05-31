package com.example.petproject.converter;

import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

public interface Converter<S, T> {

    T convert (S source);

    List<T> convert (List<S> source);

//    Collection<T> convert(Collection<S> source);
//
//    Page<T> convert(Page<S> source);
}
