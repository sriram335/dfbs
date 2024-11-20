// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 10/31/2016 10:15:11 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 

package ai.kwikekard.checkout.service2.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Remote;
import java.util.HashSet;
import java.util.Iterator;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import org.apache.axis.AxisFault;
import org.apache.axis.EngineConfiguration;
import org.apache.axis.client.Service;
import org.apache.axis.client.Stub;

// Referenced classes of package ai.kwikekard.checkout.service2.client:
//            CheckoutService2SoapBindingStub, CheckoutService2_Service, CheckoutService2_PortType

public class CheckoutService2_ServiceLocator extends Service
    implements CheckoutService2_Service
{

    public CheckoutService2_ServiceLocator()
    {
       // CheckoutService2_address = "https://secure.IN.gov/kwikekard/checkout/services/CheckoutService2";
        CheckoutService2_address = "https://secure.IN.gov/apps/kwikekard/checkout/services/CheckoutService2";
        CheckoutService2WSDDServiceName = "CheckoutService2";
        ports = null;
    }

    public CheckoutService2_ServiceLocator(EngineConfiguration engineconfiguration)
    {
        super(engineconfiguration);
        CheckoutService2_address = "https://secure.IN.gov/apps/kwikekard/checkout/services/CheckoutService2";
        CheckoutService2WSDDServiceName = "CheckoutService2";
        ports = null;
    }

    public CheckoutService2_ServiceLocator(String s, QName qname)
        throws ServiceException
    {
        super(s, qname);
        CheckoutService2_address = "https://secure.IN.gov/apps/kwikekard/checkout/services/CheckoutService2";
        CheckoutService2WSDDServiceName = "CheckoutService2";
        ports = null;
    }

    public String getCheckoutService2Address()
    {
        return CheckoutService2_address;
    }

    public String getCheckoutService2WSDDServiceName()
    {
        return CheckoutService2WSDDServiceName;
    }

    public void setCheckoutService2WSDDServiceName(String s)
    {
        CheckoutService2WSDDServiceName = s;
    }

    public CheckoutService2_PortType getCheckoutService2()
        throws ServiceException
    {
        URL url;
        try
        {
            url = new URL(CheckoutService2_address);
        }
        catch(MalformedURLException malformedurlexception)
        {
            throw new ServiceException(malformedurlexception);
        }
        return getCheckoutService2(url);
    }

    public CheckoutService2_PortType getCheckoutService2(URL url)
        throws ServiceException
    {
        CheckoutService2SoapBindingStub checkoutservice2soapbindingstub;
        try {
            checkoutservice2soapbindingstub = new CheckoutService2SoapBindingStub(url, this);
            checkoutservice2soapbindingstub.setPortName(getCheckoutService2WSDDServiceName());
           return checkoutservice2soapbindingstub;
        } catch (AxisFault e) {
            return null;
        }
    }

    public void setCheckoutService2EndpointAddress(String s)
    {
        CheckoutService2_address = s;
    }

    public Remote getPort(Class class1)
        throws ServiceException
    {
        CheckoutService2SoapBindingStub checkoutservice2soapbindingstub;
            try {
            if ((ai.kwikekard.checkout.service2.client.CheckoutService2_PortType.class).isAssignableFrom(class1)){
            checkoutservice2soapbindingstub = new CheckoutService2SoapBindingStub(new URL(CheckoutService2_address), this);
            checkoutservice2soapbindingstub.setPortName(getCheckoutService2WSDDServiceName());
            return checkoutservice2soapbindingstub;
            }
        }catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (class1 != null ? class1.getName() : "null"));
        
    }

    public Remote getPort(QName qname, Class class1)
        throws ServiceException
    {
        if(qname == null)
            return getPort(class1);
        String s = qname.getLocalPart();
        if("CheckoutService2".equals(s))
        {
            return getCheckoutService2();
        } else
        {
            Remote remote = getPort(class1);
            ((Stub)remote).setPortName(qname);
            return remote;
        }
    }

    public QName getServiceName()
    {
        return new QName("https://secure.IN.gov/apps/kwikekard/checkout/services/CheckoutService2", "CheckoutService2");
    }

    public Iterator getPorts()
    {
        if(ports == null)
        {
            ports = new HashSet();
            ports.add(new QName("https://secure.IN.gov/apps/kwikekard/checkout/services/CheckoutService2", "CheckoutService2"));
        }
        return ports.iterator();
    }

    public void setEndpointAddress(String s, String s1)
        throws ServiceException
    {
        if("CheckoutService2".equals(s))
            setCheckoutService2EndpointAddress(s1);
        else
            throw new ServiceException(" Cannot set Endpoint Address for Unknown Port" + s);
    }

    public void setEndpointAddress(QName qname, String s)
        throws ServiceException
    {
        setEndpointAddress(qname.getLocalPart(), s);
    }

    private String CheckoutService2_address;
    private String CheckoutService2WSDDServiceName;
    private HashSet ports;
}