package hs.util;

import com.oreilly.servlet.MailMessage;

import java.io.PrintStream;


public class HsMailer {
   // private static final String MAIL_HOST = "10.1.23.57";//Replaced with below mail server. 10/27/2015 Dev
   private static final String MAIL_HOST = "mailrelay.iot.in.gov";

    private MailMessage msg = null;

    public HsMailer() throws Exception {
        msg = new MailMessage(MAIL_HOST);
    }

    public void createMail(String from,String to,String subject,String text) 
     throws Exception
     {
       msg.from(from);
       msg.to(to);
       
        
       msg.setSubject(subject);
         
       PrintStream out = msg.getPrintStream();
       out.println(text);
     }
    public void createMail(String from, String[] to, String[] cc,
                           String subject, String text) throws Exception {
        //--- UNCOMMENT THIS TO DISABLE IGMS EMAIL ---
        //to = new String[1];
        //to[0] = "dleonardo@dhs.in.gov";
        //cc = new String[1];
        //cc[0] = "dleonardo@dhs.in.gov";
        //---------------------------------------------

        msg.from(from);
        if (to != null) {
            for (int i = 0; i < to.length; i++) {
                msg.to(to[i]);
            }
        }
        if (cc != null) {
            for (int i = 0; i < cc.length; i++) {
                msg.bcc(cc[i]);
            }
        }
        msg.setSubject(subject);

        PrintStream out = msg.getPrintStream();
        out.println(text);
    }


    public void sendAndClose() throws Exception {
        msg.sendAndClose();
    }

}
