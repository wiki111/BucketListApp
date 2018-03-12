package bucketlistapp.service;

import java.util.ArrayList;
import java.util.List;

import bucketlistapp.dao.UserDAO;
import bucketlistapp.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
  @Autowired
   private AppUserDAO userDAO;

   @Override
   public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
       AppUser user = this.userDAO.findUserAccount(userName);

       if (user == null) {
           System.out.println("User not found! " + userName);
           throw new UsernameNotFoundException("User " + userName + " was not found in the database");
       }

       System.out.println("Found User: " + user);

       List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

      for (String role : roleNames) {
          GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
          grantList.add(authority);
      }


       UserDetails userDetails = (UserDetails) new User(user.getUserName(),
               user.getEncrytedPassword(), grantList);

       return userDetails;
   }
}
