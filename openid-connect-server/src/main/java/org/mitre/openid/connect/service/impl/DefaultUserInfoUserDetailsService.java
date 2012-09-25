package org.mitre.openid.connect.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.mitre.openid.connect.model.UserInfo;
import org.mitre.openid.connect.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * A UserDetailsService backed by a UserInfoRepository.
 *  
 * @author jricher
 *
 */
@Service("userInfoUserDetailsService")
public class DefaultUserInfoUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserInfoRepository repository;
	
    public static final GrantedAuthority ROLE_USER = new SimpleGrantedAuthority("ROLE_USER");
    public static final GrantedAuthority ROLE_ADMIN = new SimpleGrantedAuthority("ROLE_ADMIN");

    private List<String> admins = new ArrayList<String>();
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo = repository.getByUsername(username);
		
		if (userInfo != null) {
		
			// TODO: make passwords configurable? part of object?
			String password = "password";
			
	        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	        authorities.add(ROLE_USER);
	        
	        if (admins != null && admins.contains(username)) {
	        	authorities.add(ROLE_ADMIN);
	        }
	        
	        // TODO: this should really be our own UserDetails wrapper class, shouldn't it?
			User user = new User(userInfo.getUserId(), password, authorities);
			return user;
		} else {
			throw new UsernameNotFoundException("Could not find username: " + username);
		}
	}

	/**
     * @return the admins
     */
    public List<String> getAdmins() {
    	return admins;
    }

	/**
     * @param admins the admins to set
     */
    public void setAdmins(List<String> admins) {
    	this.admins = admins;
    }

}