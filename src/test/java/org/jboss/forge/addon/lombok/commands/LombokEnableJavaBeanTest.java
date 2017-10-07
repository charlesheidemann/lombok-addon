package org.jboss.forge.addon.lombok.commands;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.forge.addon.lombok.AbstractTestCase;
import org.jboss.forge.addon.lombok.builders.LombokEntityBuilder;
import org.jboss.forge.addon.parser.java.resources.JavaResource;
import org.jboss.forge.addon.projects.Project;
import org.jboss.forge.addon.ui.controller.CommandController;
import org.jboss.forge.addon.ui.metadata.UICommandMetadata;
import org.jboss.forge.addon.ui.result.Failed;
import org.jboss.forge.addon.ui.result.Result;
import org.jboss.forge.addon.ui.test.UITestHarness;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class LombokEnableJavaBeanTest extends AbstractTestCase
{

   @Inject
   private UITestHarness uiTestHarness;

   @Inject
   private Project project;

   @Inject
   private JavaResource source;

   @Test
   public void testEnableEntity() throws Exception
   {
      try (CommandController controller = uiTestHarness.createCommandController(LombokEnableJavaBean.class,
               project.getRoot()))
      {
         controller.initialize();
         assertTrue(controller.getCommand() instanceof LombokEnableJavaBean);
         UICommandMetadata metadata = controller.getMetadata();
         assertThat(metadata.getName(), allOf(notNullValue(), containsString("Lombok: Enable JavaBean")));
         assertThat(controller.getInputs().size(), is(1));
         controller.setValueFor("lombokEntity", source);
         Result result = controller.execute();
         assertThat(result, not(instanceOf(Failed.class)));
         JavaResource sourceResult = (JavaResource) controller.getValueFor("lombokEntity");
         assertTrue(sourceResult.getJavaType().hasAnnotation(LombokEntityBuilder.DATA_ANNOTATION));
      }
   }
}
