package com.yqz.springboot.quickstart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yqz.springboot.quickstart.model.jpa.Owner;
import com.yqz.springboot.quickstart.repository.OwnerReposity;
import com.yqz.springboot.quickstart.resource.OwnerResource;
import com.yqz.springboot.quickstart.resource.OwnerResourceAssembler;

@RestController
@RequestMapping("/owner")
public class OwnerController {
	private static final Logger logger = LoggerFactory.getLogger(OwnerController.class);
	@Autowired
	private OwnerReposity ownerReposity;

	@RequestMapping("list")
	public Resources<OwnerResource> getAll() {
		List<Owner> owners = ownerReposity.findAll();

		return new Resources<OwnerResource>(new OwnerResourceAssembler().toResources(owners));
		// if (owners != null) {
		// List<OwnerResource> list = owners.stream().map(p -> new
		// OwnerResource(p)).collect(Collectors.toList());
		// return new Resources<OwnerResource>(list);
		// }
		// return new Resources<OwnerResource>(new ArrayList<OwnerResource>());

	}

	@RequestMapping("/{id}")
	public OwnerResource getById(@PathVariable int id) {
		Owner o = ownerReposity.findOne(1);
		return new OwnerResourceAssembler().toResource(o);

	}

	@RequestMapping("/name/{namelike}")
	public List<Owner> getByOwnerNameLike(@PathVariable String namelike) {
		return ownerReposity.findByOwnerNameLike(namelike);
	}
}
