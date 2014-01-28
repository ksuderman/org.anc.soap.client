package org.anc.soap.client;

import javax.xml.rpc.ServiceException;

/**
 * Provides a concrete implementation of the AbstractSoapClient in case this is
 * all the user really wants or needs.
 *
 * @author Keith Suderman
 */
public class SoapClient extends AbstractSoapClient
{
   public SoapClient() throws ServiceException
   {
      super();
   }

   public SoapClient(String namespace) throws ServiceException
   {
      super(namespace);
   }

   public SoapClient(String namespace, String endpoint) throws ServiceException
   {
      super(namespace, endpoint);
   }
}
