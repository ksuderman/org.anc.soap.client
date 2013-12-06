package org.anc.soap.client;

import javax.xml.rpc.ServiceException;

public class HelloService extends AbstractSoapClient
{

   public HelloService() throws ServiceException
   {
      super("");
      super.setEndpoint("");
      super.setCredentials("operator1", "operator1");
   }

}
