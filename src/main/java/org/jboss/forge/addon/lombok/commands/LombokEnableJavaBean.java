package org.jboss.forge.addon.lombok.commands;

import java.util.Optional;

import javax.inject.Inject;

import org.jboss.forge.addon.facets.constraints.FacetConstraint;
import org.jboss.forge.addon.lombok.builders.LombokEntityBuilder;
import org.jboss.forge.addon.lombok.facets.LombokProjectFacet;
import org.jboss.forge.addon.parser.java.facets.JavaSourceFacet;
import org.jboss.forge.addon.parser.java.resources.JavaResource;
import org.jboss.forge.addon.ui.context.UIBuilder;
import org.jboss.forge.addon.ui.context.UIContext;
import org.jboss.forge.addon.ui.context.UIExecutionContext;
import org.jboss.forge.addon.ui.facets.HintsFacet;
import org.jboss.forge.addon.ui.hints.InputType;
import org.jboss.forge.addon.ui.input.UIInput;
import org.jboss.forge.addon.ui.metadata.UICommandMetadata;
import org.jboss.forge.addon.ui.metadata.WithAttributes;
import org.jboss.forge.addon.ui.result.Result;
import org.jboss.forge.addon.ui.result.Results;
import org.jboss.forge.addon.ui.util.Metadata;

/**
 * Lombok: Enable JavaBean Command
 */
@FacetConstraint(LombokProjectFacet.class)
public class LombokEnableJavaBean extends AbstractLombokProjectCommand
{

   @Inject
   @WithAttributes(label = "Entity to enable Lombok", required = true)
   private UIInput<JavaResource> lombokEntity;

   @Override
   public UICommandMetadata getMetadata(UIContext context)
   {
      return Metadata.from(super.getMetadata(context), getClass()).name("Lombok: Enable JavaBean")
               .description("Enable lombok generator in your JavaBean");
   }

   @Override
   public void initializeUI(UIBuilder builder) throws Exception
   {

      lombokEntity.getFacet(HintsFacet.class).setInputType(InputType.JAVA_CLASS_PICKER);

      Optional.ofNullable(builder.getUIContext().getInitialSelection().get()).ifPresent(entity -> {
         if (entity instanceof JavaResource)
         {
            lombokEntity.setDefaultValue((JavaResource) entity);
         }
      });

      builder.add(lombokEntity);
   }

   @Override
   public Result execute(UIExecutionContext context) throws Exception
   {
      LombokEntityBuilder lombokEntityBuilder = LombokEntityBuilder.create(lombokEntity.getValue().getJavaType());
      getSelectedProject(context).getFacet(JavaSourceFacet.class)
               .saveJavaSource(lombokEntityBuilder.addDataAnnotation().build());
      return Results.success("Command 'Lombok: Enable entity' successfully executed!");
   }

}