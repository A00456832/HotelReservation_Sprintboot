package com.assignment.HotelRestAPI.repository;

import com.assignment.HotelRestAPI.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
//    @Query(value = "SELECT * from Employee e where e.employeeName =:name AND e.employeeRole = :role ", nativeQuery = true)
//    List<Employee> findByNameAndRoleNative(@Param("name") String name, @Param("role")String role);

    @Query(value = "SELECT DISTINCT h.* FROM hotel as h left join reservation as r on h.id=r.hotel_id where is_available=:isAvailable ", nativeQuery = true)
    List<Hotel> getAvailableHotelList(@Param("isAvailable") Boolean isAvailable);
}
