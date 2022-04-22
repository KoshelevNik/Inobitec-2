package main.mapper;

import main.model.Session;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SessionMapper {

    List<Session> selectAllSessions();

    void insertSession(Session session);

    void deleteSession(Integer id);

    void updateSession(Session session);

    Session selectSessionById(Integer id);

    Session selectSessionBySessionGUID(String sessionGUID);
}
