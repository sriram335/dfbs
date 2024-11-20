// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 10/31/2016 10:32:51 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 

package ai.kwikekard.checkout.service2.client;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.encoding.SerializerFactory;
import org.apache.axis.AxisFault;
import org.apache.axis.NoEndPointException;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.client.Stub;
import org.apache.axis.constants.Style;
import org.apache.axis.constants.Use;
import org.apache.axis.description.FaultDesc;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;
import org.apache.axis.encoding.DeserializerFactory;
import org.apache.axis.encoding.ser.ArrayDeserializerFactory;
import org.apache.axis.encoding.ser.ArraySerializerFactory;
import org.apache.axis.soap.SOAPConstants;
import org.apache.axis.utils.JavaUtils;

// Referenced classes of package ai.kwikekard.checkout.service2.client:
//            GetException, CreateException, CompleteException, OrderInfo, 
//            CheckoutService2_PortType

public class CheckoutService2SoapBindingStub extends Stub
    implements CheckoutService2_PortType
{

    private static void _initOperationDesc1()
    {
        OperationDesc operationdesc = new OperationDesc();
        operationdesc.setName("isAuthorized");
        ParameterDesc parameterdesc = new ParameterDesc(new QName("", "orderId"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        operationdesc.addParameter(parameterdesc);
        operationdesc.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        operationdesc.setReturnClass(Boolean.TYPE);
        operationdesc.setReturnQName(new QName("", "isAuthorizedReturn"));
        operationdesc.setStyle(Style.RPC);
        operationdesc.setUse(Use.ENCODED);
        operationdesc.addFault(new FaultDesc(new QName("https://secure.IN.gov/apps/kwikekard/checkout/services/CheckoutService2", "fault"), "ai.kwikekard.checkout.service2.client.GetException", new QName("http://service2.checkout.kwikekard.ai", "GetException"), true));
        _operations[0] = operationdesc;
        operationdesc = new OperationDesc();
        operationdesc.setName("createOrder");
        parameterdesc = new ParameterDesc(new QName("", "orderInfo"), (byte)1, new QName("http://service2.checkout.kwikekard.ai", "OrderInfo"), ai.kwikekard.checkout.service2.client.OrderInfo.class, false, false);
        operationdesc.addParameter(parameterdesc);
        operationdesc.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        operationdesc.setReturnClass(java.lang.String.class);
        operationdesc.setReturnQName(new QName("", "createOrderReturn"));
        operationdesc.setStyle(Style.RPC);
        operationdesc.setUse(Use.ENCODED);
        operationdesc.addFault(new FaultDesc(new QName("https://secure.IN.gov/apps/kwikekard/checkout/services/CheckoutService2", "fault"), "ai.kwikekard.checkout.service2.client.CreateException", new QName("http://service2.checkout.kwikekard.ai", "CreateException"), true));
        _operations[1] = operationdesc;
        operationdesc = new OperationDesc();
        operationdesc.setName("completeOrder");
        parameterdesc = new ParameterDesc(new QName("", "orderId"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        operationdesc.addParameter(parameterdesc);
        parameterdesc = new ParameterDesc(new QName("", "receiptPage"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        operationdesc.addParameter(parameterdesc);
        operationdesc.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
        operationdesc.setReturnClass(Integer.TYPE);
        operationdesc.setReturnQName(new QName("", "completeOrderReturn"));
        operationdesc.setStyle(Style.RPC);
        operationdesc.setUse(Use.ENCODED);
        operationdesc.addFault(new FaultDesc(new QName("https://secure.IN.gov/apps/kwikekard/checkout/services/CheckoutService2", "fault"), "ai.kwikekard.checkout.service2.client.CompleteException", new QName("http://service2.checkout.kwikekard.ai", "CompleteException"), true));
        _operations[2] = operationdesc;
        operationdesc = new OperationDesc();
        operationdesc.setName("getOrderInfo");
        parameterdesc = new ParameterDesc(new QName("", "orderId"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        operationdesc.addParameter(parameterdesc);
        operationdesc.setReturnType(new QName("http://service2.checkout.kwikekard.ai", "OrderInfo"));
        operationdesc.setReturnClass(ai.kwikekard.checkout.service2.client.OrderInfo.class);
        operationdesc.setReturnQName(new QName("", "getOrderInfoReturn"));
        operationdesc.setStyle(Style.RPC);
        operationdesc.setUse(Use.ENCODED);
        operationdesc.addFault(new FaultDesc(new QName("https://secure.IN.gov/apps/kwikekard/checkout/services/CheckoutService2", "fault"), "ai.kwikekard.checkout.service2.client.GetException", new QName("http://service2.checkout.kwikekard.ai", "GetException"), true));
        _operations[3] = operationdesc;
    }

    public CheckoutService2SoapBindingStub()
        throws AxisFault
    {
        this(null);
    }

    public CheckoutService2SoapBindingStub(URL url, javax.xml.rpc.Service service)
        throws AxisFault
    {
        this(service);
        super.cachedEndpoint = url;
    }

    public CheckoutService2SoapBindingStub(javax.xml.rpc.Service service)
        throws AxisFault
    {
        cachedSerClasses = new Vector();
        cachedSerQNames = new Vector();
        cachedSerFactories = new Vector();
        cachedDeserFactories = new Vector();
        if(service == null)
            super.service = new Service();
        else
            super.service = service;
        ((Service)super.service).setTypeMappingVersion("1.2");
        Class class2 = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
        Class class3 = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
        Class class4 = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
        Class class5 = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
        Class class6 = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
        Class class7 = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
        Class class8 = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
        Class class9 = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
        Class class10 = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
        Class class11 = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
        QName qname = new QName("http://service2.checkout.kwikekard.ai", "AccountHolderInfo");
        cachedSerQNames.add(qname);
        Class class1 = ai.kwikekard.checkout.service2.client.AccountHolderInfo.class;
        cachedSerClasses.add(class1);
        cachedSerFactories.add(class2);
        cachedDeserFactories.add(class3);
        qname = new QName("http://service2.checkout.kwikekard.ai", "AccountInfo");
        cachedSerQNames.add(qname);
        class1 = ai.kwikekard.checkout.service2.client.AccountInfo.class;
        cachedSerClasses.add(class1);
        cachedSerFactories.add(class2);
        cachedDeserFactories.add(class3);
        qname = new QName("http://service2.checkout.kwikekard.ai", "Address");
        cachedSerQNames.add(qname);
        class1 = ai.kwikekard.checkout.service2.client.Address.class;
        cachedSerClasses.add(class1);
        cachedSerFactories.add(class2);
        cachedDeserFactories.add(class3);
        qname = new QName("http://service2.checkout.kwikekard.ai", "BankAccountInfo");
        cachedSerQNames.add(qname);
        class1 = ai.kwikekard.checkout.service2.client.BankAccountInfo.class;
        cachedSerClasses.add(class1);
        cachedSerFactories.add(class2);
        cachedDeserFactories.add(class3);
        qname = new QName("http://service2.checkout.kwikekard.ai", "CompleteException");
        cachedSerQNames.add(qname);
        class1 = ai.kwikekard.checkout.service2.client.CompleteException.class;
        cachedSerClasses.add(class1);
        cachedSerFactories.add(class2);
        cachedDeserFactories.add(class3);
        qname = new QName("http://service2.checkout.kwikekard.ai", "CreateException");
        cachedSerQNames.add(qname);
        class1 = ai.kwikekard.checkout.service2.client.CreateException.class;
        cachedSerClasses.add(class1);
        cachedSerFactories.add(class2);
        cachedDeserFactories.add(class3);
        qname = new QName("http://service2.checkout.kwikekard.ai", "CreditCardAccountInfo");
        cachedSerQNames.add(qname);
        class1 = ai.kwikekard.checkout.service2.client.CreditCardAccountInfo.class;
        cachedSerClasses.add(class1);
        cachedSerFactories.add(class2);
        cachedDeserFactories.add(class3);
        qname = new QName("http://service2.checkout.kwikekard.ai", "GetException");
        cachedSerQNames.add(qname);
        class1 = ai.kwikekard.checkout.service2.client.GetException.class;
        cachedSerClasses.add(class1);
        cachedSerFactories.add(class2);
        cachedDeserFactories.add(class3);
        qname = new QName("http://service2.checkout.kwikekard.ai", "ItemInfo");
        cachedSerQNames.add(qname);
        class1 = ai.kwikekard.checkout.service2.client.ItemInfo.class;
        cachedSerClasses.add(class1);
        cachedSerFactories.add(class2);
        cachedDeserFactories.add(class3);
        qname = new QName("http://service2.checkout.kwikekard.ai", "MapEntry");
        cachedSerQNames.add(qname);
        class1 = ai.kwikekard.checkout.service2.client.MapEntry.class;
        cachedSerClasses.add(class1);
        cachedSerFactories.add(class2);
        cachedDeserFactories.add(class3);
        qname = new QName("http://service2.checkout.kwikekard.ai", "OrderInfo");
        cachedSerQNames.add(qname);
        class1 = ai.kwikekard.checkout.service2.client.OrderInfo.class;
        cachedSerClasses.add(class1);
        cachedSerFactories.add(class2);
        cachedDeserFactories.add(class3);
        qname = new QName("http://service2.checkout.kwikekard.ai", "PersonInfo");
        cachedSerQNames.add(qname);
        class1 = ai.kwikekard.checkout.service2.client.PersonInfo.class;
        cachedSerClasses.add(class1);
        cachedSerFactories.add(class2);
        cachedDeserFactories.add(class3);
        qname = new QName("http://service2.checkout.kwikekard.ai", "PhoneNumber");
        cachedSerQNames.add(qname);
        class1 = ai.kwikekard.checkout.service2.client.PhoneNumber.class;
        cachedSerClasses.add(class1);
        cachedSerFactories.add(class2);
        cachedDeserFactories.add(class3);
        qname = new QName("http://service2.checkout.kwikekard.ai", "SubscriptionAccountInfo");
        cachedSerQNames.add(qname);
        class1 = ai.kwikekard.checkout.service2.client.SubscriptionAccountInfo.class;
        cachedSerClasses.add(class1);
        cachedSerFactories.add(class2);
        cachedDeserFactories.add(class3);
        qname = new QName("http://service2.checkout.kwikekard.ai", "TransactionInfo");
        cachedSerQNames.add(qname);
        class1 = ai.kwikekard.checkout.service2.client.TransactionInfo.class;
        cachedSerClasses.add(class1);
        cachedSerFactories.add(class2);
        cachedDeserFactories.add(class3);
        qname = new QName("https://secure.IN.gov/apps/kwikekard/checkout/services/CheckoutService2", "ArrayOf_tns1_ItemInfo");
        cachedSerQNames.add(qname);
        class1 = ai.kwikekard.checkout.service2.client.ItemInfo[].class;
        cachedSerClasses.add(class1);
        qname = new QName("http://service2.checkout.kwikekard.ai", "ItemInfo");
        QName qname1 = null;
        cachedSerFactories.add(new ArraySerializerFactory(qname, qname1));
        cachedDeserFactories.add(new ArrayDeserializerFactory());
        qname = new QName("https://secure.IN.gov/apps/kwikekard/checkout/services/CheckoutService2", "ArrayOf_tns1_MapEntry");
        cachedSerQNames.add(qname);
        class1 = ai.kwikekard.checkout.service2.client.MapEntry[].class;
        cachedSerClasses.add(class1);
        qname = new QName("http://service2.checkout.kwikekard.ai", "MapEntry");
        qname1 = null;
        cachedSerFactories.add(new ArraySerializerFactory(qname, qname1));
        cachedDeserFactories.add(new ArrayDeserializerFactory());
        qname = new QName("https://secure.IN.gov/apps/kwikekard/checkout/services/CheckoutService2", "ArrayOf_tns1_TransactionInfo");
        cachedSerQNames.add(qname);
        class1 = ai.kwikekard.checkout.service2.client.TransactionInfo[].class;
        cachedSerClasses.add(class1);
        qname = new QName("http://service2.checkout.kwikekard.ai", "TransactionInfo");
        qname1 = null;
        cachedSerFactories.add(new ArraySerializerFactory(qname, qname1));
        cachedDeserFactories.add(new ArrayDeserializerFactory());
    }

    protected Call createCall()
        throws RemoteException
    {
        Call call;
        try {
            call = super._createCall();
         if(super.maintainSessionSet)
            call.setMaintainSession(super.maintainSession);
        if(super.cachedUsername != null)
            call.setUsername(super.cachedUsername);
        if(super.cachedPassword != null)
            call.setPassword(super.cachedPassword);
        if(super.cachedEndpoint != null)
            call.setTargetEndpointAddress(super.cachedEndpoint);
        if(super.cachedTimeout != null)
            call.setTimeout(super.cachedTimeout);
        if(super.cachedPortName != null)
            call.setPortName(super.cachedPortName);
      
        String s;
        for(Enumeration enumeration = super.cachedProperties.keys(); enumeration.hasMoreElements(); call.setProperty(s, super.cachedProperties.get(s)))
            s = (String)enumeration.nextElement();

        synchronized(this)
        {
            if(firstCall())
            {
                call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
                call.setEncodingStyle("http://schemas.xmlsoap.org/soap/encoding/");
                for(int i = 0; i < cachedSerFactories.size(); i++)
                {
                    Class class1 = (Class)cachedSerClasses.get(i);
                    QName qname = (QName)cachedSerQNames.get(i);
                    Object obj = cachedSerFactories.get(i);
                    if(obj instanceof Class)
                    {
                        Class class2 = (Class)cachedSerFactories.get(i);
                        Class class3 = (Class)cachedDeserFactories.get(i);
                        call.registerTypeMapping(class1, qname, class2, class3, false);
                        continue;
                    }
                    if(obj instanceof SerializerFactory)
                    {
                        org.apache.axis.encoding.SerializerFactory serializerfactory = (org.apache.axis.encoding.SerializerFactory)cachedSerFactories.get(i);
                        DeserializerFactory deserializerfactory = (DeserializerFactory)cachedDeserFactories.get(i);
                        call.registerTypeMapping(class1, qname, serializerfactory, deserializerfactory, false);
                    }
                }

            }
        }
        return call;
        } catch (ServiceException e) {
            return null;
      /*    
       } catch (org.apache.axis.AxisFault axisFaultException) {
                if (axisFaultException.detail != null) {
                    if (axisFaultException.detail instanceof java.rmi.RemoteException) {
                          throw (java.rmi.RemoteException) axisFaultException.detail;
                     }
                }
        throw axisFaultException;
        */
        }
    }

    public boolean isAuthorized(String s)
        throws RemoteException, GetException
    {
        Call call;
        if(super.cachedEndpoint == null)
            throw new NoEndPointException();
        try{
        call = createCall();
        call.setOperation(_operations[0]);
        call.setUseSOAPAction(true);
        call.setSOAPActionURI("");
        call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
        call.setOperationName(new QName("https://secure.IN.gov/apps/kwikekard/checkout/services/CheckoutService2", "isAuthorized"));
        setRequestHeaders(call);
        setAttachments(call);
        Object obj;
        obj = call.invoke(new Object[] {
            s
        });
        if(obj instanceof RemoteException)
            throw (RemoteException)obj;
        extractAttachments(call);
            try{
             return ((Boolean)obj).booleanValue();
            }catch(java.lang.Exception exception){
             return ((Boolean)JavaUtils.convert(obj, Boolean.TYPE)).booleanValue();
             }
/*
        AxisFault axisfault =null;
        if (axisfault.detail != null)
        {
            if(axisfault.detail instanceof RemoteException)
                throw (RemoteException)axisfault.detail;
            if(axisfault.detail instanceof GetException)
                throw (GetException)axisfault.detail;
        }
        throw axisfault;
*/
        } catch (org.apache.axis.AxisFault axisFaultException) {
            if (axisFaultException.detail != null) {
                if (axisFaultException.detail instanceof java.rmi.RemoteException) {
                      throw (java.rmi.RemoteException) axisFaultException.detail;
                 }
                if (axisFaultException.detail instanceof GetException) {
                      throw (GetException) axisFaultException.detail;
                 }
            }
      
        throw axisFaultException;
        } 
        
    }

    public String createOrder(OrderInfo orderinfo)
        throws RemoteException, CreateException
    {
        Call call;
        if(super.cachedEndpoint == null)
            throw new NoEndPointException();
        try{
        call = createCall();
        call.setOperation(_operations[1]);
        call.setUseSOAPAction(true);
        call.setSOAPActionURI("");
        call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
        call.setOperationName(new QName("https://secure.IN.gov/apps/kwikekard/checkout/services/CheckoutService2", "createOrder"));
        setRequestHeaders(call);
        setAttachments(call);
        Object obj;
        obj = call.invoke(new Object[] {
            orderinfo
        });
        if(obj instanceof RemoteException)
            throw (RemoteException)obj;
        extractAttachments(call);
            try{
        return (String)obj;
            }catch(java.lang.Exception e){
        return (String)JavaUtils.convert(obj, java.lang.String.class);
            }
       /* AxisFault axisfault;
        if(axisfault.detail != null)
        {
            if(axisfault.detail instanceof RemoteException)
                throw (RemoteException)axisfault.detail;
            if(axisfault.detail instanceof CreateException)
                throw (CreateException)axisfault.detail;
        }
        throw axisfault;
        */
       } catch (org.apache.axis.AxisFault axisFaultException) {
           if (axisFaultException.detail != null) {
               if (axisFaultException.detail instanceof java.rmi.RemoteException) {
                     throw (java.rmi.RemoteException) axisFaultException.detail;
                }
               if (axisFaultException.detail instanceof CreateException) {
                     throw (CreateException) axisFaultException.detail;
                }
           }
       
       throw axisFaultException;
       }
    }

    public int completeOrder(String s, String s1)
        throws RemoteException, CompleteException
    {
        Call call;
        if(super.cachedEndpoint == null)
            throw new NoEndPointException();
        try{
        call = createCall();
        call.setOperation(_operations[2]);
        call.setUseSOAPAction(true);
        call.setSOAPActionURI("");
        call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
        call.setOperationName(new QName("https://secure.IN.gov/apps/kwikekard/checkout/services/CheckoutService2", "completeOrder"));
        setRequestHeaders(call);
        setAttachments(call);
        Object obj;
        obj = call.invoke(new Object[] {
            s, s1
        });
        if(obj instanceof RemoteException)
            throw (RemoteException)obj;
        extractAttachments(call);
            try{
        return ((Integer)obj).intValue();
            }catch(java.lang.Exception e){
        return ((Integer)JavaUtils.convert(obj, Integer.TYPE)).intValue();
            }
        /*AxisFault axisfault;
        if(axisfault.detail != null)
        {
            if(axisfault.detail instanceof RemoteException)
                throw (RemoteException)axisfault.detail;
            if(axisfault.detail instanceof CompleteException)
                throw (CompleteException)axisfault.detail;
        }
        throw axisfault;
      */
        } catch (org.apache.axis.AxisFault axisFaultException) {
            if (axisFaultException.detail != null) {
                if (axisFaultException.detail instanceof java.rmi.RemoteException) {
                      throw (java.rmi.RemoteException) axisFaultException.detail;
                 }
                if (axisFaultException.detail instanceof CompleteException) {
                      throw (CompleteException) axisFaultException.detail;
                 }
            }
        
        throw axisFaultException;
        }
    }

    public OrderInfo getOrderInfo(String s)
        throws RemoteException, GetException
    {
        Call call;
        if(super.cachedEndpoint == null)
            throw new NoEndPointException();
        try{
        call = createCall();
        call.setOperation(_operations[3]);
        call.setUseSOAPAction(true);
        call.setSOAPActionURI("");
        call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
        call.setOperationName(new QName("https://secure.IN.gov/apps/kwikekard/checkout/services/CheckoutService2", "getOrderInfo"));
        setRequestHeaders(call);
        setAttachments(call);
        Object obj;
        obj = call.invoke(new Object[] {
            s
        });
        if(obj instanceof RemoteException)
            throw (RemoteException)obj;
        extractAttachments(call);
            try{
        return (OrderInfo)obj;
            }catch(java.lang.Exception e){
        return (OrderInfo)JavaUtils.convert(obj, ai.kwikekard.checkout.service2.client.OrderInfo.class);
            }
      /*  AxisFault axisfault;
        if(axisfault.detail != null)
        {
            if(axisfault.detail instanceof RemoteException)
                throw (RemoteException)axisfault.detail;
            if(axisfault.detail instanceof GetException)
                throw (GetException)axisfault.detail;
        }
        throw axisfault;
*/
      } catch (org.apache.axis.AxisFault axisFaultException) {
          if (axisFaultException.detail != null) {
              if (axisFaultException.detail instanceof java.rmi.RemoteException) {
                    throw (java.rmi.RemoteException) axisFaultException.detail;
               }
              if (axisFaultException.detail instanceof GetException) {
                    throw (GetException) axisFaultException.detail;
               }
          }
      
      throw axisFaultException;
      }
    }

    private Vector cachedSerClasses;
    private Vector cachedSerQNames;
    private Vector cachedSerFactories;
    private Vector cachedDeserFactories;
    static OperationDesc _operations[] = new OperationDesc[4];

    static 
    {
        _initOperationDesc1();
    }
}