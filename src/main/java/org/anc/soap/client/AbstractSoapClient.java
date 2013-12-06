package org.anc.soap.client;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

/**
 * The AbstractSoapClient manages the Axis Service and Call objects 
 * and invokes methods on the remote service.
 * <p>
 * Typically users will extend the AbstractSoapClient to provide
 * Java methods that invoke the service methods.
 * <pre>
 * class CustomSoapClient extends AbstractSoapClient {
 *    public CustomSoapClient() {
 *       super(SERVICE_NAMESPACE, ENDPOINT_URL);
 *    }
 *    
 *    String serviceMethod(String arg1, String arg2) {
 *       Object[] serviceArgs = new String[] { arg1, arg2 };
 *       return (String) super.invoke("serviceMethod", args);
 *    }
 *    ...
 * }
 * </pre>
 * @author Keith Suderman
 *
 */
public abstract class AbstractSoapClient
{
   protected String namespace;
   protected String endpoint;

   protected Service service;
   protected Call call;
   
   public AbstractSoapClient() throws ServiceException
   {
      service = new Service();
      call = (Call) service.createCall();
   }
   
   public AbstractSoapClient(String namespace) throws ServiceException
   {
      this();
      this.namespace = namespace;
   }
   
   public AbstractSoapClient(String namespace, String endpoint) throws ServiceException
   {
      this();
      this.namespace = namespace;
      this.endpoint = endpoint;
      call.setTargetEndpointAddress(endpoint);
   }
   
   public void setNamespace(String namespace)
   {
      this.namespace = namespace;
   }
   
   public void setEndpoint(String endpoint)
   {
      call.setTargetEndpointAddress(endpoint);      
   }
   
   public void setCredentials(String username, String password)
   {
      call.setUsername(username);
      call.setPassword(password);
   }

   public String getNamespace() { return namespace; }
   public String getEndpoint() { return endpoint; }

   /**
    * Invokes a method on a remote service.
    * 
    * @param method The name of the method to be invoked on the
    * remote service.
    * @return the object returned from the remote method.
    * @throws RemoteException
    */
   public Object invoke(String method) throws RemoteException
   {
      return invoke(method, new Object[0]);
   }
   
   /**
    * Invokes a method on a remote service.
    * 
    * @param method The name of the method to be invoked on the
    * remote service.
    * @param args Any parameters that should be passed to the
    * remote method.
    * @return the object returned from the remote method.
    * @throws RemoteException
    */
   public Object invoke(String method, Object[] args) throws RemoteException
   {
      call.setOperationName(new QName(namespace, method));
      return call.invoke(args);
   }
}