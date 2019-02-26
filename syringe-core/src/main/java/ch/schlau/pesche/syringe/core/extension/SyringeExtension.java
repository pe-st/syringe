package ch.schlau.pesche.syringe.core.extension;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.AfterDeploymentValidation;
import javax.enterprise.inject.spi.AfterTypeDiscovery;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.BeforeShutdown;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.inject.spi.ProcessBean;
import javax.enterprise.inject.spi.ProcessBeanAttributes;
import javax.enterprise.inject.spi.ProcessInjectionPoint;
import javax.enterprise.inject.spi.ProcessInjectionTarget;
import javax.enterprise.inject.spi.ProcessObserverMethod;
import javax.enterprise.inject.spi.ProcessProducer;

public class SyringeExtension implements Extension {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(SyringeExtension.class);

    // events in the order specified in https://docs.jboss.org/cdi/spec/2.0/cdi-spec.html#initialization

    public void beforeBeanDiscoveryEvent(@Observes BeforeBeanDiscovery beforeBeanDiscovery) {
        // WELD logging:
        // DEBUG org.jboss.weld.BootstrapTracker - START bootstrap > startInitialization > BeforeBeanDiscovery
        logger.trace("BeforeBeanDiscovery");
    }

    public void processAnnotatedTypeEvent(@Observes ProcessAnnotatedType processAnnotatedType) {
        // not logged by WELD
        logger.info("Type discovery: {}", processAnnotatedType.getAnnotatedType());
    }

    public void afterTypeDiscoveryEvent(@Observes AfterTypeDiscovery afterTypeDiscovery) {
        // WELD logging:
        // DEBUG org.jboss.weld.BootstrapTracker - START bootstrap > startInitialization > AfterTypeDiscovery
        logger.trace("AfterTypeDiscovery");
    }

    public void processInjectionPointEvent(@Observes ProcessInjectionPoint processInjectionPoint) {
        // not logged by WELD
        logger.info("Bean discovery; injection point {}", processInjectionPoint.getInjectionPoint());
    }

    public void processInjectionTargetEvent(@Observes ProcessInjectionTarget processInjectionTarget) {
        // usually just logged before ProcessBeanAttributes
        logger.info("Bean discovery; injection target {}", processInjectionTarget.getInjectionTarget());
    }

    public void processProducerEvent(@Observes ProcessProducer processProducer) {
        logger.info("Bean discovery; producer {}", processProducer.getProducer());
    }

    public void processObserverMethodEvent(@Observes ProcessObserverMethod processObserverMethod) {
        logger.info("Bean discovery; observer {}", processObserverMethod.getObserverMethod());
    }

    public void processBeanAttributesEvent(@Observes ProcessBeanAttributes processBeanAttributes) {
        // indirectly logged by Weld ProbeExtension (development mode):
        // DEBUG org.jboss.weld.Bootstrap - WELD-000149: ProcessBeanAttributes.setBeanAttributes() called by org.jboss.weld.probe.ProbeExtension@b5942961:
        //       Managed Bean [class X] with qualifiers [@Any @Default] replaced by Managed Bean [class X] with qualifiers [@Any @Default]
        // DEBUG org.jboss.weld.probe.Probe - PROBE-000009: @MonitoredComponent stereotype added to [BackedAnnotatedType] @Dependent class X
        logger.debug("Bean discovery; bean attributes {}", processBeanAttributes.getBeanAttributes());
    }

    public void processBeanEvent(@Observes ProcessBean processBean) {
        // WELD logging depending on type:
        // DEBUG org.jboss.weld.Bootstrap - WELD-000106: Bean: x
        // DEBUG org.jboss.weld.Bootstrap - WELD-000107: Interceptor: x
        logger.trace("Bean discovery; bean {}", processBean.getBean());
    }

    public void afterBeanDiscoveryEvent(@Observes AfterBeanDiscovery afterBeanDiscovery) {
        // WELD logging:
        // DEBUG org.jboss.weld.BootstrapTracker - START bootstrap > deployBeans > AfterBeanDiscovery
        logger.trace("AfterBeanDiscovery");
    }

    public void afterDeploymentValidationEvent(@Observes AfterDeploymentValidation afterDeploymentValidation) {
        logger.info("AfterDeploymentValidation");
    }

    public void beforeShutdownEvent(@Observes BeforeShutdown beforeShutdown) {
        logger.info("BeforeShutdown");
    }
}
