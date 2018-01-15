package dev.topcollegues.api.entite;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vote {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
	private String avis;
	
	@ManyToOne
	private Collegue collegue;
	
	public Vote() {
		super();
	}

  /**
   * @return the id
   */
  public Integer getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * @return the avis
   */
  public String getAvis() {
    return avis;
  }

  /**
   * @param avis the avis to set
   */
  public void setAvis(String avis) {
    this.avis = avis;
  }

  /**
   * @return the collegue
   */
  public Collegue getCollegue() {
    return collegue;
  }

  /**
   * @param collegue the collegue to set
   */
  public void setCollegue(Collegue collegue) {
    this.collegue = collegue;
  }
  
}
