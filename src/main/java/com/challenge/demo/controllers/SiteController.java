package com.challenge.demo.controllers;

import com.challenge.demo.entities.Site;
import com.challenge.demo.services.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/sites")
public class SiteController {

	@Autowired
	private final SiteService siteService;

	@Autowired
	public SiteController(SiteService siteService) {
		this.siteService = siteService;
	}

	// Handles POST requests to '/sites' with method createSite(@RequestBody Site createSite). It creates new Site object with random UUID and saves it to the database (as i dont have fake url)+ returns the saved Site object.
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Site createSite(@RequestBody Site createSite) {
		return siteService.save(createSite);
	}

	// Handles GET requests to '/sites' with method getSites(). It relives all Site objects from the database and returns them + if no Site object is found, it returns 404.
	@GetMapping()
	public ResponseEntity<List<Site>> getSites() {
		return Optional
				.ofNullable(siteService.getAllSites())
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Handles PUT requests to '/sites/{id}' with method updateSite(@RequestBody Site updatedSite, @PathVariable(value = "id") Long id). It udates the Site object with the given id and returns the updated Site object.
	// If no Site object is found, it returns 404.
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Site> updateSite(@RequestBody Site updatedSite, @PathVariable(value = "id") Long id) {
		return siteService
				.findById(id)
				.map(site -> {
					site.setUrl(updatedSite.getUrl());
					return new ResponseEntity<>(siteService.save(site), HttpStatus.OK);
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Handles Delete requests to '/sites/{id}' with method deleteSite(@PathVariable(value = "id") Long id). Deletes the Site object with the given id and returns the deleted Site object
	// If no Site object is found, it returns 404.
	@DeleteMapping("/{id}")
	public ResponseEntity<Site> deleteSite(@PathVariable(value = "id") Long id) {
		return siteService
				.findById(id)
				.map(site -> {
					siteService.delete(site);
					return ResponseEntity.ok(site);
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Handles GET requests to 	'/sites/{id}' with method getSiteById(@PathVariable(value = "id") Long id). Retrieves the Site object with the given id and returns it.
	// If no Site object is found, it returns 404.
	@GetMapping("/{id}")
	public ResponseEntity<Site> getSiteById(@PathVariable(value = "id") Long id) {
		return siteService
				.findById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
}
