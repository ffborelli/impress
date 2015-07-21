package br.ufabc.impress.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@Table(name = "place")
public class Place implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public static final int DEPENDENT = 0;
	public static final int INDEPENDENT = 1;
	
	@Id
	@SequenceGenerator(name = "place_func", sequenceName = "place_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "place_func")
	@Column(name = "id_place", nullable = false)
	private Integer id;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "dependent_0_independent_1", nullable = false)
	private int dependentIndependent;
	
	@ManyToOne
	@JoinColumn(name = "id_place_type_fk", referencedColumnName = "id_place_type")
	private PlaceType placeType;
	
	@OneToMany(mappedBy = "place")
	private List<Place> places;
	
	@ManyToOne
	@JoinColumn(name = "id_place_fk", referencedColumnName = "id_place")
	private Place place;
	
	@OneToMany(mappedBy = "place")
	private List<Resource> resource;
	
	@OneToMany(mappedBy = "place")
	private List<Context> context;
	
	public Place(){
		
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void setDependentIndependent(int dependentIndependent){
		this.dependentIndependent = dependentIndependent;
	}
	
	public int getDependentIndependent(){
		return this.dependentIndependent;
	}
	
	public void setPlaceType(PlaceType placeType){
		this.placeType = placeType;
	}
	
	public PlaceType getPlaceType(){
		return this.placeType;
	}
	
	public void setPlaces(List<Place> places){
		this.places = places;
	}
	
	public List<Place> getPlaces(){
		return this.places;
	}
	
	public void setPlace(Place place){
		this.place = place;
	}
	
	public Place getPlace(){
		return this.place;
	}
	
	public void setResource(List<Resource> resource){
		this.resource = resource;
	}
	
	public List<Resource> getResource(){
		return this.resource;
	}
	
	public void setContext(List<Context> context){
		this.context = context;
	}
	
	public List<Context> getContext(){
		return this.context;
	}
	
}
