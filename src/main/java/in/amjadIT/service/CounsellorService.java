package in.amjadIT.service;

import in.amjadIT.DTO.CounsellorDTO;

public interface CounsellorService {

	public CounsellorDTO login(CounsellorDTO counsellorDTO);

	// if unique return true else return false
	public boolean uniqueEmailCheck(String email);

	public boolean register(CounsellorDTO counsellorDTO);

}
