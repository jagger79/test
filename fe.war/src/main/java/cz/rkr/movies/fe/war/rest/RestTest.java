package cz.rkr.movies.fe.war.rest;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = { "/" })
public class RestTest {

	@RequestMapping()
	public ResponseEntity<Object> getRoot() {
		return ResponseEntity.accepted().body("<html><body>WORKS</body></html>");
	}

	@RequestMapping(path = { "/sub" })
	public ResponseEntity<Object> getSubroot() {
		return ResponseEntity.accepted().body("");
	}

	@EventListener
	@Cacheable
	public void test(String param) {
	}

}
