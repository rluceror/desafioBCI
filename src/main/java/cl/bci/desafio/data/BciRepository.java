package cl.bci.desafio.data;

import cl.bci.desafio.data.dtos.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BciRepository extends CrudRepository<User, Long> {

  @Query("SELECT u FROM User u WHERE u.email = :vEmail")
  User findByEmail(@Param("vEmail") String vEmail);

  @Query("SELECT u FROM User u WHERE u.token = :vToken")
  User findUserByToken(@Param("vToken") String vToken);

  List<User> findAll();

  @Modifying
  @Query(
      "UPDATE User u SET u.token = :vToken, u.lastLogin = :vLastLogin,u.modified = :vModified WHERE u.email = :vEmail")
  int updateUser(
      @Param("vEmail") String vEmail,
      @Param("vToken") String vToken,
      @Param("vLastLogin") String vLastLogin,
      @Param("vModified") String vModified);

  @Modifying
  @Query("UPDATE User u SET u.token = :vTokenUpdate WHERE u.token = :vTokenSearch")
  int revocateToken(
      @Param("vTokenSearch") String vTokenSearch, @Param("vTokenUpdate") String vTokenUpdate);
}
