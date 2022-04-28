package main.service;

import main.model.Patient;
import main.model.Session;
import main.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class SessionService {

    private final HashMap<String, Session> sessionCache = new HashMap<>();

    @Autowired
    private SessionRepository sessionRepository;

    public void createSession(Session session) {
        sessionRepository.insertSession(session);
    }

    public List<Session> readAllSessions() {
        return sessionRepository.selectAllSessions();
    }

    public void updateSession(Session session, Integer id) {
        session.setId(id);
        sessionRepository.updateSession(session);
    }

    public void deleteSession(Integer id) {
        sessionRepository.deleteSession(id);
    }

    public Session readSessionById(Integer id) {
        return sessionRepository.selectSessionById(id);
    }

    public Session readSessionBySessionGUID(String sessionGUID) {
        try {
            return sessionRepository.selectSessionBySessionGUID(sessionGUID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public HashMap<String, Session> getSessionCache() {
        return sessionCache;
    }
}
