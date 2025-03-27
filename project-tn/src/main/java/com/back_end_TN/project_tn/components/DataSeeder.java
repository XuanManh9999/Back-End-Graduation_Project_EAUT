package com.back_end_TN.project_tn.components;

import com.back_end_TN.project_tn.entitys.Permission;
import com.back_end_TN.project_tn.entitys.RoleEntity;
import com.back_end_TN.project_tn.enums.Active;
import com.back_end_TN.project_tn.enums.Role;
import com.back_end_TN.project_tn.repositorys.PermissionRepository;
import com.back_end_TN.project_tn.repositorys.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner  {

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args)  {
        seederPermission();
        seederRole();
    }

    public void seederPermission () {
        if (permissionRepository.count() == 0) {
            permissionRepository.save(Permission.builder().name("CREATE").build());
            permissionRepository.save(Permission.builder().name("UPDATE").build());
            permissionRepository.save(Permission.builder().name("READ").build());
            permissionRepository.save(Permission.builder().name("DELETE").build());
            permissionRepository.save(Permission.builder().name("FULL_ACCESS").build());
        }
    }

    public void seederRole () {
        if (roleRepository.count() == 0) {
            var roleAdmin = new  RoleEntity();
            var roleClient = new RoleEntity();
            var roleLecturer= new RoleEntity();
            roleAdmin.setName(Role.ADMIN);
            roleAdmin.setActive(Active.HOAT_DONG);
            roleClient.setName(Role.CLIENT);
            roleClient.setActive(Active.HOAT_DONG);
            roleLecturer.setName(Role.LECTURER);
            roleLecturer.setActive(Active.HOAT_DONG);
            roleRepository.save(roleAdmin);
            roleRepository.save(roleClient);
            roleRepository.save(roleLecturer);
        }
    }



}
