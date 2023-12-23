package live.chotakr.chotakr.service;

import live.chotakr.chotakr.model.ChotakrRequest;
import live.chotakr.chotakr.repository.URLDao;
import live.chotakr.chotakr.util.UrlMakerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ChotakrService {

    @Value("${host.name}")
    private String hostName;

    private final URLDao urlDao;

    public ChotakrService(URLDao urlDao) {
        this.urlDao = urlDao;

    }



    public String chotaKr(ChotakrRequest chotakrRequest){

        log.info("url : "+chotakrRequest.url());

        Long nextID = urlDao.getNextID();
        log.info("id : "+nextID);

        String uniqueID = UrlMakerUtil.createUniqueID(nextID);
        log.info("uniqueID : "+uniqueID);

        try {
            urlDao.saveUrl(""+nextID, chotakrRequest);
        } catch (RuntimeException e){
            e.printStackTrace();
        }

        StringBuilder shortenedUrl = new StringBuilder();

        shortenedUrl.append(hostName).append("/");
        shortenedUrl.append(uniqueID);

        return shortenedUrl.toString();
    }


    public static String getLongURLFromID(String uniqueID) throws Exception {
        Long dictionaryKey = UrlMakerUtil.getDictionaryKeyFromUniqueID(uniqueID);
        String longUrl = URLDao.getUrl(dictionaryKey);
        log.info("Converting shortened URL back to {}", longUrl);
        if(longUrl.isEmpty())
            throw new RuntimeException("Invalid Key");
        return longUrl;
    }

}
