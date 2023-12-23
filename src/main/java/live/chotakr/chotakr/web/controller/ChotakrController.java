package live.chotakr.chotakr.web.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import live.chotakr.chotakr.model.ChotakrRequest;
import live.chotakr.chotakr.service.ChotakrService;
import live.chotakr.chotakr.service.UrlValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@Slf4j
public class ChotakrController {

    private final ChotakrService chotakrService;
    private final UrlValidationService urlValidationService;

    public ChotakrController(ChotakrService chotakrService, UrlValidationService urlValidationService) {
        this.chotakrService = chotakrService;
        this.urlValidationService = urlValidationService;
    }


    @GetMapping("/chotakr")
    @ResponseBody
    public String shortenLink(@RequestBody @Valid ChotakrRequest chotakrRequest){

        log.info("Provided URL : "+chotakrRequest.url());
        if (urlValidationService.validateUrl(chotakrRequest.url())){
            return chotakrService.chotaKr(chotakrRequest);
        }

        throw new RuntimeException("Invalid URL!");
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> redirectUrl(@PathVariable String id, HttpServletResponse response) throws IOException, URISyntaxException, Exception {
        String redirectUrl = ChotakrService.getLongURLFromID(id);
        log.info("redirectUrl: "+redirectUrl);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(URI.create(redirectUrl));
//        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
        response.sendRedirect(redirectUrl);
        return null;
    }

}
