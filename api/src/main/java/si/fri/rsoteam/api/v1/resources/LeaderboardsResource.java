package si.fri.rsoteam.api.v1.resources;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
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
    @Operation(summary = "Get list of leaderboards", description = "Returns list of leaderboard enteries.")
    @APIResponses({
            @APIResponse(
                    description = "Returned successfully list leaderboard",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = LeaderboardDto.class, type = SchemaType.ARRAY)),
                    headers = {@Header(name = "X-Total-Count", description = "Number of objects in list")}
            )
    })
    public Response getLeaderboards() {
        return Response.ok(leaderboardsBean.getAllLeaderboards()).build();
    }

    @GET
    @Path("/{objectId}")
    @Operation(summary = "Get leaderboard by id.", description = "Returns specific leaderboard.")
    @APIResponses({
            @APIResponse(
                    description = "Returned successfully specific leaderboard entry",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = LeaderboardDto.class, type = SchemaType.ARRAY))
            )
    })
    public Response getLeaderboardById(@PathParam("objectId") Integer id) {
        return Response.ok(leaderboardsBean.getLeaderboard(id)).build();
    }

    @POST
    @Operation(summary = "Create new leaderboard entry ", description = "Create new leaderboard entry")
    @APIResponses({
            @APIResponse(
                    description = "Created successfully leaderboard entry",
                    responseCode = "201",
                    content = @Content(schema = @Schema(implementation = LeaderboardDto.class, type = SchemaType.ARRAY))
            )
    })
    public Response createLeaderboard(LeaderboardDto leaderboardDto) {
        return Response.status(201).entity(leaderboardsBean.createLeaderboard(leaderboardDto)).build();
    }

    @PUT
    @Path("{objectId}")
    @Operation(summary = "Update leaderboard.", description = "Updates specific leaderboard by id.")
    @APIResponses({
            @APIResponse(
                    description = "Updated successfully leaderboard entry",
                    responseCode = "201",
                    content = @Content(schema = @Schema(implementation = LeaderboardDto.class, type = SchemaType.ARRAY))
            )
    })
    public Response updateLeaderboard(@PathParam("objectId") Integer id, LeaderboardDto eventDto) {
        return Response.status(201).entity(leaderboardsBean.updateLeaderboard(eventDto, id)).build();
    }

    @DELETE
    @Path("{objectId}")
    @Operation(summary = "Delete leaderboard.", description = "Delete specific leaderboard by id.")
    @APIResponses({
            @APIResponse(
                    description = "Deleted successfully leaderboard entry",
                    responseCode = "204",
                    content = @Content(schema = @Schema(implementation = LeaderboardDto.class, type = SchemaType.ARRAY))
            )
    })
    public Response deleteEvent(@PathParam("objectId") Integer id) {
        leaderboardsBean.deleteLeaderboard(id);
        return Response.status(204).build();
    }
}
