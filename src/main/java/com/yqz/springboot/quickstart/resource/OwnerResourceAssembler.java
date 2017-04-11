package com.yqz.springboot.quickstart.resource;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.yqz.springboot.quickstart.controller.OwnerController;
import com.yqz.springboot.quickstart.model.jpa.Owner;

public class OwnerResourceAssembler extends ResourceAssemblerSupport<Owner, OwnerResource> {

	public OwnerResourceAssembler() {
		super(OwnerController.class, OwnerResource.class);
	}

	@Override
	public OwnerResource toResource(Owner o) {
		OwnerResource resource = createResourceWithId(o.getOwnerId(), o);
		return resource;
	}

	@Override
	protected OwnerResource instantiateResource(Owner entity) {
		return new OwnerResource(entity);
	}
}