package com.kingston.jforgame.server.tools;

import com.kingston.jforgame.common.utils.ClassScanner;
import com.kingston.jforgame.server.ServerScanPaths;
import com.kingston.jforgame.socket.message.Message;

import java.util.stream.Collectors;

/**
 * 导出所有客户端协议
 */
public class MessageExport {

    public static void main(String[] args) {
        ClassScanner.listAllSubclasses(ServerScanPaths.MESSAGE_PATH, Message.class)
                .stream()
                .map(k -> {
                try {
                    return k.newInstance();
                } catch (Exception ignore) { }
                    return null;
                })
                .map(m -> MessageMetadata.valueOf((Message) m))
                .filter(m -> m.getId() > 0)
                .sorted(MessageMetadata::compareTo)
                .collect(Collectors.toList()).forEach(System.out::println);
    }

}
