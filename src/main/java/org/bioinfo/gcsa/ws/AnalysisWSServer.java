package org.bioinfo.gcsa.ws;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.bioinfo.gcsa.lib.analysis.AnalysisJobExecuter;
import org.bioinfo.gcsa.lib.users.persistence.UserManagementException;

@Path("/analysis")
public class AnalysisWSServer extends GenericWSServer {
	AnalysisJobExecuter aje;
	String baseUrl;

	public AnalysisWSServer(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest)
			throws IOException {
		super(uriInfo, httpServletRequest);
		baseUrl = uriInfo.getBaseUri().toString();
	}

	@GET
	@Path("/{analysis}")
	public Response help1(@DefaultValue("") @PathParam("analysis") String analysis) {
		try {
			aje = new AnalysisJobExecuter(analysis);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UserManagementException e) {
			e.printStackTrace();
		}
		return createOkResponse(aje.help(baseUrl));
	}

	@GET
	@Path("/{analysis}/help")
	public Response help2(@DefaultValue("") @PathParam("analysis") String analysis) {
		try {
			aje = new AnalysisJobExecuter(analysis);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UserManagementException e) {
			e.printStackTrace();
		}
		return createOkResponse(aje.help(baseUrl));
	}

	@GET
	@Path("/{analysis}/params")
	public Response showParams(@DefaultValue("") @PathParam("analysis") String analysis) {
		try {
			aje = new AnalysisJobExecuter(analysis);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UserManagementException e) {
			e.printStackTrace();
		}
		return createOkResponse(aje.params());
	}

	@GET
	@Path("/{analysis}/test")
	public Response test(@DefaultValue("") @PathParam("analysis") String analysis) {
		try {
			aje = new AnalysisJobExecuter(analysis);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UserManagementException e) {
			e.printStackTrace();
		}
		return createOkResponse(aje.test());
	}

	@GET
	@Path("/{analysis}/status")
	public Response status(@DefaultValue("") @PathParam("analysis") String analysis, @QueryParam("jobid") String jobId) {
		try {
			aje = new AnalysisJobExecuter();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UserManagementException e) {
			e.printStackTrace();
		}

		return createOkResponse(aje.status(jobId));
	}

	@GET
	@Path("/{analysis}/run")
	public Response analysisGet(@DefaultValue("") @PathParam("analysis") String analysis) {
		try {
			aje = new AnalysisJobExecuter(analysis);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UserManagementException e) {
			e.printStackTrace();
		}
		// MultivaluedMap<String, String> params =
		// this.uriInfo.getQueryParameters();
		System.out.println("**GET executed***");
		System.out.println("get params: " + params);
		// params.add("analysis", analysis);

		return this.analysis(params);
	}

	@POST
	@Path("/{analysis}/run")
	@Consumes({ MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_FORM_URLENCODED })
	public Response analysisPost(@DefaultValue("") @PathParam("analysis") String analysis,
			MultivaluedMap<String, String> postParams) {
		try {
			aje = new AnalysisJobExecuter(analysis);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UserManagementException e) {
			e.printStackTrace();
		}
		System.out.println("**POST executed***");
		System.out.println("post params: " + postParams);
		// params.add("analysis", analysis);

		return this.analysis(postParams);
	}

	private Response analysis(MultivaluedMap<String, String> params) {
		// System.out.println("params: "+params.toString());
		// Map<String, List<String>> paramsMap = params;

		String jobId = "";
		// String jobId = execute("SW","HPG.SW", dataIds, params, "-d");
		jobId = aje.execute(params);

		return createOkResponse(jobId);
	}
}
