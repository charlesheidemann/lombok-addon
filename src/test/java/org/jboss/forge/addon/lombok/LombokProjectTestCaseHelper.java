package org.jboss.forge.addon.lombok;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javax.enterprise.inject.Produces;

import org.jboss.forge.addon.maven.projects.MavenFacet;
import org.jboss.forge.addon.parser.java.facets.JavaSourceFacet;
import org.jboss.forge.addon.parser.java.resources.JavaResource;
import org.jboss.forge.addon.projects.Project;
import org.jboss.forge.addon.projects.ProjectFactory;
import org.jboss.forge.addon.resource.Resource;
import org.jboss.forge.addon.resource.ResourceFactory;

public class LombokProjectTestCaseHelper
{

   @Produces
   public Project createProject(final ProjectFactory projectFactory) throws Exception
   {
      return projectFactory.createTempProject(Arrays.asList(MavenFacet.class, JavaSourceFacet.class));
   }

   @Produces
   public JavaResource createJavaResource(final Project project, final ResourceFactory resourceFactory)
            throws IOException
   {

      File tmpFile = File.createTempFile("Person", ".java");
      tmpFile.deleteOnExit();
      Resource<File> unreified = resourceFactory.create(JavaResource.class, tmpFile);
      JavaResource resource = unreified.reify(JavaResource.class);

      try (InputStream openStream = getClass().getResource("Person.java").openStream())
      {
         resource.setContents(openStream);
      }

      return resource;
   }
}
