/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fordsoft.tech.mydesk.service;

import fordsoft.tech.mydesk.model.Comment;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class CommentService {
    @Autowired
    ServiceUtil service;
    public void save(Comment cm){
    service.getCommentRepo().save(cm);
    }
    public Comment getComment(Long id){
        Optional<Comment> cm = service.getCommentRepo().findById(id);
       
        return cm.get();
    }
    public List<Comment> listComments(){
        return service.getCommentRepo().listAll();
    }
    public List<Comment> listComments(int page, int size){
        return service.getCommentRepo().listAll(page, size);
    }
}
