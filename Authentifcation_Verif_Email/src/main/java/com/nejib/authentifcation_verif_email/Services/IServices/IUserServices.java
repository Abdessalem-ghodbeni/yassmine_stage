package com.nejib.authentifcation_verif_email.Services.IServices;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserServices {
    UserDetailsService userDetailsService();
      void verifyUser(User user);
      List<com.nejib.authentifcation_verif_email.Entites.User> FetAllUser();
      com.nejib.authentifcation_verif_email.Entites.User DsactivateUser(Long idUser);
    com.nejib.authentifcation_verif_email.Entites.User activateUser(Long idUser);
}
