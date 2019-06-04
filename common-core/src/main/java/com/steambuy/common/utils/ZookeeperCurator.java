package com.steambuy.common.utils;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ZookeeperCurator {

    public static void main(String[] args) throws Exception {
        //创建zookeeper的客户端
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.10.104:2181", retryPolicy);
        client.start();
        InterProcessMutex mutex = new InterProcessMutex(client, "/curator/lock");
        try {
            mutex.acquire();
            System.out.println(Thread.currentThread().getName()+"得到锁");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //获得了锁, 进行业务流程
        System.err.println("操作操作");
        //完成业务流程, 释放锁
        try {
            System.out.println(Thread.currentThread().getName()+"释放锁");
            mutex.release();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //关闭客户端
        client.close();
    }
}
