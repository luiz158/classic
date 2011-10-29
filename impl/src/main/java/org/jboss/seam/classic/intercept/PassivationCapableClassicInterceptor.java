package org.jboss.seam.classic.intercept;

import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.PassivationCapable;

/**
 * Passivation capable alternative to {@link ClassicInterceptor}
 * 
 * @author <a href="http://community.jboss.org/people/jharting">Jozef Hartinger</a>
 */
public class PassivationCapableClassicInterceptor<T> extends ClassicInterceptor<T> implements PassivationCapable {

    public PassivationCapableClassicInterceptor(AnnotatedType<T> interceptorType, BeanManager manager) {
        super(interceptorType, manager);
    }

    @Override
    public String getId() {
        return getBeanClass().getName();
    }

}