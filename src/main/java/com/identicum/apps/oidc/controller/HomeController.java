package com.identicum.apps.oidc.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String greeting(Model model, @AuthenticationPrincipal OAuth2User oauth2User, @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
		model.addAttribute("accessToken",authorizedClient.getAccessToken().getTokenValue());
        model.addAttribute("idToken",oauth2User.getAttributes().get("idToken"));
		return "home";
	}

}