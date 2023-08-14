package com.knife.core.redis;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: pretty-cms
 * @description:
 * @author: GouYe
 * @create: 2021-10-09 16:35
 **/
@Data
public class RedisConnectionBuilder implements Serializable {
    private static final long serialVersionUID = 8249125269257152856L;
    /**
     * 地址
     */
    private String host;
    /**
     * 密码
     */
    private String password;
    /**
     * 端口
     */
    private int port;
    /**
     * 超时时间
     * */
    private int timeout = 3000;
    /**
     * 数据库序号
     */
    private int database;
    /**
     *  连接池最大建立连接等待时间, 单位为ms, 如果超过此时间将抛出异常
     */
    private int maxWait = 3000;
    /**
     *  连接池最大活跃连接数
     */
    private int maxActive =100;
    /**
     * 连接池最大空闲连接数, 超过空闲数将被标记为不可用,然后被释放
     */
    private int maxIdle =20 ;
    /**
     * 连接池里始终应该保持的最小连接数
     */
    private int minIdle = 1;

    public RedisConnectionBuilder(String host, String password, int port, int database) {
        this.host = host;
        this.password = password;
        this.port = port;
        this.database = database;
    }

    public RedisConnectionBuilder(String host, String password, int port, int timeout, int database, int maxWait, int maxActive, int maxIdle, int minIdle) {
        this.host = host;
        this.password = password;
        this.port = port;
        this.timeout = timeout;
        this.database = database;
        this.maxWait = maxWait;
        this.maxActive = maxActive;
        this.maxIdle = maxIdle;
        this.minIdle = minIdle;
    }
}
