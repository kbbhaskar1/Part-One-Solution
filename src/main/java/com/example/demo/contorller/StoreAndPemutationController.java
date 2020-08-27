package com.example.demo.contorller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.InMemoryStorageService;

@RestController
public class StoreAndPemutationController {

	@Autowired
	InMemoryStorageService store;

	long counter = 0;

	@PostMapping("/store")
	public Long store(@RequestParam(name = "numbers", required = true) String input) throws Exception {

		try {
			if (input != null && !StringUtils.isEmpty(input)) {

				Integer[] arr = Arrays.stream(input.split(",")).map(String::trim).map(Integer::valueOf)
						.toArray(Integer[]::new);
				store.getLocalStore().put(++counter, arr);

				return counter;

			} else
				throw new Exception();

		} catch (Throwable e) {

			throw new Exception();
		}

	}

	@GetMapping("/permutation")
	public String getPermutation(@RequestParam(name = "id", required = true) Long id) throws Exception {

		if (null == id)
			throw new Exception();

		try {

			List<Integer> permutation = Arrays.asList(store.getLocalStore().get(id));
			Collections.shuffle(permutation);
			return permutation.toString().replace("[", "") // remove the right bracket
					.replace("]", "") // remove the left bracket
					.replaceAll("\\s+", "").trim();

		} catch (Throwable e) {

			return "";
		}

	}
}
