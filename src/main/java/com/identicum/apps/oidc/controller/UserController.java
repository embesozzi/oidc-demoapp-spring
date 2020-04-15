package com.identicum.apps.oidc.controller; 

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mbesozzi
 */
// @Controller
@RestController
// @RequestMapping("/")
public class UserController {

    @GetMapping("/")
    public ResponseEntity<Map<String,Object>> index(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
						@AuthenticationPrincipal OAuth2User oauth2User) {
		Map<String,Object> info= new HashMap<>();
        info.put("attributes", oauth2User.getAttributes());
        info.put("access_token", authorizedClient.getAccessToken());
        info.put("client", authorizedClient.getClientRegistration());
        return ResponseEntity.ok(info);
	}

	@GetMapping("/me")
	public ResponseEntity<OAuth2AuthenticationToken> me(OAuth2AuthenticationToken token) {
		return ResponseEntity.ok(token);
	}

    @GetMapping("/me/claims")
    public ResponseEntity<Map<String,Object>> claims()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof OidcUser) {
            OidcUser principal = ((OidcUser) authentication.getPrincipal());
            return ResponseEntity.ok(principal.getClaims());
        }
        return ResponseEntity.ok(Collections.emptyMap());
    }
}