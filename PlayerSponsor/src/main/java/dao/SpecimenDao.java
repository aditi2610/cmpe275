package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.player.sponsor.PlayerSponsor.dto.Player;

@Component
public class SpecimenDao implements ISpecimenDao {
	@Autowired
	SpecimenRepository repo;
	
	@Override
	public boolean save(Player photoDto) throws Exception {
		// TODO Auto-generated method stub
		repo.save(photoDto);
		return false;
	}

}
