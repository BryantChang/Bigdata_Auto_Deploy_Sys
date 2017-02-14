package resource;

import bean.Response;
import constants.Constants;
import service.NodeOpsService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by bryantchang on 2016/12/27.
 * 节点操作接口(控制节点启动停止,查看节点状态)
 * /nodeops
 */
@Singleton
@Path("/nodeops")
public class NodeOpsResource {
    @GET
    @Path("/spark/startmaster")
    @Produces(MediaType.APPLICATION_JSON)
    public Response startSparkMaster() {
        String res = new NodeOpsService().startSparkMaster();
        Response response = null;
        if(res.equals("error")) {
            response = new Response(Constants.ERR, "script exec error");
        }else {
            response = new Response(Constants.SUCC, "ops succ");
        }

        return response;
    }

    @GET
    @Path("/spark/stopmaster")
    @Produces(MediaType.APPLICATION_JSON)
    public Response stopSparkMaster() {
        String res = new NodeOpsService().stopSparkMaster();
        Response response = null;
        if(res.equals("error")) {
            response = new Response(Constants.ERR, "script exec error");
        }else {
            response = new Response(Constants.SUCC, "ops succ");
        }
        return response;
    }

    @GET
    @Path("/spark/startslave")
    @Produces(MediaType.APPLICATION_JSON)
    public Response startSparkSlave(@QueryParam("master") String master,
                                    @DefaultValue("7077") @QueryParam("port") String sparkMasterPort) {
        Response response = null;
        if(master.equals("") || master == null) {
            return new Response(Constants.ERR, "invalid master");
        }else {
            String res = new NodeOpsService().startSparkSlave(master, sparkMasterPort);
            if(res.equals("error")) {
                response = new Response(Constants.ERR, "script exec error");
            }else {
                response = new Response(Constants.SUCC, "ops succ");
            }
            return response;
        }
    }


    @GET
    @Path("/spark/stopslave")
    @Produces(MediaType.APPLICATION_JSON)
    public Response stopSparkSlave() {
        String res = new NodeOpsService().stopSparkSlave();
        Response response = null;
        if(res.equals("error")) {
            response = new Response(Constants.ERR, "script exec error");
        }else {
            response = new Response(Constants.SUCC, "ops succ");
        }
        return response;
    }

    @GET
    @Path("/spark/startcluster")
    @Produces(MediaType.APPLICATION_JSON)
    public Response startSparkCluster() {
        String res = new NodeOpsService().startSparkCluster();
        Response response = null;
        if(res.equals("error")) {
            response = new Response(Constants.ERR, "script exec error");
        }else {
            response = new Response(Constants.SUCC, "ops succ");
        }
        return response;
    }


    @GET
    @Path("/spark/stopcluster")
    @Produces(MediaType.APPLICATION_JSON)
    public Response stopSparkCluster() {
        String res = new NodeOpsService().stopSparkCluster();
        Response response = null;
        if(res.equals("error")) {
            response = new Response(Constants.ERR, "script exec error");
        }else {
            response = new Response(Constants.SUCC, "ops succ");
        }
        return response;
    }

    @GET
    @Path("/checknode")
    @Produces(MediaType.APPLICATION_JSON)
    public Response isRunning(@QueryParam("type") String type) {
        Response response = null;
        String nodeType = (String)Constants.nodeTypeMap.get(type);
        if(nodeType == null) {
            response = new Response(Constants.ERR, "invalid node type");
        }else {
            String res  = new NodeOpsService().check(nodeType);
            if(res.equals("error")) {
                response = new Response(Constants.ERR, "query error");
            }else {
                if(res.equals("")) {
                    response = new Response(Constants.SUCC, "not running");
                }else {
                    response = new Response(Constants.SUCC, "running");
                }
            }
        }

        return response;
    }


    @GET
    @Path("/hadoop/hdfs/ops")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hadoopHDFSNodeOps(@QueryParam("nodetype") String nodeType,
                                  @QueryParam("opstype") String opsType) {
        Response response = null;
        if((!opsType.equals("start")) && (!opsType.equals("stop"))) {
            response = new Response(Constants.ERR, "invalid ops type");
        }else {
            String res = new NodeOpsService().hadoopHDFSNodeOps(nodeType, opsType);
            if(res.equals("error")) {
                response = new Response(Constants.ERR, "exec error");
            }else {
                if(res.equals("")) {
                    response = new Response(Constants.SUCC, "ops exec err");
                }else {
                    response = new Response(Constants.SUCC, "ops exec succ");
                }
            }
        }
        return response;
    }

    @GET
     @Path("/hadoop/hdfs/startcluster")
     @Produces(MediaType.APPLICATION_JSON)
     public Response startHDFSCluster() {
        String res = new NodeOpsService().startHDFSCluster();
        Response response = null;
        if(res.equals("error")) {
            response = new Response(Constants.ERR, "script exec error");
        }else {
            response = new Response(Constants.SUCC, "ops succ");
        }
        return response;
    }

    @GET
    @Path("/hadoop/hdfs/stopcluster")
    @Produces(MediaType.APPLICATION_JSON)
    public Response stopHDFSCluster() {
        String res = new NodeOpsService().stopHDFSCluster();
        Response response = null;
        if(res.equals("error")) {
            response = new Response(Constants.ERR, "script exec error");
        }else {
            response = new Response(Constants.SUCC, "ops succ");
        }
        return response;
    }


    @GET
    @Path("/hadoop/yarn/ops")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hadoopYARNNodeOps(@QueryParam("nodetype") String nodeType,
                                      @QueryParam("opstype") String opsType) {
        Response response = null;
        if((!opsType.equals("start")) && (!opsType.equals("stop"))) {
            response = new Response(Constants.ERR, "invalid ops type");
        }else {
            String res = new NodeOpsService().hadoopYARNNodeOps(nodeType, opsType);
            if(res.equals("error")) {
                response = new Response(Constants.ERR, "exec error");
            }else {
                if(res.equals("")) {
                    response = new Response(Constants.SUCC, "ops exec err");
                }else {
                    response = new Response(Constants.SUCC, "ops exec succ");
                }
            }
        }
        return response;
    }


    @GET
    @Path("/hadoop/yarn/startcluster")
    @Produces(MediaType.APPLICATION_JSON)
    public Response startYARNCluster() {
        String res = new NodeOpsService().stopHDFSCluster();
        Response response = null;
        if(res.equals("error")) {
            response = new Response(Constants.ERR, "script exec error");
        }else {
            response = new Response(Constants.SUCC, "ops succ");
        }
        return response;
    }

    @GET
    @Path("/hadoop/yarn/stopcluster")
    @Produces(MediaType.APPLICATION_JSON)
    public Response stopYARNCluster() {
        String res = new NodeOpsService().stopYARNCluster();
        Response response = null;
        if(res.equals("error")) {
            response = new Response(Constants.ERR, "script exec error");
        }else {
            response = new Response(Constants.SUCC, "ops succ");
        }
        return response;
    }
}
