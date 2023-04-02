package com.shop.bike.security;


import com.shop.bike.entity.Role;
import com.shop.bike.entity.User;
import com.shop.bike.entity.enumeration.AuthorityType;
import com.shop.bike.entity.enumeration.ErrorEnum;
import com.shop.bike.repository.UserRepository;
import com.shop.bike.web.rest.errors.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "userCustomService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public org.springframework.security.core.userdetails.User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findOneWithAuthoritiesByLogin(username);
        if (optionalUser.isEmpty()) {
			throw new UsernameNotFoundException(ErrorEnum.UNAUTHORIZED, null);
        }
		checkUserStatus(optionalUser.get());
		List<GrantedAuthority> grantedAuthorities = optionalUser.get()
				.getRoles()
				.stream()
				.map(authority -> new SimpleGrantedAuthority(authority.getCode()))
				.collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(optionalUser.get().getId().toString(), optionalUser.get().getPassword(), grantedAuthorities);
    }
	
	private void checkUserStatus(User user) {
		if(user.getStatus()==null) {
			throw new UserBlockedException();
		}
		if (user.getStatus() == 0) {
			throw new UserNotActivatedException();
		}
	}
	
	/**
	 * Checks if the user has any of the authorities.
	 *
	 * @param authorities the authorities to check.
	 * @return true if the user has any of the authorities, false otherwise.
	 */
	public static boolean hasUserThisAuthority(User user, AuthorityType... authorities) {
		return user.getRoles().stream()
				.map(Role::getCode)
				.map(AuthorityType::get)
				.anyMatch(authority -> Arrays.asList(authorities).contains(authority));
	}
}
