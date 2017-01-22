package com.uvlab.cloud.site.security.Service;

import com.uvlab.cloud.site.security.common.JwtUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//import org.zerhusen.security.JwtUserFactory;
//import org.zerhusen.security.repository.UserRepository;

/**
 * Created by stephan on 20.03.16.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

//    @Autowired
//    private UserRepository userRepository;
    JwtUserService svc=new JwtUserService();
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username); //need to use JxUserService to get data from database
        try {
            JwtUser user = svc.getUserByname(username);
            return user;
        }catch (Exception e){
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
//        if (user == null) {
//            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
//        } else {
//            return JwtUserFactory.create(user);
//        }
    }
}
