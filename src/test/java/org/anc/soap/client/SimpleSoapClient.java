package org.anc.soap.client;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

public class SimpleSoapClient
{
   
   public void run() throws ServiceException, RemoteException
   {
      ProviderService provider = new ProviderService();
      TranslatorService translator = new TranslatorService();
      
      System.out.println("Soap clients created.");
      String data = provider.getData();
      System.out.println("Service returned: " + data);      
      System.out.println("Translating");
      String[] parts = data.split(" ");
      for (String part : parts)
      {
         System.out.println(part + " -> " + translator.translate(part));
      }
   }
   
   /**
    * @param args
    */
   public static void main(String[] args)
   {
      try
      {
         new SimpleSoapClient().run();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

}

class ProviderService extends AbstractSoapClient
{
   private final String endpoint = "http://picard:8080/service_manager/invoker/lapps:FOOBAR-SERVICE";
   
   public ProviderService() throws ServiceException
   {
      super("servicegrid:servicetype:DATA:PROVIDER");
      super.setEndpoint(endpoint);
      super.setCredentials("Keith", "Suderman");
   }
   
   public String getData() throws RemoteException
   {
      return (String) super.invoke("getData");
   }
}

class TranslatorService extends AbstractSoapClient
{
   final static String NAMESPACE = "servicegrid:servicetype:PROC:TRANSLATOR";
   final static String ENDPOINT = "http://picard:8080/service_manager/invoker/lapps:FB-TRANSLATE";
   
   public TranslatorService() throws ServiceException
   {
      super(NAMESPACE);
      super.setEndpoint(ENDPOINT);
      super.setCredentials("Keith", "Suderman");
   }
   
   public String translate(String input) throws RemoteException
   {
      return (String) this.invoke("translate", new String[] { input });
   }
}

