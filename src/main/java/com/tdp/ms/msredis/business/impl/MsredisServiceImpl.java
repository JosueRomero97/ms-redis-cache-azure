package com.tdp.ms.msredis.business.impl;

import com.tdp.ms.msredis.business.MsredisService;
import com.tdp.ms.msredis.model.MsredisResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Class: MsredisServiceImpl. <br/>
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
@Service
@RequiredArgsConstructor
public class MsredisServiceImpl implements MsredisService {

    @Override
    public MsredisResponse get() {
        return new MsredisResponse("Hello world!");
    }

    @Override
    public MsredisResponse put(String name) {
        return new MsredisResponse(name + " created!");
    }

}
