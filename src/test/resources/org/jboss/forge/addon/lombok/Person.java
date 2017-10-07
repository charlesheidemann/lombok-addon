package org.jboss.forge.addon.lombok.bean;

public class Person
{

   private String name;

   private Long id;

   private boolean active;

   public Long getIdShouldBeIgnore()
   {
      return id;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public boolean isActive()
   {
      return active;
   }

   public void setActive(boolean active)
   {
      this.active = active;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (name != null && !name.trim().isEmpty())
         result += "name: " + name;
      if (id != null)
         result += ", id: " + id;
      result += ", active: " + active;
      return result;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof Person))
      {
         return false;
      }
      Person other = (Person) obj;
      if (name != null)
      {
         if (!name.equals(other.name))
         {
            return false;
         }
      }
      if (id != null)
      {
         if (!id.equals(other.id))
         {
            return false;
         }
      }
      if (active != other.active)
      {
         return false;
      }
      return true;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((name == null) ? 0 : name.hashCode());
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + (active ? 1231 : 1237);
      return result;
   }
}