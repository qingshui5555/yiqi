package com.health.api.file;

import com.health.api.BaseApi;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author henry
 */
@Component
@Path("/file")
@Api(value = "/file")
public class FileApi extends BaseApi {

    @GET
    @Path("download/app")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public byte[] downloadApp(@Context HttpServletRequest request, @Context HttpServletResponse response){
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream(new File("/home/centos/app/server.js"));
            byte[] b = new byte[fis.available()];
            fis.read(b);
            response.setHeader("Content-Disposition","attachment;filename=server.js");
            response.addHeader("content-type","application/pdf");
            return b;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
