package org.jboss.forge.addon.lombok.commands;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.forge.addon.lombok.AbstractTestCase;
import org.jboss.forge.addon.maven.projects.MavenFacet;
import org.jboss.forge.addon.projects.Project;
import org.jboss.forge.addon.projects.ProjectFactory;
import org.jboss.forge.addon.shell.test.ShellTest;
import org.jboss.forge.addon.ui.controller.CommandController;
import org.jboss.forge.addon.ui.metadata.UICommandMetadata;
import org.jboss.forge.addon.ui.result.Failed;
import org.jboss.forge.addon.ui.result.Result;
import org.jboss.forge.addon.ui.test.UITestHarness;
import org.jboss.forge.arquillian.AddonDependencies;
import org.jboss.forge.arquillian.AddonDependency;
import org.jboss.forge.arquillian.archive.AddonArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class LombokProjectSetupTest
{

   @Inject
   private ProjectFactory projectFactory;

   @Inject
   private UITestHarness uiTestHarness;

   @Inject
   private ShellTest shellTest;

   private Project project;

   @Deployment
   @AddonDependencies({
            @AddonDependency(name = "org.jboss.forge.addon:ui-test-harness"),
            @AddonDependency(name = "org.jboss.forge.addon:shell-test-harness"),
            @AddonDependency(name = "org.jboss.forge.furnace.container:cdi"),
            @AddonDependency(name = "org.jboss.forge.addon:maven")
   })
   public static AddonArchive getDeployment()
   {
      return ShrinkWrap.create(AddonArchive.class).addPackages(true, AbstractTestCase.class.getPackage()).addBeansXML();
   }

   @Before
   public void init() throws Exception
   {
      project = projectFactory.createTempProject(Arrays.asList(MavenFacet.class));
   }

   @Test
   public void checkProjectUISetupCommand() throws Exception
   {
      try (CommandController controller = uiTestHarness.createCommandController(LombokProjectSetup.class,
               project.getRoot()))
      {
         controller.initialize();
         assertTrue(controller.getCommand() instanceof LombokProjectSetup);
         UICommandMetadata metadata = controller.getMetadata();
         assertThat(metadata.getName(), allOf(notNullValue(), containsString("Lombok: Project Setup")));
         assertThat(controller.getInputs().size(), is(0));
         Result result = controller.execute();
         assertThat(result, not(instanceOf(Failed.class)));
      }
   }

   @Test
   public void checkProjectShellSetupCommand() throws Exception
   {
      shellTest.getShell().setCurrentResource(project.getRoot());
      Result result = shellTest.execute(("lombok-project-setup"), 15, TimeUnit.SECONDS);
      assertThat(result, not(instanceOf(Failed.class)));
   }
}