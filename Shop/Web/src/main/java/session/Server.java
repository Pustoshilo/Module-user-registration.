package session;

import javax.servlet.http.HttpServletRequest;
import constants.Constants;

public final class Server {
     public static void initSessionInfo(HttpServletRequest request) {
        if (request.getSession().getAttribute("sessionInfo") == null) {
            SessionInfo sessionInfo = new SessionInfo();
            sessionInfo.setLocale(Constants.DEFAULT_LOCALE);
            request.getSession().setAttribute("sessionInfo", sessionInfo);

             }
    }
}
