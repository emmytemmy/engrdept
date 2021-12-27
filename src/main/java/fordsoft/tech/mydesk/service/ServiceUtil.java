package fordsoft.tech.mydesk.service;

import fordsoft.tech.mydesk.repo.CommentRepo;
import fordsoft.tech.mydesk.repo.UserRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@Data
public class ServiceUtil {
    @Autowired
    UserRepo userRepo;
    @Autowired
    CommentRepo commentRepo;

    public UserRepo getUserRepo() {
        return userRepo;
    }

    public CommentRepo getCommentRepo() {
        return commentRepo;
    }
    
}
