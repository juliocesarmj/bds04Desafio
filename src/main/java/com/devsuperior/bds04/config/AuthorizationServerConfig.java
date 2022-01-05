package com.devsuperior.bds04.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Value("${security.oauth2.client.client-id}")
	private String clientId;

	@Value("${security.oauth2.client.client-secret}")
	private String clientSecret;

	@Value("${jwt.duration}")
	private Integer jwtDuration;

	private final BCryptPasswordEncoder passwordEncoder;
	private final JwtAccessTokenConverter accessTokenConverter;
	private final JwtTokenStore tokenStore;
	private final AuthenticationManager authenticationManager;

	@Autowired
	public AuthorizationServerConfig(final BCryptPasswordEncoder passwordEncoder,
			final JwtAccessTokenConverter accessTokenConverter, final JwtTokenStore tokenStore,
			final AuthenticationManager authenticationManager) {
		this.passwordEncoder = passwordEncoder;
		this.accessTokenConverter = accessTokenConverter;
		this.tokenStore = tokenStore;
		this.authenticationManager = authenticationManager;
	}

	@Override
	public void configure(final AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permiteAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(this.clientId).secret(this.passwordEncoder.encode(this.clientSecret))
				.scopes("read", "write").authorizedGrantTypes("password").accessTokenValiditySeconds(this.jwtDuration);
	}

	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		endpoints.authenticationManager(this.authenticationManager).tokenStore(this.tokenStore)
				.accessTokenConverter(this.accessTokenConverter);
	}
}
