package com.gao.business.common;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/***
 * Created by gyang on 2017/2/20.
 */
public class UIdUtil {

    public static final String UID_WORKER_ID = "uid.worker.id";
    private static final UIdUtil INSTANCE = new UIdUtil();

    // 计算节点编号（0 至 9）
    private final int workerId;

    // 自增序列
    private int sequence = 0;
    private long lastTimestamp = System.currentTimeMillis();

    private UIdUtil() {
        this.workerId = (System.getProperty(UID_WORKER_ID) == null ? 0 : Integer.valueOf(System.getProperty("uid.worker.id")));
        if (this.workerId < 0 || this.workerId > 9) {
            throw new RuntimeException("计算节点编号不合法");
        }
    }

    public static final UIdUtil getInstance() {
        return INSTANCE;
    }

    private synchronized long nextId() {
        long timestamp = System.currentTimeMillis(); // 获取当前毫秒数

        // 如果服务器时间有问题(时钟后退) 报错。
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        // 如果上次生成时间和当前时间相同,在同一毫秒内
        if (lastTimestamp == timestamp) {
            sequence++;
            if (sequence > 99) {
                sequence = 1;
                timestamp = tilNextMillis(lastTimestamp); // 自旋等待到下一毫秒
            }
        } else {
            sequence = 1; // 如果和上次生成时间不同, 重置sequence
        }
        lastTimestamp = timestamp;

        return Long.valueOf(String.valueOf(timestamp) + String.valueOf(this.workerId) + StringUtils.leftPad(String.valueOf(sequence), 2, "0"));
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    /**
     * 生成唯一编号(uuid)
     *
     * @return
     */
    public String uid() {
        return StringUtils.replace(UUID.randomUUID().toString(), "-", "");
    }

    /**
     * 获取全局唯一id，保证位数在16个长度内，避免返回到前端失去精度 1毫秒至多生成99个id
     *
     * @return long
     */
    public long id() {
        return nextId();
    }

    /**
     * 获取16位全局唯一id
     *
     * @return String
     */
    public String id16() {
        return StringUtils.leftPad(Long.toHexString(nextId()), 16, "8");
    }

    public String getUuid() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

    static class IdGen {
        private final long workerId;
        private final long datacenterId;
        private final long twepoch = 1288834974657L; // Thu, 04 Nov 2010 01:42:54 GMT
        private final long workerIdBits = 5L; // 节点ID长度
        private final long datacenterIdBits = 5L; // 数据中心ID长度
        private final long maxWorkerId = -1L ^ (-1L << workerIdBits); // 最大支持机器节点数0~31，一共32个
        private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits); // 最大支持数据中心节点数0~31，一共32个
        private final long sequenceBits = 12L; // 序列号12位
        private final long workerIdShift = sequenceBits; // 机器节点左移12位
        private final long datacenterIdShift = sequenceBits + workerIdBits; // 数据中心节点左移17位
        private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits; // 时间毫秒数左移22位
        private final long sequenceMask = -1L ^ (-1L << sequenceBits); // 4095
        private long sequence = 0L;
        private long lastTimestamp = -1L;

        public IdGen() {
            this(0L, 0L);
        }

        public IdGen(long workerId, long datacenterId) {
            if (workerId > maxWorkerId || workerId < 0) {
                throw new IllegalArgumentException(
                        String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
            }
            if (datacenterId > maxDatacenterId || datacenterId < 0) {
                throw new IllegalArgumentException(
                        String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
            }
            this.workerId = workerId;
            this.datacenterId = datacenterId;
        }

        public synchronized long nextId() {
            long timestamp = timeGen(); // 获取当前毫秒数
            // 如果服务器时间有问题(时钟后退) 报错。
            if (timestamp < lastTimestamp) {
                throw new RuntimeException(String.format(
                        "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
            }
            // 如果上次生成时间和当前时间相同,在同一毫秒内
            if (lastTimestamp == timestamp) {
                // sequence自增，因为sequence只有12bit，所以和sequenceMask相与一下，去掉高位
                sequence = (sequence + 1) & sequenceMask;
                // 判断是否溢出,也就是每毫秒内超过4095，当为4096时，与sequenceMask相与，sequence就等于0
                if (sequence == 0) {
                    timestamp = tilNextMillis(lastTimestamp); // 自旋等待到下一毫秒
                }
            } else {
                sequence = 0L; // 如果和上次生成时间不同,重置sequence，就是下一毫秒开始，sequence计数重新从0开始累加
            }
            lastTimestamp = timestamp;
            // 最后按照规则拼出ID。
            // 000000000000000000000000000000000000000000 00000 00000 000000000000
            // time datacenterId workerId sequence
            return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
                    | (workerId << workerIdShift) | sequence;
        }

        private long tilNextMillis(long lastTimestamp) {
            long timestamp = timeGen();
            while (timestamp <= lastTimestamp) {
                timestamp = timeGen();
            }
            return timestamp;
        }

        private long timeGen() {
            return System.currentTimeMillis();
        }
    }

    public static void main(String[] args) {
        System.out.println(UIdUtil.getInstance().id());
        System.out.println(UIdUtil.getInstance().id16());
    }
}
