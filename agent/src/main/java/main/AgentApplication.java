package main;

import resource.HadoopJobResource;
import resource.NodeInfoResource;
import resource.NodeOpsResource;
import resource.SparkJobResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bryantchang on 2016/12/27.
 */
@ApplicationPath("/")
public class AgentApplication extends Application{
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        // register root resource
        classes.add(NodeOpsResource.class);
        classes.add(NodeInfoResource.class);
        classes.add(HadoopJobResource.class);
        classes.add(SparkJobResource.class);
        return classes;
    }
}
