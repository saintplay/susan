package launch;

import java.io.File;

import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.EmptyResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.WebResourceSet;

public class Main {
	public static void main(String[] args) throws Exception {

     // Define a folder to hold web application contents.
        String webappDirLocation = "WebContent/";
        Tomcat tomcat = new Tomcat();
 
        // Define port number for the web application
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "5000";
        }
        // Bind the port to Tomcat server
        tomcat.setPort(Integer.valueOf(webPort));
        tomcat.getConnector().setURIEncoding("UTF-8");
 
        // Define a web application context.
        Context context = tomcat.addWebapp("", new File(
                webappDirLocation).getAbsolutePath());

        // Declare an alternative location for your "WEB-INF/classes" dir
        // Servlet 3.0 annotation will work
        File additionWebInfClassesFolder = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(context);
        
        WebResourceSet resourceSet;
        if (additionWebInfClassesFolder.exists()) {
            resourceSet = new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClassesFolder.getAbsolutePath(), "/");
            System.out.println("loading WEB-INF resources from as '" + additionWebInfClassesFolder.getAbsolutePath() + "'");
        } else {
            resourceSet = new EmptyResourceSet(resources);
        }
       
        resources.addPreResources(resourceSet);
        context.setResources(resources);
 
        tomcat.start();
        tomcat.getServer().await();
    }
}
