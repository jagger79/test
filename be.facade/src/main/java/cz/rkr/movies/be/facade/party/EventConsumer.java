package cz.rkr.movies.be.facade.party;

import java.util.Collection;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventConsumer {

	@EventListener
	@Order(40)
	public Collection<String> onApplicationEvent(ApplicationEvent event) {
		System.out.format("onApplicationEvent %s\n", this);

		if (event instanceof PayloadApplicationEvent) {
			PayloadApplicationEvent<?> payload = (PayloadApplicationEvent<?>) event;
			System.out.format("EVENT %s\n", payload.getPayload());
			return null;// Arrays.asList("POM ", "POMO");
		}
		Object obj = event.getSource();
		System.out.format("EVENT %s\n", event);
		return null;// not fire event
	}
}
