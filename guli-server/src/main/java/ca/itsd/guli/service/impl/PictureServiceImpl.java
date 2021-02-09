package ca.itsd.guli.service.impl;

import ca.itsd.guli.domain.Picture;
import ca.itsd.guli.dto.PictureDTO;
import ca.itsd.guli.repository.PictureRepository;
import ca.itsd.guli.service.PictureService;
import ca.itsd.guli.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 *this class handles all business logic for all picture related requests
 */

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    //where the pictures will be stored
    private static final String UPLOAD_FOLDER="/static/uploadFile";

    /**
     * upload a picture. it will be stored in the /resources/static/uploadedFile
     * @param picture a picture dto object
     * @return the accessing url of the new added picture
     */
    @Override
    public ResultEntity<String> uploadPicture(PictureDTO picture) {
        //1.get the MultipartFile
        MultipartFile file=picture.getFile();
        Integer userId=picture.getUserId();
        //2. check if the file is empty
        if( file==null || file.isEmpty())
        {
            return ResultEntity.failed("Please select a picture first");
        }
        //3.get the file name
        String pictureName=file.getOriginalFilename();
        try {
            //4.get the byte here because getBytes() cannot be used after the transferTo()
            byte[] data = file.getBytes();
            //generate the current time
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now=new Date();
            String currentTime=sdf.format(now);
            //5.create the file path where the picture will be stored under the project
           String realPath=new String("src/main/resources"+UPLOAD_FOLDER);
            //add this file to the database
             Picture p=new Picture(userId,pictureName,realPath,currentTime,data);
             pictureRepository.save(p);

             //store this file under the project
            File folder=new File(realPath);
            // if the folder does not exist, create it
            if(!folder.isDirectory())
            {
               folder.mkdirs();
           }
            File newFile=new File(folder.getAbsolutePath()+File.separator+pictureName);
            file.transferTo(newFile);

             //get the access url
            String downloadURL= ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploadFile")
                    .path("/"+pictureName)
                    .toUriString();
            return ResultEntity.successWithData(downloadURL);
        }
        catch (Exception e)
        {
            System.out.println(e);
            return ResultEntity.failed("Please select a picture first");
        }
    }

    /**
     * get all the files
     * @return a customized files
     */
    @Override
    public ResultEntity<List<PictureDTO>> getAllFiles() {
        List<Picture> pictures=pictureRepository.findAll();
        //check if any files have been returned
        if(pictures==null)
        {
            return ResultEntity.successWithoutData("No files");
        }
        List<PictureDTO>  pictureDTOS=pictures.stream().map(file ->{
            //get the downloading url
            String downloadURL= ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploadfile/")
                    .path(file.getPictureName())
                    .toUriString();
            return new PictureDTO(
                    file.getPictureId(),
                    downloadURL);
        }).collect(Collectors.toList());
        return ResultEntity.successWithData(pictureDTOS);
    }
}
