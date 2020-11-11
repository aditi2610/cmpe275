package dao;

import org.springframework.data.repository.CrudRepository;

import com.player.sponsor.PlayerSponsor.dto.Player;

public interface SpecimenRepository extends CrudRepository<Player, Integer> {

}
