import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.Server;

import org.restlet.data.Protocol;

import org.restlet.routing.Extractor;
import org.restlet.routing.Redirector;
import org.restlet.routing.Router;

public class BookDepositoryApplication3 extends Application {

	public static void main(String[] args) {
		Component component = new Component();
		component.getDefaultHost().attach("/bookdepository", new BookDepositoryApplication3());
		Server server = new Server(Protocol.HTTP, 8111, component);
		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Restlet createInboundRoot() {
		Router router = new Router(getContext());
		router.setDefaultMatchingQuery(true);

		Redirector isbnRedirector = new Redirector(
			getContext(),
			"http://www.bookdepository.com/search/advanced?searchIsbn={isbn}",
			Redirector.MODE_CLIENT_FOUND
		);
		Extractor isbnExtractor = new Extractor(getContext(), isbnRedirector);
		isbnExtractor.extractFromEntity("isbn", "isbn", true);
		router.attach("/book/{isbn}", isbnExtractor);

		Redirector searchRedirector = new Redirector(
			getContext(),
			"http://www.bookdepository.com/search?searchTerm={searchTerm}&search=Find+book",
			Redirector.MODE_CLIENT_FOUND
		);
		Extractor searchExtractor = new Extractor(getContext(), searchRedirector);
		searchExtractor.extractFromQuery("searchTerm", "searchTerm", true);
		router.attach("/search?{query}", searchExtractor);

		return router;
	}

}