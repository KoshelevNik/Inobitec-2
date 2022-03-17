package main.mapper;

import main.model.MedicineService;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface MedicineServiceMapper {

    @Select("select * from \"service\"")
    List<MedicineService> selectAll();

    @Insert("insert into \"service\" values (#{name}, #{cost}, #{id}, #{description}, #{doctorSpecialistName})")
    void insert(String name, Integer cost, UUID id, String description, String doctorSpecialistName);

    @Delete("delete from \"service\" where \"id\"=#{id}")
    void delete(@Param(value = "id") UUID id);

    @Update("update \"service\" set \"name\"=#{name}, \"cost\"=#{cost}, \"description\"=#{description}, \"doctor_specialist_name\"=#{doctorSpecialistName} where \"id\"=#{id}")
    void update(String name, Integer cost, UUID id, String description, String doctorSpecialistName);
}
