package org.jboss.seam.classic.init.el;

import javax.el.ArrayELResolver;
import javax.el.BeanELResolver;
import javax.el.CompositeELResolver;
import javax.el.ELResolver;
import javax.el.ListELResolver;
import javax.el.MapELResolver;
import javax.el.ResourceBundleELResolver;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.BeanManager;

import org.jboss.seam.classic.runtime.outjection.OutjectedReferenceElResolver;
import org.jboss.solder.el.Composite;
import org.jboss.solder.el.ELResolverProducer;
import org.jboss.solder.el.Resolver;

/**
 * Modifier version of {@link ElResolverProducer}. This version places plugin resolver into the beginning of the resolver chain.
 * 
 * @author <a href="http://community.jboss.org/people/jharting">Jozef Hartinger</a>
 * 
 */
public class ElResolverProducer extends ELResolverProducer {

    @Produces
    @Composite
    @ApplicationScoped
    public ELResolver getELResolver(@Resolver Instance<ELResolver> resolvers, BeanManager beanManager, OutjectedReferenceElResolver classicResolver) {
        // Create the default el resolvers
        CompositeELResolver compositeResolver = new CompositeELResolver();

        // Add plugin resolvers
        for (ELResolver resolver : resolvers) {
            compositeResolver.add(resolver);
        }

        compositeResolver.add(classicResolver);
//        compositeResolver.add(beanManager.getELResolver());
        compositeResolver.add(new MapELResolver());
        compositeResolver.add(new ListELResolver());
        compositeResolver.add(new ArrayELResolver());
        compositeResolver.add(new ResourceBundleELResolver());
        compositeResolver.add(new BeanELResolver());

        return compositeResolver;
    }
}