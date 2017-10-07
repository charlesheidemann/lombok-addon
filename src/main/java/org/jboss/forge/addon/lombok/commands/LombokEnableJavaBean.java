package org.jboss.forge.addon.lombok.commands;

import org.jboss.forge.addon.ui.context.UIBuilder;
import org.jboss.forge.addon.ui.context.UIContext;
import org.jboss.forge.addon.ui.context.UIExecutionContext;
import org.jboss.forge.addon.ui.metadata.UICommandMetadata;
import org.jboss.forge.addon.ui.result.Result;
import org.jboss.forge.addon.ui.result.Results;
import org.jboss.forge.addon.ui.util.Metadata;

/**
 * Lombok: Enable JavaBean Command
 */
public class LombokEnableJavaBean extends AbstractLombokProjectCommand
{

   @Override
   public UICommandMetadata getMetadata(UIContext context)
   {
      return Metadata.from(super.getMetadata(context), getClass()).name("Lombok: Enable JavaBean")
               .description("Enable lombok generator in your JavaBean");
   }

   @Override
   public void initializeUI(UIBuilder builder) throws Exception
   {
   }

   @Override
   public Result execute(UIExecutionContext context) throws Exception
   {
      return Results
               .success("Command 'Lombok: Enable JavaBean' successfully executed!");
   }
}