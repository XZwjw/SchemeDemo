package com.example.myapplication;

/**
 * Description:
 * Created by wangjiawang on 2018/8/30.
 * complete
 */
public class SchemeEntry {
    private String host;
    private String dataString;
    private String key1;
    private String path;
    private String path1;
    private String queryString;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath1() {
        return path1;
    }

    public void setPath1(String path1) {
        this.path1 = path1;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    @Override
    public String toString() {
        return "SchemeEntry{" +
                "host='" + host + '\'' +
                ", dataString='" + dataString + '\'' +
                ", key1='" + key1 + '\'' +
                ", path='" + path + '\'' +
                ", path1='" + path1 + '\'' +
                ", queryString='" + queryString + '\'' +
                '}';
    }
}
