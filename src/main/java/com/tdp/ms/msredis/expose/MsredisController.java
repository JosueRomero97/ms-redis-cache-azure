package com.tdp.ms.msredis.expose;

import com.tdp.ms.msredis.business.MsredisService;
import com.tdp.ms.msredis.model.MsredisRequest;
import com.tdp.ms.msredis.model.MsredisResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Class: MsredisController. <br/>
 * <b>Copyright</b>: &copy; 2019 Telef&oacute;nica del Per&uacute;<br/>
 * <b>Company</b>: Telef&oacute;nica del Per&uacute;<br/>
 *
 * @author Telef&oacute;nica del Per&uacute; (TDP) <br/>
 *         <u>Service Provider</u>: Everis Per&uacute; SAC (EVE) <br/>
 *         <u>Developed by</u>: <br/>
 *         <ul>
 *         <li>Developer name</li>
 *         </ul>
 *         <u>Changes</u>:<br/>
 *         <ul>
 *         <li>YYYY-MM-DD Creaci&oacute;n del proyecto.</li>
 *         </ul>
 * @version 1.0
 */
@RestController
@RequestMapping("/msredis/v1/greeting")
@RequiredArgsConstructor
public class MsredisController {

    private final MsredisService msredisService;

    @GetMapping
    public Mono<MsredisResponse> indexGet() {
        return Mono.justOrEmpty(this.msredisService.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MsredisResponse> indexPost(@RequestBody MsredisRequest request) {
        return Mono.justOrEmpty(this.msredisService.put(request.getName()));
    }

}
