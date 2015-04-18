package sample;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class SampleFilter implements ContainerRequestFilter {

    private static final Logger logger = Logger.getLogger(SampleFilter.class
            .getName());

    // SampleFilterがSingletonなため通常はインジェクションできない。
    // Providerをインジェクションすることで実際のインスタンス取得を
    // Provider.get()まで遅延している。
    // 単純名がjavax.ws.rs.ext.Providerと重複するのでFQCNを書く必要がある。。。
    @Inject
    private javax.inject.Provider<RequestObj> requestObj;

    @Override
    public void filter(ContainerRequestContext requestContext)
            throws IOException {

        // Provider.get()でリクエストスコープからインスタンスを取得している。
        RequestObj ro = requestObj.get();
        ro.value = LocalDateTime.now();

        logger.info(() -> "filter - " + ro);
    }
}
