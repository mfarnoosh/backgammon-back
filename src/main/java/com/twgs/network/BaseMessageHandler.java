package com.twgs.network;

import com.twgs.network.messages.SocketMessage;

/**
 * Created by Mehrdad on 16/12/11.
 */

public abstract class BaseMessageHandler {
    public abstract SocketMessage handle(SocketMessage message);
}
