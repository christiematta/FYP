package murex.dev.mxem.Scheduler.controller;


import lombok.extern.slf4j.Slf4j;

import murex.dev.mxem.Scheduler.model.Request;
import murex.dev.mxem.Scheduler.service.AuthorizationService;
import murex.dev.mxem.Scheduler.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@Slf4j
@RestController
@Validated
@RefreshScope
@RequestMapping(value = "/requests")
public class SchedulerController {

    @Autowired
    RequestService requestService;

    @Autowired
    AuthorizationService authorizationService;

    @GetMapping
    public ResponseEntity<List<Request>> getAllRequests() {
        log.info("Calling get all environments");
        List<Request> reqs = requestService.findAll();
        log.info(reqs.toString());
        return ResponseEntity.ok(reqs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getEnvironmentDetails(@PathVariable String id) {
        Request req = requestService.findByOperationId(id);
        return ResponseEntity.ok(req);
    }

    @GetMapping("/environments/{envId}")
    public ResponseEntity<List<Request>> getRequestsForEnvironment(@PathVariable String envId, @RequestHeader("Authorization") String token){
log.info("CCCCCCCCCCCCCCCCCCCCCCCCC");
        List<Request> reqs = requestService.findByEnvironment(envId);
        List<Request> res = new ArrayList<>();
        String username = authorizationService.getUsernameFromToken(token);
        for(Request req : reqs){
            if(req.getUser().equals(username)){
                res.add(req);
            }
        }
        return ResponseEntity.ok(res);
    }



}
