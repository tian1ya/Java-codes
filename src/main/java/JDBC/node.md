
数据库连接是在执行查询的时候最耗费资源的事件

尤其是当查询非常频繁的时候，每次使用玩就断开，然后使用的时候在建立连接

基于此产生数据池的技术

在数据池中建立多个连接，再操作的时候，将连接拿出来使用

当操作结束的时候，将连接再放回到数据池中，也就是建立一个『缓冲池』

Java 中实现连接池，需要实现 javax.sql.DataSource 接口，这个接口中定义了两个重载的 getConnection 方法

 
Connection.getConnection() 
Connection.getConnection(String username, String password)

创建连接池的步骤：
    1. 在自定义的构造函数中批量创建 Connection ，并把创建的连接池保存到一个集合对象中
    2. 在自定义类中实现Connection.getConnection 方法，让这个方法每次调用的时候，都从集合中取出一个Connection 返回给
        用户
    3. 当用户使用完 connection 不能调用 connection.cloes() 方法，而是使用连接池提供关闭方法，
        将Connection 放回到连接池中
        
    


