package eurekaClient.connnector;

import io.reactivex.Observable;

public interface Connector {
    String failure();

    String success();

    String ignoreException();

    Observable<String> methodWhichReturnsAStream();
}
