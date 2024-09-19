package com.nejib.authentifcation_verif_email.Repository;


import com.nejib.authentifcation_verif_email.Entites.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepository extends JpaRepository<Admin, Long> {

}
