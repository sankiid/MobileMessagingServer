package com.mobile.repo;

import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by sandeep on 16/2/17.
 */
public class CacheRepositoryServiceImpl implements IRepositoryService {

    private volatile static CacheRepositoryServiceImpl service = null;

    private static ConcurrentMap<String, Socket> cache = new ConcurrentHashMap<String, Socket>();

    public static CacheRepositoryServiceImpl getService() {
        if (service == null) {
            synchronized (CacheRepositoryServiceImpl.class) {
                if (service == null) {
                    service = new CacheRepositoryServiceImpl();
                }
            }
        }
        return service;
    }

    public Socket getConnection(String deviceId) {
        return cache.get(deviceId);
    }

    public void setConnection(String deviceId, Socket socket) {
        cache.putIfAbsent(deviceId, socket);
    }

}
