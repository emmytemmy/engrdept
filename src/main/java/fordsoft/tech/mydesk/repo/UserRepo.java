package fordsoft.tech.mydesk.repo;

import fordsoft.tech.mydesk.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findByEmailAndPsn(String email, String psn);

    long countByPsn(String psn);

    List<User> findByEmailLike(String email);

    @Query(value = "select * from User where Email like %:term% or FullName like %:term%", nativeQuery = true)
    List<User> filterAll(@Param("term") String desc);

}
