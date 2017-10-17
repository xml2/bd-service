import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.Server;

import org.restlet.data.Protocol;

import org.restlet.routing.Router;

public class BookDepositoryApplication2 extends Application {

	static {
		System.setProperty("org.restlet.engine.loggerFacadeClass", "org.restlet.ext.slf4j.Slf4jLoggerFacade");
	}

	public static void main(String[] args) {
		Component component = new Component();
		component.getDefaultHost().attach("/bookdepository", new BookDepositoryApplication2());
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
		router.attach("/book/{isbn}", BookResource.class);
		router.attach("/search?{query}", SearchResource.class);
		return router;
	}

}