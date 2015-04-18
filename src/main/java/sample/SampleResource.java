package sample;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("sample")
public class SampleResource {

    private static final Logger logger = Logger.getLogger(SampleResource.class
            .getName());

    @Inject
    private RequestObj ro;

    @GET
    public String get() {

        logger.info(() -> "resource method - " + ro);

        // フィルタでセットした値を取り出している。
        LocalDateTime dateTime = ro.value;

        return dateTime.toString();
    }
}
