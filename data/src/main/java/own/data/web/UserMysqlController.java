package own.data.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import own.data.mapper.UserMapper;
import own.data.model.UserMysql;

import java.util.List;

@RestController
public class UserMysqlController {
	
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping("/getUsers")
	public List<UserMysql> getUsers() {
		List<UserMysql> users=userMapper.getAll();
		return users;
	}
	
    @RequestMapping("/getUserss")
    public UserMysql getUser(Long id) {
    	UserMysql user=userMapper.getOne(id);
        return user;
    }
    
    @RequestMapping("/add")
    public void save(UserMysql user) {
    	userMapper.insert(user);
    }
    
    @RequestMapping(value="update")
    public void update(UserMysql user) {
    	userMapper.update(user);
    }
    
    @RequestMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
    	userMapper.delete(id);
    }
    
    
}