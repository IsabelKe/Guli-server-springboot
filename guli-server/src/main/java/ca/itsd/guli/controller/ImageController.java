package ca.itsd.guli.controller;


import ca.itsd.guli.dto.PictureDTO;
import ca.itsd.guli.service.PictureService;
import ca.itsd.guli.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * RestController to handle all picture related requests
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/v1/files")
public class ImageController {

    @Autowired
    private PictureService pictureService;

    /**
     * add a new picture
     * @param file of the picture dto
     * @return the accessing url of the new added picture
     */
    @PostMapping("")
    public ResultEntity<String> addPicture(@ModelAttribute PictureDTO file)
    {
        return pictureService.uploadPicture(file);
    }

    /**
     * get all the files
     *
     * @return
     */
    @GetMapping("")
    public ResultEntity<List<PictureDTO>> getAllFiles()
    {
        return pictureService.getAllFiles();
    }
}
