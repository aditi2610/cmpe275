package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.player.sponsor.PlayerSponsor.dto.Player;

import service.ISpecimenService;

public class ControllerClass {
	
	@Autowired
	private ISpecimenService service;
	
	@RequestMapping(value= "/save")
	public String saveSpecimen(Player pd) {
		pd.setFirstname("Aditi");
		try {
			service.save(pd);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "start";
	}
	
}
