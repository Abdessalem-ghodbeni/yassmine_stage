package com.nejib.authentifcation_verif_email.Services.ServiceImpl;

import com.nejib.authentifcation_verif_email.Repository.IUserRepository;
import com.nejib.authentifcation_verif_email.Services.IServices.IUserServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class IUserServicesImp implements IUserServices {

    private final IUserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService(){
            @Override
            public UserDetails loadUserByUsername(String s) {
                return userRepository.findByEmail(s).orElseThrow(() -> new RuntimeException("User not found"));
            }
        };
    }

    @Override
    @Transactional
    public void verifyUser(User user) {

    }

    @Override
    public List<com.nejib.authentifcation_verif_email.Entites.User> FetAllUser() {
        List<com.nejib.authentifcation_verif_email.Entites.User> listeUser=userRepository.findAll();
        return listeUser;
    }

    @Override
    public com.nejib.authentifcation_verif_email.Entites.User DsactivateUser(Long idUser) {
        com.nejib.authentifcation_verif_email.Entites.User user=userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("User not found"));
        user.setVerified(false);
        userRepository.save(user);
        return user;
    }

    @Override
    public com.nejib.authentifcation_verif_email.Entites.User activateUser(Long idUser) {
        com.nejib.authentifcation_verif_email.Entites.User user=userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("User not found"));
        user.setVerified(true);
        userRepository.save(user);
        return user;
    }
}
