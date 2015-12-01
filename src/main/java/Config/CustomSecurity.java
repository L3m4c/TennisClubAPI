package Config;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class CustomSecurity extends WebSecurityConfigurerAdapter {

    @Resource
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication() //TODO BDD auth
                .withUser("test")  // #1
                .password("test")
                .roles("USER")
                .and()
                .withUser("admin") // #2
                .password("password")
                .roles("ADMIN", "USER");
        /*auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "Select email, password, true as enabled from User where email=?")
                .authoritiesByUsernameQuery(
                        "Select email, role From User where email=?");*/

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**"); // #3
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/signup", "/about", "/login").permitAll() // #4
                .antMatchers("/admin/**").hasRole("ADMIN") // #6
                .anyRequest().authenticated() // 7
                .and()
                .httpBasic(); // #5
    }
}
