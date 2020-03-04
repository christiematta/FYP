package murex.dev.mxem.Authorization.service;


import lombok.extern.slf4j.Slf4j;
import murex.dev.mxem.Authorization.model.Permission;
import murex.dev.mxem.Authorization.model.Role;
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
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserService {
    @Autowired
    DiscoveryClient discoveryClient;

    public Role[] getRoles(String username){
    try{
            RestTemplate restTemplate = new RestTemplate();
            URI uri= discoveryClient.getInstances("Users")
                .stream()
                .map(si -> si.getUri())
                .findFirst().get();

            URI userUri=new URI(uri.toString()+"/users/"+username+"/roles");

            ResponseEntity<Role[]> entity = restTemplate.getForEntity(userUri,Role[].class);
            log.info("My entity :" +entity);
            Role[] roles = entity.getBody();
        log.info("My roles :" +entity.getBody().toString());
            return roles;


    } catch (Exception e) {
    e.printStackTrace();
}
    return null;
    }

    public Permission[] getPermission(String rolename){
        try{
            RestTemplate restTemplate = new RestTemplate();
            URI uri= discoveryClient.getInstances("Users")
                    .stream()
                    .map(si -> si.getUri())
                    .findFirst().get();

            URI userUri=new URI(uri.toString()+"/roles/"+rolename+"/permissions");

            ResponseEntity<Permission[]> entity = restTemplate.getForEntity(userUri,Permission[].class);
            log.info("My entity :" +entity);
            Permission[] perms = entity.getBody();
            log.info("My roles :" +entity.getBody().toString());
            return perms;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



//    public Set<Permission> getPermissions(Long roleId){
//        try {
//            RestTemplate a;
//            URI uri= discoveryClient.getInstances("Users")
//                    .stream()
//                    .map(si -> si.getUri())
//                    .findFirst().get();
//
//            URL url = null;
//
//            url = new URL(uri.toURL(),"/roles/"+roleId.toString());
//
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//
//            if (conn.getResponseCode() ==200) {
//                Role role = (Role) conn.getContent();
//                return role.getPermissions();
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }



}
