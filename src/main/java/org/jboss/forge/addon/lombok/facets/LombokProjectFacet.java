package org.jboss.forge.addon.lombok.facets;

import java.util.List;

import org.jboss.forge.addon.dependencies.Dependency;
import org.jboss.forge.addon.dependencies.builder.DependencyBuilder;
import org.jboss.forge.addon.facets.AbstractFacet;
import org.jboss.forge.addon.facets.constraints.FacetConstraint;
import org.jboss.forge.addon.projects.Project;
import org.jboss.forge.addon.projects.ProjectFacet;
import org.jboss.forge.addon.projects.facets.DependencyFacet;

/**
 * Facet to supports Lombok
 */
@FacetConstraint(DependencyFacet.class)
public class LombokProjectFacet extends AbstractFacet<Project> implements ProjectFacet
{

   public static final Dependency POM_DEPENDENCY = DependencyBuilder.create()
            .setGroupId("org.projectlombok")
            .setArtifactId("lombok")
            .setVersion("1.16.16")
            .setPackaging("jar")
            .setScopeType("provided");

   @Override
   public boolean install()
   {
      addLombokDependency();
      return isInstalled();
   }

   @Override
   public boolean isInstalled()
   {
      DependencyFacet dependencyFacet = getFaceted().getFacet(DependencyFacet.class);
      List<Dependency> dependencies = dependencyFacet.getDependencies();
      return hasDependency(POM_DEPENDENCY, dependencies);
   }

   private boolean hasDependency(Dependency dependency, List<Dependency> dependencies)
   {
      return dependencies.stream().anyMatch(dep -> dep.equals(dependency));
   }

   private void addLombokDependency()
   {
      DependencyFacet dependencyFacet = getFaceted().getFacet(DependencyFacet.class);
      dependencyFacet.addDirectManagedDependency(POM_DEPENDENCY);
      dependencyFacet.addDirectDependency(POM_DEPENDENCY);
   }

}