package in.amjadIT.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.amjadIT.entity.EnquiryEntity;

public interface EnquiryRepository extends JpaRepository<EnquiryEntity, Integer> {

	//select * from enq_tbl where counsellor_Id= Id;
	public List<EnquiryEntity> findByCounsellorCounsellorId(Integer counsellorId);
	
	
	
	}


