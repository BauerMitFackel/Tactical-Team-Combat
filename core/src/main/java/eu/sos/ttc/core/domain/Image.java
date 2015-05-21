package eu.sos.ttc.core.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


/**
 * Image domain object.
 * @author BauerMitFackel
 */
@Entity
@Table(name = "image")
public class Image {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@Lob
	@Column(name = "bytes")
	private byte[] bytes;

	@Column(name = "media_type", unique = false, nullable = false)
	private String type;


	/**
	 * Creates a new image instance using the specified bytes and type.
	 * @param bytes The bytes of the image.
	 * @param type The media type of the image
	 */
	public static Image create (byte[] bytes, String type) {

		Image image = new Image();
		image.setBytes(bytes);
		image.setType(type);

		return image;
	}


	/**
	 * Mandatory empty constructor for JPA.
	 */
	@SuppressWarnings("unused")
	protected Image () {
	}


	/**
	 * Returns the id of the image.
	 */
	public long getId () {
		return id;
	}


	/**
	 * Returns the bytes of the image.
	 */
	public byte[] getBytes () {
		return bytes;
	}


	/**
	 * Sets the bytes of the image.
	 */
	private void setBytes (byte[] bytes) {

		if (bytes == null || bytes.length == 0) {
			throw new IllegalArgumentException("bytes must be not null and not empty");
		}

		this.bytes = bytes;
	}


	/**
	 * Returns the media type of the image.
	 */
	public String getType () {
		return type;
	}


	/**
	 * Sets the media type of the image.
	 */
	private void setType (String type) {

		if (type == null || type.isEmpty()) {
			throw new IllegalArgumentException("type must be not null and not empty");
		}

		this.type = type;
	}


	@Override
	public boolean equals (Object object) {

		if (this == object) {
			return true;
		}

		if (object == null) {
			return false;
		}

		if (!(object instanceof Image)) {
			return false;
		}

		Image image = (Image) object;
		return getId() == image.getId();
	}


	@Override
	public int hashCode () {
		return (int) (getId() ^ (getId() >>> 32));
	}


	@Override
	public String toString () {
		return String.format("%s{id=%d}", Image.class.getSimpleName(), getId());
	}
}
