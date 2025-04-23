package in.amjadIT.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.amjadIT.DTO.DashboardResponseDTO;
import in.amjadIT.DTO.EnqFilterDTO;
import in.amjadIT.DTO.EnquiryDTO;
import in.amjadIT.entity.CounsellorEntity;
import in.amjadIT.entity.EnquiryEntity;
import in.amjadIT.repository.CounsellorRepository;
import in.amjadIT.repository.EnquiryRepository;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private EnquiryRepository enquiryRepository;

	@Autowired
	private CounsellorRepository counsellorRepository;

	@Override
	public DashboardResponseDTO getDashboardInfo(Integer counsellorId) {

		DashboardResponseDTO dto = new DashboardResponseDTO();
		List<EnquiryEntity> enqsList = enquiryRepository.findByCounsellorCounsellorId(counsellorId);

		dto.setTotalEnqCnt(enqsList.size());

		int openCnt = enqsList.stream().filter(enq -> enq.getEnqStatus().equalsIgnoreCase("Open")).collect(Collectors.toList())
				.size();

		dto.setOpenEnqCnt(openCnt);

		int enrolledCnt = enqsList.stream().filter(enq -> enq.getEnqStatus().equalsIgnoreCase("Enrolled"))
				.collect(Collectors.toList()).size();

		dto.setEnrolledEnqCnt(enrolledCnt);

		int lostCnt = enqsList.stream().filter(enq -> enq.getEnqStatus().equalsIgnoreCase("Lost")).collect(Collectors.toList())
				.size();

		dto.setLostEnqCnt(lostCnt);
		return dto;
	}

	@Override
	public boolean addEnquiry(EnquiryDTO enqDTO, Integer counsellorId) {
		Optional<CounsellorEntity> byId = counsellorRepository.findById(counsellorId);

		EnquiryEntity entity = new EnquiryEntity();
		BeanUtils.copyProperties(enqDTO, entity);

		if (byId.isPresent()) {
			CounsellorEntity counsellor = byId.get();
			entity.setCounsellor(counsellor);
		}

		EnquiryEntity save = enquiryRepository.save(entity);
		return save.getEnqId() != null;
	}

	@Override
	public List<EnquiryDTO> getEnquiries(Integer counsellorId) {

		List<EnquiryDTO> enqsDtoList = new ArrayList<>();

		List<EnquiryEntity> enqList = enquiryRepository.findByCounsellorCounsellorId(counsellorId);

		for (EnquiryEntity entity : enqList) {
			EnquiryDTO dto = new EnquiryDTO();
			BeanUtils.copyProperties(entity, dto);

			enqsDtoList.add(dto);
		}

		return enqsDtoList;
	}

	@Override
	public List<EnquiryDTO> getEnquiries(EnqFilterDTO filterDTO, Integer counsellorId) {

		EnquiryEntity entity = new EnquiryEntity();

		if (filterDTO.getClassMode() != null && !filterDTO.getClassMode().isEmpty()) {
			entity.setClassMode(filterDTO.getClassMode());
		}
		if (filterDTO.getCourse() != null && !filterDTO.getCourse().isEmpty()) {
			entity.setCourse(filterDTO.getCourse());
		}

		if (filterDTO.getEnqStatus() != null && !filterDTO.getEnqStatus().isEmpty()) {
			entity.setEnqStatus(filterDTO.getEnqStatus());
		}

		CounsellorEntity counsellor = new CounsellorEntity();
		counsellor.setCounsellorId(counsellorId);
		entity.setCounsellor(counsellor);
		
		Example<EnquiryEntity> of = Example.of(entity);
		List<EnquiryEntity> enqList= enquiryRepository.findAll(of);
		List<EnquiryDTO> enqsDtoList = new ArrayList<>();
		

		for (EnquiryEntity enq : enqList) {
			EnquiryDTO dto = new EnquiryDTO();
			BeanUtils.copyProperties(enq, dto);

			enqsDtoList.add(dto);
		}
		return enqsDtoList;
	}

	@Override
	public EnquiryDTO getEnquiryById(Integer enqId) {

		Optional<EnquiryEntity> byId = enquiryRepository.findById(enqId);

		if (byId.isPresent()) {
			EnquiryEntity enquiryEntity = byId.get();
			EnquiryDTO dto = new EnquiryDTO();
			BeanUtils.copyProperties(enquiryEntity, dto);
			return dto;
		}

		return null;
	}

	

}
