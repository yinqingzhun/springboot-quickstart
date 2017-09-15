package com.yqz.springboot.quickstart.repository;

//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//
//import com.yqz.springboot.quickstart.model.jpa.Owner;
//
//public interface OwnerReposity extends JpaRepository<Owner, Integer> {
//	List<Owner> findByOwnerName(String ownerName);
//
//	@Query("from Owner where ownerName like CONCAT('%',CONCAT(?1,'%'))")
//	List<Owner> findByOwnerNameLike(String name);
//
//	@Modifying
//	@Query("update Owner a set a.ownerName = ?2 where a.ownerName = ?1")
//	public int updateOwnerName(String ownerName, String newName);
//}
