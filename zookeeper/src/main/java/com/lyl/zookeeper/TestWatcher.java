package com.lyl.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * TestWatcher
 *
 * @author liyl
 * @date 2019-11-12
 */
public class TestWatcher implements Watcher {

    public void process(WatchedEvent watchedEvent) {
        System.out.println("-----------------------");
        System.out.println("WatchedEvent:++++"+watchedEvent);
        System.out.println("-----------------------");
    }
}
