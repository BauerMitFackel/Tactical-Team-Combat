package eu.sos.ttc.sqf.serializer;


/**
 * @author Ulrich Raab
 */
public interface Serializer <T> {

	public String serialize (T object);
}
