package com.mega.erp.modules.stock.ports.input;

import com.mega.erp.modules.stock.dto.NfeXmlDTO;
import org.springframework.web.multipart.MultipartFile;

public interface NfeXmlParserPort {
    NfeXmlDTO parseXml(MultipartFile file);
}