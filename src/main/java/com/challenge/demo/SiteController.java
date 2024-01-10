package com.challenge.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/sites")
public class SiteController {

	@Autowired
	SiteRepository siteRepository;

	// Handles POST requests to '/sites' with method createSite(@RequestBody Site createSite). It creates new Site object with random UUID and saves it to the database + returns the saved Site object.
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Site createSite(@RequestBody Site createSite) {
		createSite.setSiteUUID(UUID.randomUUID());

		return siteRepository.save(createSite);
	}

	// Handles GET requests to '/sites' with method getSites(). It retives all Site objects from the database and returns them + if no Site object is found, it returns 404.
	@GetMapping()
	public ResponseEntity<List<Site>> getSites() {
		return Optional
				.ofNullable(siteRepository.findAll())
				.map(sites -> ResponseEntity.ok(sites))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Handles PUT requests to '/sites/{id}' with method updateSite(@RequestBody Site updatedSite, @PathVariable(value = "id") Long siteId). It udates the Site object with the given id and returns the updated Site object.
	// If no Site object is found, it returns 404.
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Site> updateSite(@RequestBody Site updatedSite, @PathVariable(value = "id") Long siteId) {
		return siteRepository
				.findById(siteId)
				.map(site -> {
					site.setUrl(updatedSite.getUrl());
					return new ResponseEntity<>(siteRepository.save(site), HttpStatus.OK);
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Handles Delete requests to '/sites/{id}' with method deleteSite(@PathVariable(value = "id") Long siteId). Deletes the Site object with the given id and returns the deleted Site object
	// If no Site object is found, it returns 404.
	@DeleteMapping("/{id}")
	public ResponseEntity<Site> deleteSite(@PathVariable(value = "id") Long siteId) {
		return siteRepository
				.findById(siteId)
				.map(site -> {
					siteRepository.delete(site);
					return ResponseEntity.ok(site);
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Handles GET requests to 	'/sites/{id}' with method getSiteById(@PathVariable(value = "id") Long siteId). Retrieves the Site object with the given id and returns it.
	// If no Site object is found, it returns 404.
	@GetMapping("/{id}")
	public ResponseEntity<Site> getSiteById(@PathVariable(value = "id") Long siteId) {
		return siteRepository
				.findById(siteId)
				.map(site -> ResponseEntity.ok(site))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
}
