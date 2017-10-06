package org.jboss.forge.addon.lombok.commands;

import org.jboss.forge.addon.ui.command.AbstractUICommand;
import org.jboss.forge.addon.ui.context.UIBuilder;
import org.jboss.forge.addon.ui.context.UIContext;
import org.jboss.forge.addon.ui.context.UIExecutionContext;
import org.jboss.forge.addon.ui.metadata.UICommandMetadata;
import org.jboss.forge.addon.ui.util.Metadata;
import org.jboss.forge.addon.ui.util.Categories;
import org.jboss.forge.addon.ui.result.Result;
import org.jboss.forge.addon.ui.result.Results;

public class LombokEnableEntity extends AbstractUICommand
{

   @Override
   public UICommandMetadata getMetadata(UIContext context)
   {
      return Metadata.forCommand(LombokEnableEntity.class)
               .name("Lombok: Enable Entity")
               .category(Categories.create("Lombok"));
   }

   @Override
   public void initializeUI(UIBuilder builder) throws Exception
   {
   }

   @Override
   public Result execute(UIExecutionContext context) throws Exception
   {
      return Results
               .success("Command 'Lombok: Enable Entity' successfully executed!");
   }
}