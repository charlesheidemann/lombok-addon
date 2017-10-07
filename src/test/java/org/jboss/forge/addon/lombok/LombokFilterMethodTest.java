package org.jboss.forge.addon.lombok;

import static org.jboss.forge.addon.lombok.LombokFilterMethod.isGetterSetterMethod;
import static org.jboss.forge.addon.lombok.LombokFilterMethod.isObjectCommonMethod;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.forge.addon.parser.java.resources.JavaResource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class LombokFilterMethodTest extends AbstractTestCase
{

   @Inject
   private JavaResource source;

   @Test
   public void testEnableEntity() throws Exception
   {
      JavaClassSource javaSource = source.getJavaType();
      assertTrue(isGetterSetterMethod(javaSource.getField("name")).test(javaSource.getMethod("getName")));
      assertTrue(isGetterSetterMethod(javaSource.getField("active")).test(javaSource.getMethod("isActive")));
      assertTrue(isObjectCommonMethod().test(javaSource.getMethod("toString")));
      assertTrue(isObjectCommonMethod().test(javaSource.getMethod("hashCode")));
   }

}