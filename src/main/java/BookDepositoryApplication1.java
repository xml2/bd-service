import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.Server;

import org.restlet.data.Protocol;

import org.restlet.routing.Router;

public class BookDepositoryApplication1 extends Application {

	static {
		System.setProperty("org.restlet.engine.loggerFacadeClass", "org.restlet.ext.slf4j.Slf4jLoggerFacade");
	}

	public static void main(String[] args) {
		Server server = new Server(Protocol.HTTP, 8111, new BookDepositoryApplication1());
		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Restlet createInboundRoot() {
		Router router = new Router(getContext());
		router.setDefaultMatchingQuery(true);
		router.attach("http://localhost:8111/book/{isbn}", BookResource.class);
		router.attach("http://localhost:8111/search?{query}", SearchResource.class);
		return router;
	}

}