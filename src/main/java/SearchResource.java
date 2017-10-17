import java.io.IOException;

import org.restlet.data.Status;

import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import hu.unideb.inf.bd.model.SearchResults;

import hu.unideb.inf.bd.search.SimpleSearch;

public class SearchResource extends ServerResource {

	@Get("json|xml")
	public SearchResults represent() throws IOException {
		String searchTerm = getQueryValue("searchTerm");
		if (searchTerm == null) {
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Required parameter 'searchTerm' is missing");
		}
		return new SimpleSearch().doSearch(searchTerm);
	}

}
