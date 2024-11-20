package hs.data;

import java.io.Serializable;


public class HsDatabaseConnection implements Serializable {

    private String serverName;
    private String database;
    private String username;
    private String password;
    private int port;
    private boolean implicitCachingEnabled;

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isImplicitCachingEnabled() {
        return implicitCachingEnabled;
    }

    public void setImplicitCachingEnabled(boolean implicitCachingEnabled) {
        this.implicitCachingEnabled = implicitCachingEnabled;
    }


}
