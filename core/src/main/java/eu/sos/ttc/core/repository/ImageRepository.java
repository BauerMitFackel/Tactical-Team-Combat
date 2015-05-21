package eu.sos.ttc.core.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import eu.sos.ttc.core.domain.Image;


/**
 * Repository interface defining storage, retrieval, and search behavior in context of images.
 * @author BauerMitFackel
 * @see eu.sos.ttc.core.domain.Image
 */
public interface ImageRepository extends JpaRepository<Image, Long> {

}
