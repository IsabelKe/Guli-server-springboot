package ca.itsd.guli.domain;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;


/**
 * represents picture uploaded from the client
 */
@Entity
@Table(name = "picture")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Picture {
    @Id//mark this column is a primary key
    //primary key generating strategies
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    @Column(name = "picture_id")
    private String pictureId;//picture id
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "picture_name")//picture name
    private String pictureName;
    @Column(name = "upload_dir")//where pictures have been stored on the server
    private String uploadDir;
    @Column(name = "upload_time")//picture uploaded time
    private String uploadTime;
    @Lob
    private byte[] data;//pictures have stored in byte format in the database

    public Picture(Integer userId, String pictureName, String uploadDir, String uploadTime, byte[] data) {
        this.userId = userId;
        this.pictureName = pictureName;
        this.uploadDir = uploadDir;
        this.uploadTime = uploadTime;
        this.data = data;
    }
}
