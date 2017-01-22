package com.uvlab.cloud.site.security.io;

import com.uvlab.cloud.site.security.JwtAuthenticationRequest;
import com.uvlab.cloud.site.security.Service.JwtAuthenticationResponse;
import com.uvlab.cloud.site.security.Service.JwtTokenService;
import com.uvlab.cloud.site.security.common.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/7/7 0007.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/token")
public class JwtTokenController {

    @Autowired
    private JwtTokenService tokensvc;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    //get the token if the uer information is right
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestParam(value = "username")String uesrname,
                                                       @RequestParam(value = "password")String password,
                                                       Device device) throws AuthenticationException {

        JwtAuthenticationRequest authenticationRequest=new JwtAuthenticationRequest(uesrname,password);

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = tokensvc.generateToken(userDetails, device);

        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    //Refresh the token
    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader("Token");
        String username = tokensvc.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (tokensvc.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = tokensvc.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
