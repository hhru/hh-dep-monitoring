package ru.hh.school.depmonitoring.rs;

import org.apache.commons.io.FileUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.Objects;

@Path("/bamboo")
public class BambooTestResource {
    @GET
    public Response getDependencies() throws Exception {
        var dependenciesFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("dependencies.json")).toURI());
        String dependenciesContent = FileUtils.readFileToString(dependenciesFile);
        return Response.ok(dependenciesContent).type(MediaType.APPLICATION_JSON).build();
    }
}
