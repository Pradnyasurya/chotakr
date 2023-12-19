package live.chotakr.chotakr.service;

import live.chotakr.chotakr.model.ChotakrRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShortenService {

    public String chotaKr(ChotakrRequest chotakrRequest){

        log.info("url : "+chotakrRequest.getUrl());

        //TODO: write URL shortening logic.


        return "www.g.com";
    }

}
