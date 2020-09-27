package springSecurity.config;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.security.web.session.HttpSessionCreatedEvent;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;

@Configuration
public class CustomSessionListner implements SmartApplicationListener {
	private static final Logger LOG = LoggerFactory.getLogger(CustomSessionListner.class);

	private final AtomicInteger counter = new AtomicInteger();

	protected void sessionCreated(HttpSession se) {
		LOG.info("New session is created. Adding Session to the counter.");
		counter.incrementAndGet(); // incrementing the counter
		updateSessionCounter(se);
	}

	protected void sessionDestroyed(HttpSession se) {

		LOG.info("Session destroyed. Removing the Session from the counter.");
		counter.decrementAndGet(); // decrementing counter
		updateSessionCounter(se);
	}

	private void updateSessionCounter(HttpSession se) {
		// Let's set in the context
		se.getServletContext().setAttribute("activeSession", counter.get());
		LOG.info("Total active session are {} ", counter.get());
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof HttpSessionCreatedEvent) {
			this.sessionCreated(((HttpSessionCreatedEvent) event).getSession());
		}
		if (event instanceof HttpSessionDestroyedEvent) {
			this.sessionDestroyed(((HttpSessionDestroyedEvent) event).getSession());
		}
	}

	@Override
	public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
		return (HttpSessionCreatedEvent.class.isAssignableFrom(eventType)
				|| HttpSessionDestroyedEvent.class.isAssignableFrom(eventType));
	}
}
