package com.lyl.netty.newserver;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 处理服务端 channel
 */
// ChannelInboundHandler 提供了许多事件处理的接口方法，然后你可以覆盖这些方法
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 每当从客户端收到新的数据时，这个方法会在收到消息时被调用，这个例子中，收到的消息的类型是 ByteBuf
     * ByteBuf 是一个引用计数对象(建议在Unpooled中使用辅助方法创建一个新的缓冲区，而不是调用单个实现的构造函数。)
     * Unpooled.buffer()
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ctx.write(msg);
        // ctx.write(Object) 方法不会使消息写入到通道上，他被缓冲在了内部，你需要调用 ctx.flush() 方法来把缓冲区中数据强行输出。
        // 或者你可以用更简洁的 cxt.writeAndFlush(msg) 以达到同样的目的。
        ctx.flush();

         /*// 输出在控制台上
         ByteBuf in = (ByteBuf) msg;
        try {
            while (in.isReadable()) {
                System.out.println((char) in.readByte());
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }*/

        // 默默丢掉收到的数据
        // ((ByteBuf) msg).release();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 出现异常关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
