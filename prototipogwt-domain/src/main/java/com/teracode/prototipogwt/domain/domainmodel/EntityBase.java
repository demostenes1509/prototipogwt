package com.teracode.prototipogwt.domain.domainmodel;

import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import com.teracode.prototipogwt.domain.util.DomainConstants;


/**
 * @generated
 */
@javax.xml.bind.annotation.XmlRootElement
@javax.xml.bind.annotation.XmlAccessorType(value = javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.persistence.MappedSuperclass
public class EntityBase implements java.io.Serializable {
	/**
	 * @generated
	 */
	private static final long serialVersionUID = -75886507L;
	/**
	 * @generated
	 */
	@javax.persistence.Id
	@javax.persistence.GeneratedValue(generator = "generator")
	@org.hibernate.annotations.GenericGenerator(name = "generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	    	parameters = {
  		  @Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "sequence")
  		, @Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1")
  		, @Parameter(name = SequenceStyleGenerator.INITIAL_PARAM, value = DomainConstants.FIRST_AVAILABLE_ID)
  		, @Parameter(name = SequenceStyleGenerator.OPT_PARAM, value = "none")
  	})
	private Long id;

	/**
	 * @generated
	 */
	@javax.persistence.Column(length = 1, nullable = false)
	private String active = "S";

	/**
	 * @generated
	 */
	public EntityBase() {
	}

	/**
	 * @generated
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @generated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "EntityBase" + " id=" + id + " active=" + active;
	}

	/**
	 * @generated
	 */
	public String getActive() {
		return this.active;
	}

	/**
	 * @generated
	 */
	public void setActive(String active) {
		this.active = active;
	}
}