package com.yqz.springboot.quickstart.repository;

//import java.util.List;
//
//import org.assertj.core.util.Lists;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.yqz.springboot.quickstart.Application;
//import com.yqz.springboot.quickstart.model.jpa.Owner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = Application.class)
//public class OwnerReposityTest {
//
//	@Autowired
//	private OwnerReposity ownerReposity;
//
//	@Test
//	public void testFindAll() {
//		Iterable<Owner> ite = ownerReposity.findAll();
//		// ArrayList<Owner> list = StreamSupport.stream(ite.spliterator(), true)
//		// .collect(Collectors.toCollection(ArrayList::new));
//		Assert.assertTrue(Lists.newArrayList(ite).size() >= 2);
//	}
//
//	@Test
//	public void testFindByOwnerName() {
//		List<Owner> ite = ownerReposity.findByOwnerName("Rose Lee");
//		// ArrayList<Owner> list = StreamSupport.stream(ite.spliterator(), true)
//		// .collect(Collectors.toCollection(ArrayList::new));
//		Assert.assertTrue(ite.size() >= 1);
//	}
//
//	@Test
//	public void testFindById() {
//		Assert.assertNotNull(ownerReposity.findOne(1));
//	}
//
//}
