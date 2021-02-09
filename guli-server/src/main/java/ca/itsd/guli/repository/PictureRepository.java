package ca.itsd.guli.repository;

import ca.itsd.guli.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Picture repository that interacts with the database
 */
@Repository
public interface PictureRepository extends JpaRepository<Picture,String> {
}
