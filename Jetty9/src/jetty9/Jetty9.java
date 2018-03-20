/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jetty9;

import com.duong.skill.thrift_connection.SkillClientManagerImpl;
import com.duong.skill.thrift_connection.SkillClientProvider;
import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.authentication.FormAuthenticator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.security.Constraint;

/**
 *
 * @author hoang
 */
public class Jetty9 {

    public static final String CONTEXT_PATH = "/example";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Server server = new Server(8080);
//        WebSocketHandler wsHandler = new WebSocketHandler() {
//            @Override
//            public void configure(WebSocketServletFactory factory) {
//                factory.register(MyWebSocketHandler.class);
//            }
//        };
//        server.setHandler(wsHandler);
//        server.start();
//        server.join();

        SkillClientProvider skillClientProvider = new SkillClientManagerImpl("localhost", 8888);

        ServletContextHandler context = new ServletContextHandler(server, CONTEXT_PATH, ServletContextHandler.SECURITY);
        context.addServlet(new ServletHolder(new HelloServlet()), "/login");

        context.addServlet(new ServletHolder(new GoodbyeServlet()), "/bye");
        context.addServlet(new ServletHolder(new SkillServlet(skillClientProvider)), "/skill");
        context.addServlet(new ServletHolder(new SkillServlet(skillClientProvider)), "/skill/*");

        Constraint constraint = new Constraint();
        constraint.setName(Constraint.__FORM_AUTH);
        constraint.setRoles(new String[]{"user", "admin", "moderator"});
        constraint.setAuthenticate(true);

        ConstraintMapping constraintMapping = new ConstraintMapping();
        constraintMapping.setConstraint(constraint);
        constraintMapping.setPathSpec("/*");

        ConstraintSecurityHandler securityHandler = new ConstraintSecurityHandler();
        securityHandler.addConstraintMapping(constraintMapping);
        HashLoginService loginService = new HashLoginService();
        //loginService.putUser("username", new Password("password"), new String[] {"user"});
        securityHandler.setLoginService(loginService);

        FormAuthenticator authenticator = new FormAuthenticator("/login", "/login", false);
        securityHandler.setAuthenticator(authenticator);
        context.setSecurityHandler(securityHandler);
        server.start();
        server.join();
    }

}
