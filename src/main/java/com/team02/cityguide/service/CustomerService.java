package com.team02.cityguide.service;

// import com.laioffer.onlineorder.entity.CartEntity;
// import com.laioffer.onlineorder.entity.CustomerEntity;
// import com.laioffer.onlineorder.repository.CartRepository;
// import com.laioffer.onlineorder.repository.CustomerRepository;

//for cityguide project
import com.team02.cityguide.entity.RouteEntity;
import com.team02.cityguide.entity.RouteGalleryEntity;
import com.team02.cityguide.entity.SpotGalleryEntity;
import com.team02.cityguide.entity.UnitRouteEntity;
import com.team02.cityguide.entity.UerEntity;
import com.team02.cityguide.entity.UserSpotEntity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomerService {


    // private final CartRepository cartRepository;
    // private final CustomerRepository customerRepository;
    private final RouteEntity routeEntity;
    private final RouteGalleryEntity routeGalleryEntity;
    private final SpotGalleryEntity spotGalleryEntity;
    private final SpotGalleryEntity spotGalleryEntity;
    private final UnitRouteEntity unitRouteEntity;
    private final UerEntity uerEntity;
    private final UserSpotEntity userSpotEntity;
    
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsManager userDetailsManager;


    public CustomerService(
            CartRepository cartRepository,
            CustomerRepository customerRepository,
            PasswordEncoder passwordEncoder,
            UserDetailsManager userDetailsManager) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsManager = userDetailsManager;
    }


    @Transactional
    public void signUp(String email, String password, String firstName, String lastName) {
        email = email.toLowerCase();
        UserDetails user = User.builder()
                .username(email)
                .password(passwordEncoder.encode(password))
                .roles("USER")
                .build();
        userDetailsManager.createUser(user);
        customerRepository.updateNameByEmail(email, firstName, lastName);


        CustomerEntity savedCustomer = customerRepository.findByEmail(email);
        CartEntity cart = new CartEntity(null, savedCustomer.id(), 0.0);
        cartRepository.save(cart);
    }


    public CustomerEntity getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}
