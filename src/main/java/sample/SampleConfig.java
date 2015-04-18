package sample;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import com.google.inject.Guice;
import com.google.inject.servlet.RequestScoped;
import com.google.inject.servlet.ServletModule;

@ApplicationPath("/")
public class SampleConfig extends ResourceConfig {

    @Inject
    public SampleConfig(ServiceLocator serviceLocator) {
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge guiceBridge = serviceLocator
                .getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(Guice
                .createInjector(new ServletModule() {
                    @Override
                    protected void configureServlets() {
                        bind(SampleResource.class).in(RequestScoped.class);
                        bind(SampleFilter.class).in(Singleton.class);
                        bind(RequestObj.class).in(RequestScoped.class);
                    }
                }));
    }
}