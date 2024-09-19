package com.nejib.authentifcation_verif_email;

import com.nejib.authentifcation_verif_email.Entites.Admin;
import com.nejib.authentifcation_verif_email.Entites.Role;
import com.nejib.authentifcation_verif_email.Entites.User;
import com.nejib.authentifcation_verif_email.Repository.IAdminRepository;
import com.nejib.authentifcation_verif_email.Repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication


@EnableAspectJAutoProxy
@RequiredArgsConstructor
@ComponentScan(basePackages={"com.nejib.authentifcation_verif_email" ,"com.nejib.authentifcation_verif_email.CorsCongiguration"})
public class AuthentifcationVerifEmailApplication implements CommandLineRunner {

    private final IUserRepository userRepository;
    private final IAdminRepository adminRepository;
    public static void main(String[] args) {
        SpringApplication.run(AuthentifcationVerifEmailApplication.class, args);
    }

    public void run(String... args) {
        User adminAccount = userRepository.findByRole(Role.ADMIN);
        if (adminAccount == null) {
            Admin admin = new Admin();
            admin.setEmail("admin@gmail.com");
            admin.setNom("admin");
            admin.setVerified(true);
         admin.setRole(Role.ADMIN);
            admin.setImage("ffffffff");
            admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
            adminRepository.save(admin);
        }
    }
}
