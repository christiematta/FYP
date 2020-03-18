package murex.dev.mxem.Environments.service;

import murex.dev.mxem.Environments.exception.ProjectNotFoundException;
import murex.dev.mxem.Environments.model.Project;
import murex.dev.mxem.Environments.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ProjectService implements IProjectService{
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    AuthorizationService authorizationService;

    public Project add(Project project){
        projectRepository.save(project);
        return project;
    }

    public Project findById(String id){
        Project proj = projectRepository.findById(id).get();
        return proj;
    }

    public List<Project> findByName(String id){
        List<Project> projs = projectRepository.findByName(id);
        return projs;
    }

    public List<Project> findAll(){
        return projectRepository.findAll();
    }

    public Project updateNameOfProject(String name, String newName, String token) throws ProjectNotFoundException {
        Optional<Project> proj = projectRepository.findById(name);
        if(proj.get()==null){
            throw new ProjectNotFoundException();
        }
        Project project = proj.get();
        project.setName(newName);
        project.setModifiedBy(authorizationService.getUsernameFromToken(token));
        project.setModifiedOn(new Date());
        projectRepository.save(project);
        return project;
    }

}
