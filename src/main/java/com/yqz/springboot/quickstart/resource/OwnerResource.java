package com.yqz.springboot.quickstart.resource;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;

import com.yqz.springboot.quickstart.model.jpa.Owner;

@XmlRootElement
public class OwnerResource extends ResourceSupport {

	private final Owner owner;

	public OwnerResource(Owner o) {
		this.owner = o;
		// add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(OwnerController.class).getById(o.getOwnerId()))
		// .withSelfRel());
	}

	public Owner getOwner() {
		return owner;
	}

}
