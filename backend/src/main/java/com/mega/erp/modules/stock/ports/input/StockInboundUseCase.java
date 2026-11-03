package com.mega.erp.modules.stock.ports.input;

import com.mega.erp.modules.stock.dto.confirm.InboundConfirmRequestDTO;
import com.mega.erp.modules.stock.dto.confirm.InboundConfirmResponseDTO;
import com.mega.erp.modules.stock.dto.preview.XmlPreviewResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface StockInboundUseCase {

    XmlPreviewResponseDTO processXmlPreview(MultipartFile xmlFile);

    InboundConfirmResponseDTO confirmInbound(InboundConfirmRequestDTO request, UUID userId);
}