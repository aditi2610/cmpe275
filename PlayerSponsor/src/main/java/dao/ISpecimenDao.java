package dao;

import com.player.sponsor.PlayerSponsor.dto.Player;

public interface ISpecimenDao {
	
	boolean save(Player player) throws Exception;

}
