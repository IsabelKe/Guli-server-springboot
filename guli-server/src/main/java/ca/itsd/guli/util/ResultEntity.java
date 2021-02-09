package ca.itsd.guli.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * this is a customized class that will format all the return data
 * @param <T>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultEntity<T> {
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";
    public static final String NO_MESSAGE = "NO_MESSAGE";
    public static final String NO_DATA = "NO_DATA";

    //mark if the request is fail or success
    private String result;
    //the error message when the request is fail
    private String message;
    //data that will be returned
    private T data;



    /**
     * request success but no data
     * @return the failed status and an error message
     */
    public static <E> ResultEntity<E> successWithoutData(String message) {

        return new ResultEntity<E>(FAILED, message, null);
    }

    /**
     * request success with data
     * @return the SUCCESS status and the data
     */
    public static <E> ResultEntity<E> successWithData(E data) {

        return new ResultEntity<E>(SUCCESS, null, data);
    }
    /**
     * request failed
     * @return the failed status and an error message
     */
    public static <E> ResultEntity<E> failed(String message) {

        return new ResultEntity<E>(FAILED, message, null);
    }
}
