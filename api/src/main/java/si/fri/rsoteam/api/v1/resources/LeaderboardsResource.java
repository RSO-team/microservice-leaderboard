package si.fri.rsoteam.api.v1.resources;

import si.fri.rsoteam.lib.dtos.LeaderboardDto;
import si.fri.rsoteam.services.beans.LeaderboardsBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/leaderboards")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LeaderboardsResource {

    private Logger log = Logger.getLogger(LeaderboardsResource.class.getName());

    @Inject
    private LeaderboardsBean leaderboardsBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getLeaderboards() {
        return Response.ok(leaderboardsBean.getAllLeaderboards()).build();
    }

    @GET
    @Path("/{objectId}")
    public Response getLeaderboardById(@PathParam("objectId") Integer id) {
        return Response.ok(leaderboardsBean.getLeaderboard(id)).build();
    }

    @POST
    public Response createLeaderboard(LeaderboardDto leaderboardDto) {
        return Response.status(201).entity(leaderboardsBean.createLeaderboard(leaderboardDto)).build();
    }

    @PUT
    @Path("{objectId}")
    public Response updateLeaderboard(@PathParam("objectId") Integer id, LeaderboardDto eventDto) {
        return Response.status(201).entity(leaderboardsBean.updateLeaderboard(eventDto, id)).build();
    }

    @DELETE
    @Path("{objectId}")
    public Response deleteEvent(@PathParam("objectId") Integer id) {
        leaderboardsBean.deleteLeaderboard(id);
        return Response.status(204).build();
    }
}
