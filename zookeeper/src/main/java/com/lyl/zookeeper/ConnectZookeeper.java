package com.lyl.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * Zookeeper单机连接测试
 *
 * @author liyl
 * @date 2019-11-12
 */
public class ConnectZookeeper {
    public static void main(String[] args) {
        try {
            ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 1000, new TestWatcher());
            String node = "/Test";
            // 查看是否存在节点
            Stat stat = zooKeeper.exists(node, false);
            // 如果不存在节点，则创建节点
            if (stat == null) {
                String creatResult = zooKeeper.create(node, node.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.CONTAINER);
                System.out.println(creatResult);
            }
            byte[] data = zooKeeper.getData(node, false, stat);
            System.out.println(new String(data));
            zooKeeper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
