/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fordsoft.tech.mydesk.repo;

import fordsoft.tech.mydesk.model.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author DELL
 */
public interface CommentRepo extends JpaRepository<Comment,Long>{
    
    @Query(value="select * from comment", nativeQuery = true)
    List<Comment> listAll();
        @Query(value="select * from comment limit :page,:size", nativeQuery = true)
    List<Comment> listAll(int page, int size);
    @Query(value="select * from comment where description=?1 and id=?2", nativeQuery = true)
    List<Comment> filter(String desc, Long id);
       @Query(value="select * from comment where description=:desc and id=:recordid", nativeQuery = true)
    List<Comment> filterV( @Param("recordid")Long id,@Param("desc")String desc);
    
      @Query(value="select st from Comment st where st.description=:desc and st.id=:recordid", nativeQuery = true)
    List<Comment> filterVT( @Param("recordid")Long id,@Param("desc")String desc);
    
    List<Comment> findByDescription(String description);
    
    
     @Query(value="select * from comment where description like %:term% or comment like %:term%", nativeQuery = true)
    List<Comment> filterAll(@Param("term")String desc);
    
}
