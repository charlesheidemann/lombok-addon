package org.jboss.forge.addon.lombok;

import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.jboss.forge.roaster.model.Field;
import org.jboss.forge.roaster.model.Method;

/**
 * Predicate to discover methods that should be removed from JavaBean
 */
public interface LombokFilterMethod
{

   public static Predicate<Method<?, ?>> isObjectCommonMethod()
   {
      return isEquals().or(isHashCode()).or(isToString());
   }

   public static Predicate<Method<?, ?>> isGetterSetterMethod(final Field<?> field)
   {
      return isGetterFromField(field).or(isSetterFromField(field));
   }

   public static Predicate<Method<?, ?>> isGetterFromField(final Field<?> field)
   {
      return p -> p.getName().matches("(get|is)" + StringUtils.capitalize(field.getName()));
   }

   public static Predicate<Method<?, ?>> isSetterFromField(final Field<?> field)
   {
      return p -> p.getName().matches("set" + StringUtils.capitalize(field.getName()));
   }

   public static Predicate<Method<?, ?>> isEquals()
   {
      return p -> p.getName().equals("equals");
   }

   public static Predicate<Method<?, ?>> isHashCode()
   {
      return p -> p.getName().equals("hashCode");
   }

   public static Predicate<Method<?, ?>> isToString()
   {
      return p -> p.getName().equals("toString");
   }
}
