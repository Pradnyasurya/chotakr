package live.chotakr.chotakr.web.controller;


import live.chotakr.chotakr.model.ChotakrRequest;
import live.chotakr.chotakr.service.ShortenService;
import live.chotakr.chotakr.service.UrlValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
public class ChotakrController {

    private final ShortenService shortenService;
    private final UrlValidationService urlValidationService;

    public ChotakrController(ShortenService shortenService, UrlValidationService urlValidationService) {
        this.shortenService = shortenService;
        this.urlValidationService = urlValidationService;
    }


    @GetMapping("/chotakr")
    @ResponseBody
    public String shortenLink(@RequestBody @Valid ChotakrRequest chotakrRequest){

        log.info("Provided URL : "+chotakrRequest.getUrl());
        if (urlValidationService.validateUrl(chotakrRequest.getUrl())){
            return shortenService.chotaKr(chotakrRequest);
        }

        throw new RuntimeException("Invalid URL!");
    }

}
