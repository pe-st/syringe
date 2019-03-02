package ch.schlau.pesche.syringe.junit5;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

public class SyringeInitCdiSe implements TestInstancePostProcessor {

    private static final String STORE_KEY_CONTAINER = "container";

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {

        SeContainerInitializer containerInit = SeContainerInitializer.newInstance();
        SeContainer container = containerInit.initialize();

        getStore(context).put(STORE_KEY_CONTAINER, container);
    }

    private ExtensionContext.Store getStore(ExtensionContext context) {
        ExtensionContext root = context.getRoot();
        return root.getStore(ExtensionContext.Namespace.create(getClass(), root));
    }
}
