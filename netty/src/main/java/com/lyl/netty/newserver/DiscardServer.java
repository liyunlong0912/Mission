package com.lyl.netty.newserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 丢弃服务器,启动DiscardServerHandler
 */
public class DiscardServer {

    // 服务器端口
    private int port;

    // 服务器地址
    private String host;

    public DiscardServer(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public void run() {
        // Netty 提供了许多不同的 EventLoopGroup 的实现用来处理不同的传输。在这个例子中我们实现了一个服务端的应用，
        // 因此会有2个 NioEventLoopGroup 会被使用。第一个经常被叫做‘boss’，用来接收进来的连接。第二个经常被叫做‘worker’，
        // 用来处理已经被接收的连接，一旦‘boss’接收到连接，就会把连接信息注册到‘worker’上。如何知道多少个线程已经被使用，
        // 如何映射到已经创建的 Channel上都需要依赖于 EventLoopGroup 的实现，并且可以通过构造函数来配置他们的关系。
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();

        try {
            // ServerBootstrap 是一个启动 NIO 服务的辅助启动类
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(parentGroup, childGroup)
                    // 使用 NioServerSocketChannel 类来举例说明一个新的 Channel 如何接收进来的连接
                    .channel(NioServerSocketChannel.class)
                    // ChannelInitializer 是一个特殊的处理类，他的目的是帮助使用者配置一个新的 Channel。也许你想通过增加一些
                    // 处理类比如DiscardServerHandler来配置一个新的 Channel 或者其对应的ChannelPipeline 来实现你的网络程序
                    .childHandler(new ChannelInitializer<io.netty.channel.socket.SocketChannel>() {
                        @Override
                        public void initChannel(io.netty.channel.socket.SocketChannel ch) {
                            ch.pipeline().addLast(new DiscardServerHandler());
                        }
                    })
                    // 设置这里指定的 Channel 实现的配置参数,我们正在写一个TCP/IP 的服务端，因此我们被允许设置 socket 的参数选项
                    // 比如tcpNoDelay 和 keepAlive
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // .你关注过 option() 和 childOption() 吗？option()是提供给NioServerSocketChannel 用来接收进来的连接。
                    // childOption() 是提供给由父管道 ServerChannel 接收到的连接，在这个例子中也是 NioServerSocketChannel。
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 绑定端口，开始接收进来的连接
            ChannelFuture f = bootstrap.bind(port).sync();

            // 等待服务器 socket 关闭 。
            // 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            childGroup.shutdownGracefully();
            parentGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new DiscardServer(8001, "localhost").run();
    }
}
