package main.servlet;

import main.model.Session;
import main.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebFilter("/servlet/*")
public class MyFilter implements Filter {

    @Autowired
    private SessionService sessionService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String sessionId = request.getHeader("sessionId");
        if (sessionId == null || sessionId.isEmpty()) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Invalid Session");
        } else if (sessionService.getSessionCache().get(sessionId) != null) {
            Session session = sessionService.getSessionCache().get(sessionId);
            Date dateOfCreation = session.getTimeOfCreation();
            Date stopTime = (Date) dateOfCreation.clone();
            stopTime.setTime(dateOfCreation.getTime() + session.getTimeoutMinutes() * 60000);
            if (stopTime.before(new Date())) {
                servletResponse.getWriter().println("Session expired");
                synchronized (sessionService.getSessionCache()) {
                    sessionService.getSessionCache().remove(sessionId);
                }
            } else {
                filterChain.doFilter(request, servletResponse);
            }
        } else if (sessionService.readSessionBySessionGUID(sessionId) == null) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("Unknown Session");
        } else {
            Session session = sessionService.readSessionBySessionGUID(sessionId);
            Date dateOfCreation = session.getTimeOfCreation();
            Date stopTime = (Date) dateOfCreation.clone();
            stopTime.setTime(dateOfCreation.getTime() + session.getTimeoutMinutes() * 60000);
            if (stopTime.before(new Date())) {
                servletResponse.getWriter().println("Session expired");
            } else {
                synchronized (sessionService.getSessionCache()) {
                    sessionService.getSessionCache().put(sessionId, session);
                }
                filterChain.doFilter(request, servletResponse);
            }
        }
    }
}