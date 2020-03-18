package murex.dev.mxem.Environments.controller;


import lombok.extern.slf4j.Slf4j;
import murex.dev.mxem.Environments.exception.ProjectNotFoundException;
import murex.dev.mxem.Environments.model.Environment;
import murex.dev.mxem.Environments.model.Project;
import murex.dev.mxem.Environments.service.AuthorizationService;
import murex.dev.mxem.Environments.service.EnvironmentService;
import murex.dev.mxem.Environments.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@Slf4j
@RestController
@Validated
@RefreshScope
@RequestMapping(value = "/projects")
public class ProjectController {


    @Autowired
    ProjectService projectService;

    @Autowired
    EnvironmentService environmentService;


    @Autowired
    AuthorizationService authorizationService;

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        log.info("Calling get all environments");
        List<Project> projs = projectService.findAll();
        log.info(projs.toString());
        return ResponseEntity.ok(projs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectDetails(@PathVariable String id) {
        Project proj  = projectService.findById(id);
        return ResponseEntity.ok(proj);
    }
//
    @GetMapping("/{id}/environments")
    public ResponseEntity<List<Environment>> getEnvironmentsOfProjects(@PathVariable String id){
        return ResponseEntity.ok(environmentService.findByProjectId(id));
    }

    @PostMapping
    public ResponseEntity<Void> addProject(@Valid @RequestBody Project project, UriComponentsBuilder builder,@RequestHeader("Authorization") String token){
        project.updateOnCreation(authorizationService.getUsernameFromToken(token));
        projectService.add(project);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/project/{id}").buildAndExpand(project.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

//    @PatchMapping("/{name}")
//    public ResponseEntity<Void> editProjectName(@PathVariable String name,@RequestBody Project project){
//        log.info("PATCH IS CALLLEDD");
//        Project curr = projectService.findByName(name).get(0);
//        curr.setName(project.getName());
//        projectService.add(curr);
//        return new ResponseEntity<Void>(HttpStatus.CREATED);
//    }


    @PatchMapping("/{projectId}")
    public ResponseEntity<Project>updateNameOfProject(@PathVariable String projectId, @RequestBody String name, @RequestHeader("Authorization") String token) {
        log.info("APPELL");
        try {
            projectService.updateNameOfProject(projectId,name,token);  //it saved the new name in the database
            List<Project> proj=projectService.findByName(name);
            return ResponseEntity.ok(proj.get(0));
        }catch(ProjectNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }


}
