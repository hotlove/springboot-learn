package com.guo.springboot.kafka;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Date: 2021/1/15 20:45
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
public class DemoPartitioner implements Partitioner {
    /**
     * 实现⾃定义的 DemoPartitioner 类之后，需要通过配置参数 partitioner.class 来显式指定这个分区器。
     * props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, DemoPartitioner.class.getName());
     */
    private final AtomicInteger counter = new AtomicInteger(0);

    /**
     *partition() ⽅法⽤来计算分区号，返回值为 int 类型。
     * partition() ⽅法中的参数分别表⽰主题、键、序列化后的键、值、序列化后的值，以及集群的元数据信息，
     * 通过这些信息可以实现功能丰富的分区器
     */
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int numPartitions = partitions.size();
        if (null == keyBytes) {
            return counter.getAndIncrement() % numPartitions;
        }else
            return Utils.toPositive(Utils.murmur2(keyBytes)) % numPartitions;
    }

    /**
     * close() ⽅法在关闭分区器的时候⽤来回收⼀些资源
     */
    @Override
    public void close() {}

    /**
     * configure() ⽅法主要⽤来获取配置信息及初始化数据。
     */
    @Override
    public void configure(Map<String, ?> configs) {}
}
