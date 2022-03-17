package main.mapper;

import main.model.Patient;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface PatientMapper {

    @Select("select * from \"patient\"")
    List<Patient> selectAll();

    @Insert("insert into \"patient\" values (#{id}, #{name}, #{password}, #{mail})")
    void insert(UUID id, String name, String password, String mail);

    @Delete("delete from \"patient\" where \"id\"=#{id}")
    void delete(@Param(value = "id") UUID id);

    @Update("update \"patient\" set \"name\"=#{name}, \"password\"=#{password}, \"mail\"=#{mail} where \"id\"=#{id}")
    void update(UUID id, String name, String password, String mail);

    @Select("select * from \"patient\" where \"id\"=#{id}")
    Patient selectById(@Param(value = "id") UUID id);
}
