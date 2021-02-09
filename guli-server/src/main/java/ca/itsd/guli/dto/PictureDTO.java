package ca.itsd.guli.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * a customized picture dto that will be returned to the client-side
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PictureDTO {
    private String pictureId;// id of the user who uploaded this picture
    private MultipartFile file;//uploaded picture
    private String url;
    private Integer userId;//the id of user who uploaded this file

    public PictureDTO(String pictureId, String url) {
        this.pictureId = pictureId;
        this.url = url;
    }
}
