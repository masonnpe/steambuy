package com.steambuy.common.utils;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.ArrayList;

public class ZKLock {

    private static ZooKeeper zookeeper;

    static {
        try {
            zookeeper = new ZooKeeper(
                    "192.168.10.104:2181",
                    50000,
                    new ZooKeeperWatcher());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lock(String key) {
        String path = "/lock-" + key;

        try {
            zookeeper.create(path, "".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println("success to acquire lock for key=" + key);
        } catch (Exception e) {
            // 如果那个商品对应的锁的node，已经存在了，就是已经被别人加锁了，那么就这里就会报错
            // NodeExistsException
            int count = 0;
            while(true) {
                try {
                    Thread.sleep(1000);
                    zookeeper.create(path, "".getBytes(),
                            ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                } catch (Exception e2) {
                    count++;
                    System.out.println("the " + count + " times try to acquire lock for key=" + key + "......");
                    continue;
                }
                System.out.println("success to acquire lock for key=" + key + "] after " + count + " times try......");
                break;
            }
        }
    }

    public void unlock(String key) {
        String path = "/lock-" + key;
        try {
            zookeeper.delete(path, -1);
            System.out.println("release the lock for key=" + key + "......");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class ZooKeeperWatcher implements Watcher {

        public void process(WatchedEvent event) {
            System.out.println("Receive watched event: " + event.getState());
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ZKLock zkLock = new ZKLock();
        zkLock.lock("1234");
        new Thread(()->{
            ZKLock lock = new ZKLock();
            lock.lock("1234");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock("1234");
        }).start();
        Thread.sleep(2000);
        zkLock.unlock("1234");
    }
}
