package com.dl.rediscache.service;

public interface CacheLoadable<T> {
    T load();
}
