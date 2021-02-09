package ca.itsd.guli.service;


import ca.itsd.guli.dto.PictureDTO;
import ca.itsd.guli.util.ResultEntity;

import java.util.List;

/**
 * this interface defines all the picture related methods
 */
public interface PictureService {
    /**
     * add a new picture
     * @param picture of the picture dto
     * @return the accessing url of the new added picture
     */
    ResultEntity<String> uploadPicture(PictureDTO picture);

    ResultEntity<List<PictureDTO>> getAllFiles();
}
