package hs.data;

import java.sql.SQLException;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;


public class HsDataSource  {

    private String jndiString = null;
    private DataSource dsDefault = null;

    // who is passing this jndiString here
    public HsDataSource(String jndiString) {
        
        this.jndiString = jndiString;
        
    }


    public HsDataSource(String serverName, String database, String username,
                        String password, int port) throws SQLException {
        
        OracleDataSource ods = new OracleDataSource();

        ods.setDriverType("thin");
        ods.setNetworkProtocol("tcp");
        ods.setPortNumber(port);

        ods.setServerName(serverName);
        ods.setDatabaseName(database);
        ods.setUser(username);
        ods.setPassword(password);
        ods.setConnectionCachingEnabled(true);
        
        dsDefault = ods;

    }
    
    

    public Connection getConnection() throws SQLException, NamingException {
        if(dsDefault != null) {
            return dsDefault.getConnection();
        } 
        else 
        {          
            Context initialContext = new InitialContext();
            DataSource d = (DataSource) initialContext.lookup(this.getJndiString());
            return d.getConnection();
        }
        
    }
    
    public HsDatabaseConnection getHsDatabaseConnection() throws Exception {
        HsDatabaseConnection db = new HsDatabaseConnection();
        if (dsDefault == null) {
            return new HsDatabaseConnection();
        }

        //db.setServerName(ds.getServerName());
        //db.setDatabase(ds.getDatabaseName());
        //db.setUsername(ds.getUser());
        //db.setPort(ds.getPortNumber());


        return db;
    }


    
    public String getJndiString() {
        return jndiString;
    }
}


