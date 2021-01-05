package kafka.interceptor;

public class interceptor {

    /*
        拦截器是在Kafka0.10 之后引入的，主要用于实现 client 端的定制化控制逻辑

        对于 producer 而言，interceptor 使得用户在消息发送前以及 producer 回调
        逻辑前有机会对消息做一些定一只需求，如修改消息等

        同时priducer 允许用户指定多个 interceptor 安装顺序作用域同一条消息
        从而形成一个拦截链

        需要实现接口 ProducerInterceptor， 主要包括3个方式
        configure(configs): 获取配置信息和初始化数据时候调用
        onSend(ProducerRecord):
            该方法封装进send方法中 ，即它允许在用户主线程中，Producer确保在消息被
            序列化以及计算分区前调用该方法，用户可以在该方法中对消息做任何操作
            但最好保证不要修改消息所属的 topic 和 分区，否则会影响目标分区的计算

        onAcknowledgement(RecordMetadata, Exception)
            该方法会在消息从 RecordMetadata 成功发送到 kafka Broker 之后
            或者在发送过程中失败时候调用，并且通常都在 producer 回调逻辑出发之前
            它运行在 producer 的IO线程中，因此不要在该方法中放入很重的逻辑
            否则会拖慢 producer 的消息发送效率

        close
            收尾工作，生产者调用 close() 方法的时候回调这个方法，如在我们的例子中，不执行
             producer.close();
             那么就不会输出  成功：10 失败：0
     */


    /*
        案列：
            实现拦截器链，
            第一个拦截器将消息时间细细加到value头
            消息发送后跟下发送消息树或者失败发送消息数

     */
}
