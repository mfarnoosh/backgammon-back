package com.twgs.network.handlers;

import com.twgs.network.messages.SocketMessage;
import com.twgs.network.BaseMessageHandler;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Mehrdad on 16/12/11.
 */
@Component
@Scope("singleton")
public class EchoMessageHandler extends BaseMessageHandler {
    private Logger logger = Logger.getLogger(EchoMessageHandler.class);
    @Override
    public SocketMessage handle(SocketMessage message) {
        logger.info("echo message: " + message);
        return message;
    }
}
