package com.example.oauth.converters;

public interface ProviderUserConverter<T,R> {
    R converter(T t);
}
