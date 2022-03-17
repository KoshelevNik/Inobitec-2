package main.mapper;

import main.model.Order;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Mapper
public interface OrderMapper {

    @Select("select * from \"order\"")
    List<Order> selectAll();

    @Insert("insert into \"order\" values (#{patientId}, #{serviceId}, #{date})")
    void insert(UUID patientId, UUID serviceId, Date date);

    @Delete("delete from \"order\" where \"patient_id\"=#{patientId} and \"service_id\"=#{serviceId}")
    void delete(@Param(value = "patientId") UUID patientId, @Param(value = "serviceId") UUID serviceId);

    @Select("select * from \"order\" where \"patient_id\"=#{patientId} and \"service_id\"=#{serviceId}")
    Order selectById(@Param(value = "patientId") UUID patientId, @Param(value = "serviceId") UUID serviceId);
}
