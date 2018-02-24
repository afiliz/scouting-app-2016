package com.scouting2016.tko;

import com.scouting2016.tko.data.DataViewServlet;
import com.scouting2016.tko.data.DataVisualizerServlet;
import com.scouting2016.tko.data.RobotViewServlet;
import com.scouting2016.tko.eventCodes.CodesUpdateServlet;
import com.scouting2016.tko.matchSchedule.ScheduleUpdateServlet;
import com.scouting2016.tko.robotData.DataCollectionServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Created by rahul on 2/27/16.
 */
public class Main {

    public enum Mode {
        DEV,
        PRODUCTION
    }

    public static void main(String[] args) throws InterruptedException {
        Mode mode = Mode.PRODUCTION;

        if (args.length > 0) {
            mode = Mode.valueOf(args[0].toUpperCase());
        }

        System.out.printf("Running in %s mode\n", mode);

        // 1. Allocate the server in the Jetty container
        Server server = configureServer(mode, new ConfigBean(mode));
        // 2. Creating the WebAppContext for the created content
        WebAppContext ctx = new WebAppContext();
        if (mode.equals(Mode.DEV)) {
            ctx.setResourceBase("src/main/webapp");
        } else {
            ctx.setResourceBase(".");
        }
        ctx.setContextPath("/");

        // 3. Including the JSTL jars for the webapp.
        ctx.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",".*/[^/]*jstl.*\\.jar$");

        ctx.setParentLoaderPriority(true);

        ctx.setWelcomeFiles(new String[] {"index.jsp"});
        ctx.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");
        ctx.setClassLoader(Thread.currentThread().getContextClassLoader());

        ctx.setContextPath("/");
        ctx.addServlet(DataCollectionServlet.class, "/collect");
        ctx.addServlet(CodesUpdateServlet.class, "/events");
        ctx.addServlet(ScheduleUpdateServlet.class, "/schedule");
        ctx.addServlet(DataVisualizerServlet.class, "/data");
        ctx.addServlet(DataViewServlet.class, "/view");
        ctx.addServlet(RobotViewServlet.class, "/robot");

        ctx.addServlet(DefaultServlet.class, "/");

        // 4. Enabling the Annotation based configuration
        org.eclipse.jetty.webapp.Configuration.ClassList classlist = org.eclipse.jetty.webapp.Configuration.ClassList.setServerDefault(server);
        classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration", "org.eclipse.jetty.plus.webapp.EnvConfiguration", "org.eclipse.jetty.plus.webapp.PlusConfiguration");
        classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration", "org.eclipse.jetty.annotations.AnnotationConfiguration");

        // 5. Setting the handler and starting the Server
        server.setHandler(ctx);
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        server.join();

    }

    //TODO ServerContextHandler should be unnecessary
    private static Server configureServer(Mode mode, ConfigBean bean) {
        ServletContextHandler servletHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletHandler.setAttribute("config", bean);

        servletHandler.setContextPath("/");
        servletHandler.addServlet(DataCollectionServlet.class, "/collect");
        servletHandler.addServlet(CodesUpdateServlet.class, "/events");
        servletHandler.addServlet(ScheduleUpdateServlet.class, "/schedule");
        servletHandler.addServlet(DataVisualizerServlet.class, "/data");
        servletHandler.addServlet(DataViewServlet.class, "/view");
        servletHandler.addServlet(RobotViewServlet.class, "/robot");

        servletHandler.addServlet(DefaultServlet.class, "/");
        if (mode.equals(Mode.DEV)) {
            servletHandler.setResourceBase("src/main/webapp");
        } else {
            servletHandler.setResourceBase(".");
        }
        servletHandler.setWelcomeFiles(new String[] {"index.jsp"});
        servletHandler.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");
        servletHandler.setClassLoader(Thread.currentThread().getContextClassLoader());

        Server server = new Server(bean.PORT);

        server.setHandler(servletHandler);

        return server;
    }
}
