package Assignment.Assignment.repository;

import Assignment.Assignment.entity.WholeSalers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WholeSalerRepo extends JpaRepository<WholeSalers, UUID> {
//    @Query("select w from WholeSalers w where w.wholeSalerId = ?1")
//    List<WholeSalers> findByWholeSalerId(String wholeSalerId);

    WholeSalers findBywholeSalerId(String wholeSalerId);

    void deleteBywholeSalerId(String wholeSalerId);

    List<WholeSalers> findByfirstName(String firstName);

    List<WholeSalers> findBylastName(String lastName);

    List<WholeSalers>findByemailId(String emailId);

    List<WholeSalers>findByphoneNo(String phoneNo);


}
