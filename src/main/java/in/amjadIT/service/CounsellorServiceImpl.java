package in.amjadIT.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.amjadIT.DTO.CounsellorDTO;
import in.amjadIT.entity.CounsellorEntity;
import in.amjadIT.repository.CounsellorRepository;

@Service
public class CounsellorServiceImpl implements CounsellorService {

	@Autowired
	private CounsellorRepository counsellorRepository;

	@Override
	public CounsellorDTO login(CounsellorDTO counsellorDTO) {

		CounsellorEntity entity = counsellorRepository.findByEmailAndPwd(counsellorDTO.getEmail(),
				counsellorDTO.getPwd());

		if (entity != null) {

			CounsellorDTO dto = new CounsellorDTO();
			BeanUtils.copyProperties(entity, dto);

			return dto;
		}

		return null;
	}

	@Override
	public boolean uniqueEmailCheck(String email) {
		CounsellorEntity entity = counsellorRepository.findByEmail(email);

		return entity == null;
	}

	@Override
	public boolean register(CounsellorDTO counsellorDTO) {

		CounsellorEntity entity = new CounsellorEntity();
		BeanUtils.copyProperties(counsellorDTO, entity);

		CounsellorEntity savedEntity = counsellorRepository.save(entity);

		return null != savedEntity.getCounsellorId();
	}

}
