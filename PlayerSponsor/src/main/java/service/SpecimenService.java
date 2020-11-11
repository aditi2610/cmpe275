package service;

import org.springframework.beans.factory.annotation.Autowired;

import com.player.sponsor.PlayerSponsor.dto.Player;

import dao.ISpecimenDao;

public class SpecimenService implements ISpecimenService{

	
	@Autowired
	ISpecimenDao specimenDao;
	
	@Override
	public boolean save(Player photoDTO) throws Exception {
		specimenDao.save(photoDTO);
		return false;
	}

}
