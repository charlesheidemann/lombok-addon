package org.jboss.forge.addon.lombok.commands;

import javax.inject.Inject;

import org.jboss.forge.addon.facets.FacetFactory;
import org.jboss.forge.addon.facets.constraints.FacetConstraint;
import org.jboss.forge.addon.lombok.facets.LombokProjectFacet;
import org.jboss.forge.addon.projects.Project;
import org.jboss.forge.addon.projects.facets.DependencyFacet;
import org.jboss.forge.addon.ui.context.UIBuilder;
import org.jboss.forge.addon.ui.context.UIContext;
import org.jboss.forge.addon.ui.context.UIExecutionContext;
import org.jboss.forge.addon.ui.metadata.UICommandMetadata;
import org.jboss.forge.addon.ui.result.Result;
import org.jboss.forge.addon.ui.result.Results;
import org.jboss.forge.addon.ui.util.Metadata;

/**
 * Lombok: Project Setup Command
 */
@FacetConstraint(DependencyFacet.class)
public class LombokProjectSetup extends AbstractLombokProjectCommand
{

   @Inject
   private FacetFactory facetFactory;

   @Override
   public UICommandMetadata getMetadata(UIContext context)
   {
      return Metadata.from(super.getMetadata(context), getClass()).name("Lombok: Project Setup")
               .description("Setup Lombok in your project");
   }

   @Override
   public void initializeUI(UIBuilder builder) throws Exception
   {
   }

   @Override
   public Result execute(UIExecutionContext context) throws Exception
   {
      Project project = getSelectedProject(context);
      if (!project.hasFacet(LombokProjectFacet.class))
      {
         facetFactory.install(project, LombokProjectFacet.class);
      }
      return Results.success("Command 'Lombok: Project Setup' successfully executed!");
   }

}