package hu.thesis.baseshop.authorization.controller;

import hu.thesis.baseshop.authorization.entity.JwtRequest;
import hu.thesis.baseshop.authorization.entity.JwtResponse;
import hu.thesis.baseshop.authorization.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @CrossOrigin(origins = "https://www.baseshop.hu")
    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }

}
