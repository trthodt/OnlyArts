package com.cowards.onlyarts.resources.v1;

import com.cowards.onlyarts.services.ImageDAO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * This class represents the endpoints for image handling, including uploading
 * and retrieving images.
 */
@Path("v1/image")
public class Image {

    private static final ImageDAO imageDao = ImageDAO.getInstance();

    /**
     * Endpoint for uploading an image.
     *
     * @param imageData The image data as a string.
     * @return Response indicating success or failure of the upload operation.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadImage(String imageData) {
        imageData = imageData.replaceAll("\"", "");
        String imageId = imageDao.addImage(imageData);
        return Response.status(Response.Status.OK)
                .entity(imageId).build();
    }

    /**
     * Endpoint for retrieving an image.
     *
     * @param imageId The unique identifier of the image.
     * @return Response containing the image data.
     */
    @GET
    @Path("{imageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getImage(@PathParam("imageId") String imageId) {
        String imageData = imageDao.getImage(imageId);
        return Response.status(Response.Status.OK)
                .entity(imageData).build();
    }

}
