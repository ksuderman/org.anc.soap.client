package org.anc.soap.client;

public class LocalException extends Exception
{
   private static final long serialVersionUID = 1L;

   public LocalException()
   {
      super();
   }

   public LocalException(String message, Throwable cause)
   {
      super(message, cause);
   }

   public LocalException(String message)
   {
      super(message);
   }

   public LocalException(Throwable cause)
   {
      super(cause);
   }

   
}
