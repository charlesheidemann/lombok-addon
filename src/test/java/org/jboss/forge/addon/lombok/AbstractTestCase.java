package org.jboss.forge.addon.lombok;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.forge.arquillian.AddonDependencies;
import org.jboss.forge.arquillian.AddonDependency;
import org.jboss.forge.arquillian.archive.AddonArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.FileAsset;

public abstract class AbstractTestCase
{
   @Deployment
   @AddonDependencies({
            @AddonDependency(name = "org.jboss.forge.furnace.container:cdi"),
            @AddonDependency(name = "org.jboss.forge.addon:ui-test-harness"),
            @AddonDependency(name = "org.jboss.forge.addon:shell-test-harness"),
            @AddonDependency(name = "org.jboss.forge.addon:parser-java"),
            @AddonDependency(name = "org.jboss.forge.addon:resources"),
            @AddonDependency(name = "org.jboss.forge.addon:facets"),
            @AddonDependency(name = "org.jboss.forge.addon:maven")
   })
   public static AddonArchive getDeployment()
   {

      return ShrinkWrap.create(AddonArchive.class).addPackages(true, AbstractTestCase.class.getPackage())
               .add(new FileAsset(new File(
                        "src/test/resources/org/jboss/forge/addon/lombok/Person.java")),
                        "org/jboss/forge/addon/lombok/Person.java")
               .addBeansXML();
   }
}