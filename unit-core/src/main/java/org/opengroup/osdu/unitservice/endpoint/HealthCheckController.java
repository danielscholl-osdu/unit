package org.opengroup.osdu.unitservice.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/_ah")
public class HealthCheckController {

	@GetMapping("/liveness_check")
	public ResponseEntity livenessCheck() {
		return ResponseEntity.ok().build();
	}

	@GetMapping("/readiness_check")
	public ResponseEntity readinessCheck() {
		return ResponseEntity.ok().build();
	}
}
