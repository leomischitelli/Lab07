package it.polito.tdp.poweroutages.model;

import java.time.*;

public class PowerOutage {

	private int id;
	private int eventTypeId;
	private int tagId;
	private int areaId;
	private Nerc nerc;
	private int responsibleId;
	private int customersAffected;
	private LocalDateTime dateEventBegan;
	private LocalDateTime dateEventFinished;
	private int demandLoss;
	
	public PowerOutage(int id, int eventTypeId, int tagId, int areaId, Nerc nerc, int responsibleId,
			int customersAffected, LocalDateTime dateEventBegan, LocalDateTime dateEventFinished, int demandLoss) {
		super();
		this.id = id;
		this.eventTypeId = eventTypeId;
		this.tagId = tagId;
		this.areaId = areaId;
		this.nerc = nerc;
		this.responsibleId = responsibleId;
		this.customersAffected = customersAffected;
		this.dateEventBegan = dateEventBegan;
		this.dateEventFinished = dateEventFinished;
		this.demandLoss = demandLoss;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(int eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public Nerc getNerc() {
		return nerc;
	}

	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}

	public int getResponsibleId() {
		return responsibleId;
	}

	public void setResponsibleId(int responsibleId) {
		this.responsibleId = responsibleId;
	}

	public int getCustomersAffected() {
		return customersAffected;
	}

	public void setCustomersAffected(int customersAffected) {
		this.customersAffected = customersAffected;
	}

	public LocalDateTime getDateEventBegan() {
		return dateEventBegan;
	}

	public void setDateEventBegan(LocalDateTime dateEventBegan) {
		this.dateEventBegan = dateEventBegan;
	}

	public LocalDateTime getDateEventFinished() {
		return dateEventFinished;
	}

	public void setDateEventFinished(LocalDateTime dateEventFinished) {
		this.dateEventFinished = dateEventFinished;
	}

	public int getDemandLoss() {
		return demandLoss;
	}

	public void setDemandLoss(int demandLoss) {
		this.demandLoss = demandLoss;
	}
	
	
	
	
	
}
	
	
	