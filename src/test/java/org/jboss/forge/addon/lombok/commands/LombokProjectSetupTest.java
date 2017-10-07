package org.jboss.forge.addon.lombok.commands;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.forge.addon.lombok.AbstractTestCase;
import org.jboss.forge.addon.lombok.facets.LombokProjectFacet;
import org.jboss.forge.addon.projects.Project;
import org.jboss.forge.addon.shell.test.ShellTest;
import org.jboss.forge.addon.ui.controller.CommandController;
import org.jboss.forge.addon.ui.metadata.UICommandMetadata;
import org.jboss.forge.addon.ui.result.Failed;
import org.jboss.forge.addon.ui.result.Result;
import org.jboss.forge.addon.ui.test.UITestHarness;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class LombokProjectSetupTest extends AbstractTestCase
{

   @Inject
   private UITestHarness uiTestHarness;

   @Inject
   private ShellTest shellTest;

   @Inject
   private Project project;

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
         assertTrue(project.hasFacet(LombokProjectFacet.class));
      }
   }

   @Test
   public void checkProjectShellSetupCommand() throws Exception
   {
      shellTest.getShell().setCurrentResource(project.getRoot());
      Result result = shellTest.execute(("lombok-project-setup"), 10, TimeUnit.SECONDS);
      assertThat(result, not(instanceOf(Failed.class)));
      assertTrue(project.hasFacet(LombokProjectFacet.class));
   }
}