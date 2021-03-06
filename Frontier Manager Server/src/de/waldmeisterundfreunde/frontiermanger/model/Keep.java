package de.waldmeisterundfreunde.frontiermanger.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
public class Keep {

	public Keep(){

	}
	
	public Keep(String name, Faction faction, Date captueredSince,
			Alliance alliance) {
		super();
		this.name = name;
		this.faction = faction;
		this.relics = new ArrayList<Relic>();
		this.captueredSince = captueredSince;
		this.alliance = alliance;
	}

	@Id
	private String id;
	private String name;

	@Enumerated(EnumType.STRING)
	private Faction faction;
	@OneToMany(targetEntity=Relic.class)
	private Collection<Relic> relics;
	@Temporal(TemporalType.DATE)
	private Date captueredSince;
	private Alliance alliance;

	public String getId() {
		return id;
	}
	public void setId(String string) {
		this.id = string;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Faction getFaction() {
		return faction;
	}
	public void setFaction(Faction faction) {
		this.faction = faction;
	}
	public Collection<Relic> getRelics() {
		return relics;
	}
	public void addRelic(Relic relic) {
        if (!getRelics().contains(relic)) {
            getRelics().add(relic);
        }
    }
	public Date getCaptueredSince() {
		return captueredSince;
	}
	public void setCaptueredSince(Date captueredSince) {
		this.captueredSince = captueredSince;
	}
	public Alliance getAlliance() {
		return alliance;
	}
	public void setAlliance(Alliance alliance) {
		this.alliance = alliance;
	}


}
