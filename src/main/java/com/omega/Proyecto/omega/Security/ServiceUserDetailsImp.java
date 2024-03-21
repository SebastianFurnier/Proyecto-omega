package com.omega.Proyecto.omega.Security;

import com.omega.Proyecto.omega.Repository.IRepositoryEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServiceUserDetailsImp implements UserDetailsService {

    @Autowired
    private IRepositoryEmployee IRepoEmployee;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return IRepoEmployee.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("The user doesn´t exist."));
    }
}
