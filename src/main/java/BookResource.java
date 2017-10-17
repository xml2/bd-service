import java.io.IOException;

import org.restlet.data.Status;

import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import hu.unideb.inf.bd.model.Book;

import hu.unideb.inf.bd.search.IsbnSearch;

public class BookResource extends ServerResource {

	@Get("json|xml")
	public Book represent() throws IOException {
		String isbn = getAttribute("isbn");
		Book book = new IsbnSearch().doSearch(isbn);
		if (book == null)
			throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);
		return book;
	}

}