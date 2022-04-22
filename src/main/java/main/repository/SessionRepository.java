package main.repository;

import main.mapper.SessionMapper;
import main.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SessionRepository {

    @Autowired
    private SessionMapper sessionMapper;

    public void insertSession(Session session) {
        sessionMapper.insertSession(session);
    }

    public void deleteSession(Integer id) {
        sessionMapper.deleteSession(id);
    }

    public void updateSession(Session session) {
        sessionMapper.updateSession(session);
    }

    public List<Session> selectAllSessions() {
        return sessionMapper.selectAllSessions();
    }

    public Session selectSessionById(Integer id) {
        return sessionMapper.selectSessionById(id);
    }

    public Session selectSessionBySessionGUID(String sessionUUID) {
        return sessionMapper.selectSessionBySessionGUID(sessionUUID);
    }
}
