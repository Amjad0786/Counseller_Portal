package in.amjadIT.service;

import java.util.List;

import in.amjadIT.DTO.DashboardResponseDTO;
import in.amjadIT.DTO.EnqFilterDTO;
import in.amjadIT.DTO.EnquiryDTO;

public interface EnquiryService {

	public DashboardResponseDTO getDashboardInfo(Integer counsellorId);

	public boolean addEnquiry(EnquiryDTO enqDTO, Integer counsellorId);

	public List<EnquiryDTO> getEnquiries(Integer counsellorId);

	public List<EnquiryDTO> getEnquiries(EnqFilterDTO filterDTO, Integer counsellorId);

	public EnquiryDTO getEnquiryById(Integer enqId);

}
