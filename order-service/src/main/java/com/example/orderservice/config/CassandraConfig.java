package com.example.orderservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.cassandra")
public class CassandraConfig {
    private String contactPoints;
    private int port;
    private String keyspaceName;
    private String localDatacenter;

    public String getContactPoints() { return contactPoints; }
    public void setContactPoints(String contactPoints) { this.contactPoints = contactPoints; }

    public int getPort() { return port; }
    public void setPort(int port) { this.port = port; }

    public String getKeyspaceName() { return keyspaceName; }
    public void setKeyspaceName(String keyspaceName) { this.keyspaceName = keyspaceName; }

    public String getLocalDatacenter() { return localDatacenter; }
    public void setLocalDatacenter(String localDatacenter) { this.localDatacenter = localDatacenter; }
}
