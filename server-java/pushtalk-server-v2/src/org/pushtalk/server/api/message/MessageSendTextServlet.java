package org.pushtalk.server.api.message;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.pushtalk.server.utils.JPushService;
import org.pushtalk.server.utils.RightJson;
import org.pushtalk.server.web.common.NormalBaseServlet;

public class MessageSendTextServlet extends NormalBaseServlet
{

    private static final long serialVersionUID = 348660245631638687L;
    private static Logger LOG = Logger.getLogger(MessageSendTextServlet.class);

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        LOG.debug("api - /message/sendtext");
        String user_name = request.getParameter("user_name");
        String friend_name = request.getParameter("friend_name");
        String text = request.getParameter("text");

        Map<String, String> msg_map = new HashMap<String, String>();
        msg_map.put("user_name", user_name);
        msg_map.put("friend_name", friend_name);
        msg_map.put("text", text);
        RightJson msg = new RightJson(3003, msg_map);
        
        JPushService.sendMsgTo(gson.toJson(msg), friend_name);

        response.getOutputStream().write(gson.toJson(msg).getBytes());
    }
}
