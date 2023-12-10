package org.example.async;

import java.util.concurrent.Future;

public interface AsyncService {

    void asyncTask();

    Future<String> asyncWithReturn(String name);
}
