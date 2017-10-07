package org.jboss.forge.addon.lombok.builders;

import org.jboss.forge.addon.lombok.LombokFilterMethod;
import org.jboss.forge.roaster.model.source.JavaClassSource;

/**
 * A Builder to adjust java source according of Lombok
 */
public class LombokEntityBuilder
{

   public final static String DATA_ANNOTATION = "lombok.Data";

   private final JavaClassSource source;

   private LombokEntityBuilder(final JavaClassSource source)
   {
      this.source = source;
   }

   /**
    * Obtain a new {@link LombokEntityBuilder} instance.
    */
   public static LombokEntityBuilder create(final JavaClassSource source)
   {
      return new LombokEntityBuilder(source);
   }

   public JavaClassSource build() throws Exception
   {
      return this.source;
   }

   public LombokEntityBuilder addDataAnnotation()
   {
      if (!this.source.hasAnnotation(DATA_ANNOTATION))
      {
         this.source.addAnnotation(DATA_ANNOTATION);
      }
      clearObjectCommonMethods();
      clearGetterSetterMethods();
      return this;
   }

   private void clearObjectCommonMethods()
   {
      this.source.getMethods().stream().filter(LombokFilterMethod.isObjectCommonMethod())
               .forEach(method -> source.removeMethod(method));
   }

   private void clearGetterSetterMethods()
   {
      this.source.getFields().stream().forEach(field -> {
         this.source.getMethods().stream().filter(LombokFilterMethod.isGetterSetterMethod(field))
                  .forEach(method -> this.source.removeMethod(method));
      });
   }
}
