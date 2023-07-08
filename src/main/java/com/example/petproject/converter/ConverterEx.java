package com.example.petproject.converter;

// TODO: 08.07.2023 Ex? я бы предложил не упираться в айди, а сделать интерфейс пошире,
// чтобы можно было любую доп. инфу в виде объекта докидывать
public interface ConverterEx<S, T> extends Converter<S, T> {
    T convert (S source, long id);
}
