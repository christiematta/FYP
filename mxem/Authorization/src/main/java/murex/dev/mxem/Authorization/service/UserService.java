package murex.dev.mxem.Authorization.service;


import lombok.extern.slf4j.Slf4j;
import murex.dev.mxem.Authorization.model.Permission;
import murex.dev.mxem.Authorization.model.Role;
import murex.dev.mxem.Authorization.model.RolesPermissions;
import murex.dev.mxem.Authorization.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserService {
    @Autowired
    DiscoveryClient discoveryClient;

    public RolesPermissions getRoles(String username){
    try{
            RestTemplate restTemplate = new RestTemplate();
            URI uri= discoveryClient.getInstances("Users")
                .stream()
                .map(si -> si.getUri())
                .findFirst().get();

            URI userUri=new URI(uri.toString()+"/users/"+username+"/roles");

            ResponseEntity<Role[]> entity = restTemplate.getForEntity(userUri,Role[].class);
            Role[] roles = entity.getBody();
        Set<String> permNames =  new HashSet<String>();
        Set<String> roleNames = new HashSet<String>();
            for(Role role : roles){
                roleNames.add(role.getName());
                URI roleUri = new URI(uri.toString() + "/roles/" + role.getName() + "/permissions");
               Permission[] permissions = restTemplate.getForEntity(roleUri,Permission[].class).getBody();
               log.info("Les permissions du role : "+ role.getName()+" sont "+ permissions);
                for(Permission perm : permissions ){
                    permNames.add(perm.getName());
                }

            }
            log.info("L'ensemble des permissions :" + permNames);
            RolesPermissions rolesPermissions = new RolesPermissions();
            rolesPermissions.setRoles(roleNames);
            rolesPermissions.setPermissions(permNames);
            return rolesPermissions;


    } catch (Exception e) {
    e.printStackTrace();
}
    return null;
    }


}
