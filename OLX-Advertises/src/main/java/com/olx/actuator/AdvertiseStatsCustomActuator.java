package com.olx.actuator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import com.olx.repository.AdvertiseRepository;

@Component
@Endpoint(id = "advertise-stats")
public class AdvertiseStatsCustomActuator {

	@Autowired
	AdvertiseRepository advertiseRepository;
	Map<String, String> advertiseStats = new HashMap<>();

	Map<String, String> advertiseCntByuser = new HashMap<>();

	@PostConstruct
	public void initialize() {
		String advertisesCnt = advertiseRepository.findNoOfAdvertises();
		String openAdvertisesCnt = advertiseRepository.findOpenAdvertises();
		String closedAdvertisesCnt = advertiseRepository.findClosedAdvertises();

		advertiseCntByuser = advertiseRepository.findFirstUserAdvertisesCnt().stream()
				.collect(Collectors.toMap((arr -> arr[0].toString()), (arr -> arr[1].toString())));

		Set<String> keySet = advertiseCntByuser.keySet();
		String userDetails = null;
		StringBuilder userDetailsSb = new StringBuilder();

		for (String val : keySet) {
			userDetails = userDetailsSb.append(val).append(": ").append(advertiseCntByuser.get(val)).append(", ")
					.toString();
		}

		if (userDetails != null) {
			userDetails = "({" + userDetails.replaceAll(", $", "") + "})";
		} else {
			userDetails = "({" + "})";
		}

		advertiseStats.put("No of advertises", advertisesCnt);
		advertiseStats.put("No of CLOSED advertises", closedAdvertisesCnt);
		advertiseStats.put("No of OPEN advertises", openAdvertisesCnt);
		advertiseStats.put("No of Advertises by username", userDetails);
	}

	@ReadOperation
	public Map<String, String> getAdvertiseStats() {

		return advertiseStats;
	}

}
